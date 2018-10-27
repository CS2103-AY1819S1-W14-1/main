package seedu.clinicio.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static seedu.clinicio.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.clinicio.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.clinicio.logic.parser.exceptions.ParseException;
import seedu.clinicio.model.doctor.Doctor;
import seedu.clinicio.model.password.Password;
import seedu.clinicio.model.person.Address;
import seedu.clinicio.model.person.Email;
import seedu.clinicio.model.person.Name;
import seedu.clinicio.model.person.Person;
import seedu.clinicio.model.person.Phone;
import seedu.clinicio.model.tag.Tag;
import seedu.clinicio.testutil.Assert;


public class ParserUtilTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_ROLE = "abc";
    private static final String INVALID_PASSWORD = "";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_ROLE_DOCTOR = "doctor";
    private static final String VALID_ROLE_RECEPTIONIST = "receptionist";
    private static final String VALID_PASSWORD = "doctor1";

    private static final String WHITESPACE = " \t\r\n";

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void parseIndex_invalidInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseIndex("10 a");
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_INVALID_INDEX);
        ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        ParserUtil.parseTag(null);
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseTag(INVALID_TAG);
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        ParserUtil.parseTags(null);
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseRole_allNullFields_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        ParserUtil.parseRole(null, null, null);
    }

    @Test
    public void parseRole_someNullFields_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);

        // null role
        ParserUtil.parseRole(null, new Name(VALID_NAME), new Password(VALID_PASSWORD, false));

        // null password
        ParserUtil.parseRole(VALID_ROLE_DOCTOR, new Name(VALID_NAME), null);

        // null name
        ParserUtil.parseRole(VALID_ROLE_DOCTOR, null, new Password(VALID_PASSWORD, false));
    }

    @Test
    public void parseRole_allInvalidFields_throwsParseException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                ParserUtil.parseRole(INVALID_ROLE,
                        new Name(INVALID_NAME),
                        new Password(INVALID_PASSWORD, false)));
    }

    @Test
    public void parseRole_someInvalidFields_throwsParseException() {
        // Invalid password
        Assert.assertThrows(IllegalArgumentException.class, () ->
                ParserUtil.parseRole(VALID_ROLE_DOCTOR,
                        new Name(VALID_NAME),
                        new Password(INVALID_PASSWORD, false)));

        // Invalid role
        Assert.assertThrows(IllegalArgumentException.class, () ->
                ParserUtil.parseRole(INVALID_ROLE,
                        new Name(INVALID_NAME),
                        new Password(INVALID_PASSWORD, false)));

        // Invalid name
        Assert.assertThrows(IllegalArgumentException.class, () ->
                ParserUtil.parseRole(VALID_ROLE_DOCTOR,
                        new Name(INVALID_NAME),
                        new Password(VALID_PASSWORD, false)));
    }

    @Test
    public void parseRole_validFieldsWithoutWhitespace_returnsPerson() throws Exception {
        Person expectedDoctor = new Doctor(new Name(VALID_NAME),
                new Password(VALID_PASSWORD, false));
        assertEquals(expectedDoctor,
                ParserUtil.parseRole(VALID_ROLE_DOCTOR,
                        new Name(VALID_NAME),
                        new Password(VALID_PASSWORD, false)));

        //TODO: Receptionist
    }

    @Test
    public void parseRole_validFieldsWithWhitespace_returnsPerson() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Person expectedDoctor = new Doctor(new Name(VALID_NAME),
                new Password(VALID_PASSWORD, false));
        assertEquals(expectedDoctor,
                ParserUtil.parseRole(VALID_ROLE_DOCTOR,
                        ParserUtil.parseName(nameWithWhitespace),
                        new Password(VALID_PASSWORD, false)));

        //TODO: Receptionist
    }

    @Test
    public void parsePassword_null_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        ParserUtil.parsePassword(null);
    }

    @Test
    public void parsePassword_invalidPassword_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        ParserUtil.parsePassword(INVALID_PASSWORD);
    }

    @Test
    public void parsePassword_validValueWithoutWhitespace_returnsPassword() throws Exception {
        Password expectedPassword = new Password(VALID_PASSWORD, false);
        assertEquals(expectedPassword, ParserUtil.parsePassword(VALID_PASSWORD));
    }
}
