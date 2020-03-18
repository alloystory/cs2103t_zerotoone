package seedu.zerotoone.storage.exercise.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zerotoone.storage.exercise.util.JacksonExercise.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.zerotoone.testutil.Assert.assertThrows;
// import static seedu.zerotoone.testutil.TypicalExercises.BENSON;

import java.util.ArrayList;
import java.util.List;
// import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.commons.exceptions.IllegalValueException;
import seedu.zerotoone.storage.exercise.util.JacksonExercise;

public class JacksonExerciseTest {
    // private static final String INVALID_NAME = "R@chel";
    // private static final String INVALID_PHONE = "+651234";
    // private static final String INVALID_ADDRESS = " ";
    // private static final String INVALID_EMAIL = "example.com";
    // private static final String INVALID_TAG = "#friend";

    // // private static final String VALID_NAME = BENSON.getName().toString();
    // // private static final String VALID_PHONE = BENSON.getPhone().toString();
    // // private static final String VALID_EMAIL = BENSON.getEmail().toString();
    // // private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    // // private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
    // //         .map(JsonAdaptedTag::new)
    // //         .collect(Collectors.toList());
    // private static final String VALID_NAME = null;
    // private static final String VALID_PHONE = null;
    // private static final String VALID_EMAIL = null;
    // private static final String VALID_ADDRESS = null;
    // private static final List<JsonAdaptedTag> VALID_TAGS = new ArrayList<>();

    // @Test
    // public void toModelType_validExerciseDetails_returnsExercise() throws Exception {
    //     JacksonExercise exercise = new JacksonExercise(BENSON);
    //     assertEquals(BENSON, exercise.toModelType());
    // }

    // @Test
    // public void toModelType_invalidName_throwsIllegalValueException() {
    //     JacksonExercise exercise =
    //             new JacksonExercise(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
    //     String expectedMessage = Name.MESSAGE_CONSTRAINTS;
    //     assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    // }

    // @Test
    // public void toModelType_nullName_throwsIllegalValueException() {
    //     JacksonExercise exercise =
    //             new JacksonExercise(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
    //     String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
    //     assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    // }

    // @Test
    // public void toModelType_invalidPhone_throwsIllegalValueException() {
    //     JacksonExercise exercise =
    //             new JacksonExercise(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
    //     String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
    //     assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    // }

    // @Test
    // public void toModelType_nullPhone_throwsIllegalValueException() {
    //     JacksonExercise exercise =
    //             new JacksonExercise(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
    //     String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
    //     assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    // }

    // @Test
    // public void toModelType_invalidEmail_throwsIllegalValueException() {
    //     JacksonExercise exercise =
    //             new JacksonExercise(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
    //     String expectedMessage = Email.MESSAGE_CONSTRAINTS;
    //     assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    // }

    // @Test
    // public void toModelType_nullEmail_throwsIllegalValueException() {
    //     JacksonExercise exercise =
    //             new JacksonExercise(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS);
    //     String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
    //     assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    // }

    // @Test
    // public void toModelType_invalidAddress_throwsIllegalValueException() {
    //     JacksonExercise exercise =
    //             new JacksonExercise(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
    //     String expectedMessage = Address.MESSAGE_CONSTRAINTS;
    //     assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    // }

    // @Test
    // public void toModelType_nullAddress_throwsIllegalValueException() {
    //     JacksonExercise exercise =
    //             new JacksonExercise(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
    //     String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
    //     assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    // }

    // @Test
    // public void toModelType_invalidTags_throwsIllegalValueException() {
    //     List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
    //     invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
    //     JacksonExercise exercise =
    //             new JacksonExercise(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
    //     assertThrows(IllegalValueException.class, exercise::toModelType);
    // }

}
