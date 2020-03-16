package seedu.zerotoone.logic.parser.exercise;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_EXERCISE_NAME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.zerotoone.logic.parser.ArgumentMultimap;
import seedu.zerotoone.logic.parser.ArgumentTokenizer;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.Prefix;

// import seedu.zerotoone.logic.commands.exercise.DeleteCommand;
import seedu.zerotoone.logic.parser.exercise.DeleteCommand;

import seedu.zerotoone.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXERCISE_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_EXERCISE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        // Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_EXERCISE_NAME).get());

        return new DeleteCommand();
        // return new DeleteCommand(name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}