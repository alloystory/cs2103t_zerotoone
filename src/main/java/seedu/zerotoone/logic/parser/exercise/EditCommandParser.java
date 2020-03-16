package seedu.zerotoone.logic.parser.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_EXERCISE_NAME;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_NEW_EXERCISE_NAME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.zerotoone.logic.parser.ArgumentMultimap;
import seedu.zerotoone.logic.parser.ArgumentTokenizer;
import seedu.zerotoone.logic.parser.Parser;
import seedu.zerotoone.logic.parser.Prefix;
import seedu.zerotoone.commons.core.index.Index;
// import seedu.zerotoone.logic.commands.exercise.EditCommand;
// import seedu.zerotoone.logic.commands.exercise.EditCommand.EditExerciseDescriptor;
import seedu.zerotoone.logic.parser.exercise.EditCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXERCISE_NAME, PREFIX_NEW_EXERCISE_NAME);
        
        if (!arePrefixesPresent(argMultimap, PREFIX_EXERCISE_NAME, PREFIX_NEW_EXERCISE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        // EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        // if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
        //     editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        // }

        // if (!editPersonDescriptor.isAnyFieldEdited()) {
        //     throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        // }

        return new EditCommand();
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
