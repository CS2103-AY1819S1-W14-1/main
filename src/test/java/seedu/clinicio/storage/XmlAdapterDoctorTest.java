package seedu.clinicio.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.clinicio.storage.XmlAdaptedDoctor.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.clinicio.testutil.TypicalPersons.BEN;

import org.junit.jupiter.api.Test;

import seedu.clinicio.commons.exceptions.IllegalValueException;

import seedu.clinicio.model.doctor.Id;
import seedu.clinicio.model.doctor.Password;
import seedu.clinicio.model.person.Name;
import seedu.clinicio.testutil.Assert;

//@@author jjlee050
public class XmlAdapterDoctorTest {
    private static final int INVALID_ID = -1;
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PASSWORD = "";

    private static final int VALID_ID = BEN.getId().id;
    private static final String VALID_NAME = BEN.getName().toString();
    private static final String VALID_PASSWORD = BEN.getPassword().toString();

    @Test
    public void toModelType_validDoctorDetails_returnsDoctor() throws Exception {
        XmlAdaptedDoctor doctor = new XmlAdaptedDoctor(BEN);
        assertEquals(BEN, doctor.toModelType());
    }

    @Test
    public void toModelType_invalidID_throwsIllegalValueException() {
        XmlAdaptedDoctor doctor =
                new XmlAdaptedDoctor(INVALID_ID, VALID_NAME, VALID_PASSWORD);
        String expectedMessage = Id.MESSAGE_ID_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        XmlAdaptedDoctor doctor =
                new XmlAdaptedDoctor(VALID_ID, INVALID_NAME, VALID_PASSWORD);
        String expectedMessage = Name.MESSAGE_NAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        XmlAdaptedDoctor doctor = new XmlAdaptedDoctor(VALID_ID, null, VALID_PASSWORD);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidPassword_throwsIllegalValueException() {
        XmlAdaptedDoctor doctor =
                new XmlAdaptedDoctor(VALID_ID, VALID_NAME, INVALID_PASSWORD);
        String expectedMessage = Password.MESSAGE_PASSWORD_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullPassword_throwsIllegalValueException() {
        XmlAdaptedDoctor doctor = new XmlAdaptedDoctor(VALID_ID, VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Password.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

}
