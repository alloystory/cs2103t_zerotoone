package seedu.zerotoone.logic.parser.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_EXERCISE_NAME;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_WORKOUT_NAME;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.parser.exceptions.ParseException;

public class ArgumentTokenizerTest {
    @Test
    public void tokenize_emptyArgsString_noValues() {
        String argsString = "  ";
        assertThrows(ParseException.class, () ->
                ArgumentTokenizer.tokenize(argsString, PREFIX_EXERCISE_NAME));
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefix} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Prefix prefix, String... expectedValues) {
        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefix).get(i));
        }
    }

    @Test
    public void tokenize_oneArgument() throws ParseException {
        // Preamble present
        String argsString = "  Some preamble string w/ Argument value ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_WORKOUT_NAME);
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, PREFIX_WORKOUT_NAME, "Argument value");

        // No preamble
        argsString = " w/   Argument value ";
        argMultimap = ArgumentTokenizer.tokenize(argsString, PREFIX_WORKOUT_NAME);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, PREFIX_WORKOUT_NAME, "Argument value");
    }

    @Test
    public void tokenize_multipleArguments() throws ParseException {
        // Only two arguments are present
        assertThrows(ParseException.class, () ->
                ArgumentTokenizer.tokenize("SomePreambleString e/value w/value",
                PREFIX_EXERCISE_NAME, PREFIX_WORKOUT_NAME, PREFIX_DATETIME));

        // Prefixes not previously given to the tokenizer should throw an error
        assertThrows(ParseException.class, () ->
                ArgumentTokenizer.tokenize("/w some value", PREFIX_EXERCISE_NAME,
                PREFIX_WORKOUT_NAME, PREFIX_DATETIME));

        // All three arguments are present
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                "Different Preamble String e/value1 w/value2 d/value3",
                PREFIX_EXERCISE_NAME, PREFIX_WORKOUT_NAME, PREFIX_DATETIME);
        assertPreamblePresent(argMultimap, "Different Preamble String");
        assertArgumentPresent(argMultimap, PREFIX_EXERCISE_NAME, "value1");
        assertArgumentPresent(argMultimap, PREFIX_WORKOUT_NAME, "value2");
        assertArgumentPresent(argMultimap, PREFIX_DATETIME, "value3");
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() {
        String argsString = "SomePreambleString e/value1 e/value2";
        assertThrows(ParseException.class, () ->
                ArgumentTokenizer.tokenize(argsString, PREFIX_EXERCISE_NAME));
    }

    @Test
    public void tokenize_multipleArgumentsJoined() {
        String argsString = "SomePreambleString e/joinedw/joined d/not joined";
        assertThrows(ParseException.class, () ->
                ArgumentTokenizer.tokenize(argsString,
                PREFIX_EXERCISE_NAME, PREFIX_WORKOUT_NAME, PREFIX_DATETIME));
    }

    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("aaa");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Prefix("aaa"));

        assertNotEquals(aaa, "aaa");
        assertNotEquals(aaa, new Prefix("aab"));
    }

}
