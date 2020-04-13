package seedu.zerotoone.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

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
import seedu.zerotoone.model.schedule.DateTime;
import seedu.zerotoone.model.schedule.OneTimeSchedule;
import seedu.zerotoone.model.workout.Workout;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Adds an workout to the schedule list.
 */
public class CreateCommand extends ScheduleCommand {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = "Usage: " + Commands.SCHEDULE_CREATE;
    public static final String MESSAGE_SUCCESS = "New schedule added: %1$s";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This schedule already exists";
    public static final String MESSAGE_DATETIME_IN_THE_PAST = "Datetime provided is in the past";

    private static final Logger logger = LogsCenter.getLogger(CreateCommand.class);
    private final Index workoutId;
    private final DateTime dateTime;

    /**
     * Creates a CreateCommand to add the specified {@code Schedule}
     */
    public CreateCommand(Index workoutId, DateTime dateTIme) {
        requireNonNull(workoutId);
        requireNonNull(dateTIme);
        this.workoutId = workoutId;
        this.dateTime = dateTIme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing schedule create command");

        if (model.isInSession()) {
            throw new CommandException(Command.MESSAGE_SESSION_STARTED);
        }

        if (!DateTime.isDateEqualOrLaterThanToday(dateTime)) {
            throw new CommandException(MESSAGE_DATETIME_IN_THE_PAST);
        }

        requireNonNull(model);
        List<Workout> lastShownList = model.getFilteredWorkoutList();

        if (workoutId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Workout workoutToSchedule = lastShownList.get(workoutId.getZeroBased());
        WorkoutName workoutName = workoutToSchedule.getWorkoutName();
        OneTimeSchedule schedule = new OneTimeSchedule(workoutName, dateTime);
        if (model.hasSchedule(schedule)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }

        model.addSchedule(schedule);

        return new CommandResult(String.format(MESSAGE_SUCCESS, schedule.toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof CreateCommand)) {
            return false;
        }

        // state check
        CreateCommand otherCommand = (CreateCommand) other;
        return workoutId.equals(otherCommand.workoutId)
                && dateTime.equals(otherCommand.dateTime);
    }
}
