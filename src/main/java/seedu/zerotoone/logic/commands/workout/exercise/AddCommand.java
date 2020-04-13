package seedu.zerotoone.logic.commands.workout.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.model.workout.WorkoutModel.PREDICATE_SHOW_ALL_WORKOUTS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.commons.core.Messages;
import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.workout.Workout;

/**
 * Adds an existing exercise to a specified workout.
 */
public class AddCommand extends WorkoutExerciseCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.WORKOUT_EXERCISE_ADD;
    public static final String MESSAGE_ADD_WORKOUT_EXERCISE_SUCCESS = "Added the exercise %s to %s!";

    private final Index workoutId;
    private final Index exerciseId;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * @param workoutId of the workout in the filtered workout list to edit
     * @param exerciseId of the exercise in the filtered exercise list to add
     */
    public AddCommand(Index workoutId, Index exerciseId) {
        requireNonNull(workoutId);
        requireNonNull(exerciseId);

        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info(String.format("Executing %s for Workout with %d and Exercise with %d",
                getClass().getSimpleName(),
                workoutId.getOneBased(),
                exerciseId.getOneBased()));

        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        List<Workout> lastShownWorkoutList = model.getFilteredWorkoutList();
        List<Exercise> lastShownExerciseList = model.getFilteredExerciseList();

        if (workoutId.getZeroBased() >= lastShownWorkoutList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        if (exerciseId.getZeroBased() >= lastShownExerciseList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Workout workoutToEdit = lastShownWorkoutList.get(workoutId.getZeroBased());
        Exercise exerciseToAdd = lastShownExerciseList.get(exerciseId.getZeroBased());

        if (exerciseToAdd.getExerciseSets().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_WORKOUT_EXERCISE);
        }

        List<Exercise> updatedWorkoutExercises = new ArrayList<>(workoutToEdit.getWorkoutExercises());
        updatedWorkoutExercises.add(exerciseToAdd);

        Workout editedWorkout = new Workout(workoutToEdit.getWorkoutName(), updatedWorkoutExercises);

        model.setWorkout(workoutToEdit, editedWorkout);
        model.updateFilteredWorkoutList(PREDICATE_SHOW_ALL_WORKOUTS);

        String outputMessage = String.format(MESSAGE_ADD_WORKOUT_EXERCISE_SUCCESS,
                exerciseToAdd.getExerciseName().fullName,
                workoutToEdit.getWorkoutName().fullName);
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof AddCommand)) {
            return false;
        }

        // state check
        AddCommand otherCommand = (AddCommand) other;
        return workoutId.equals(otherCommand.workoutId)
                && exerciseId.equals(otherCommand.exerciseId);
    }
}
