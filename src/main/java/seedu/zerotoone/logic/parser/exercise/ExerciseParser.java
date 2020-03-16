package seedu.zerotoone.logic.parser.exercise;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.zerotoone.logic.commands.Command;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.HelpCommand;

// import seedu.zerotoone.logic.commands.exercise.CreateCommand;
// import seedu.zerotoone.logic.commands.exercise.ListCommand;
// import seedu.zerotoone.logic.commands.exercise.EditCommand;
// import seedu.zerotoone.logic.commands.exercise.DeleteCommand;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.Model;

class CreateCommand extends Command {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = "some message usage";

    public CommandResult execute(Model model) { return new CommandResult("CreateCommand"); }
}

class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public CommandResult execute(Model model) { return new CommandResult("ListCommand"); }
}

class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public CommandResult execute(Model model) { return new CommandResult("EditCommand"); }
}

class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public CommandResult execute(Model model) { return new CommandResult("DeleteCommand"); }
}

/**
 * Parses user input.
 */
public class ExerciseParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case CreateCommand.COMMAND_WORD:
            return new CreateCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
