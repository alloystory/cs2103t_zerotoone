package seedu.zerotoone.logic.commands.workout;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.zerotoone.commons.core.LogsCenter;
import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.commands.util.Commands;
import seedu.zerotoone.model.Model;
import seedu.zerotoone.model.workout.PredicateFilterWorkoutName;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Finds and lists all workouts in workout list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends WorkoutCommand {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.WORKOUT_FIND;
    public static final String MESSAGE_WORKOUTS_LISTED_OVERVIEW = "%1$d workout(s) listed!";

    private final WorkoutName workoutName;
    private final Logger logger = LogsCenter.getLogger(getClass());

    public FindCommand(WorkoutName workoutName) {
        requireNonNull(workoutName);
        this.workoutName = workoutName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info(String.format("Executing %s for Workout with %s",
                getClass().getSimpleName(),
                workoutName.fullName));

        requireNonNull(model);
        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }
        PredicateFilterWorkoutName predicate = new PredicateFilterWorkoutName(workoutName.fullName);

        model.updateFilteredWorkoutList(predicate);

        String outputMessage = String.format(MESSAGE_WORKOUTS_LISTED_OVERVIEW,
                model.getFilteredWorkoutList().size());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && workoutName.equals(((FindCommand) other).workoutName)); // state check
    }
}
