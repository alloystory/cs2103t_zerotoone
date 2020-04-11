package seedu.zerotoone.logic.parser.workout;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_WORKOUT_NAME;

import seedu.zerotoone.logic.commands.workout.CreateCommand;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.util.ArgumentMultimap;
import seedu.zerotoone.logic.parser.util.ArgumentTokenizer;
import seedu.zerotoone.model.workout.WorkoutName;

/**
 * Parses input arguments and creates a new CreateCommand object
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateCommand
     * and returns an CreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap;

        try {
            argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_WORKOUT_NAME);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        WorkoutName workoutName = WorkoutParserUtil.parseWorkoutName(
                    argMultimap.getValue(PREFIX_WORKOUT_NAME).get());

        return new CreateCommand(workoutName);
    }
}
