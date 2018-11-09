package seedu.clinicio.storage;

import static seedu.clinicio.testutil.TypicalPersons.ADAM;
import static seedu.clinicio.testutil.TypicalPersons.AMY_APPT;
import static seedu.clinicio.testutil.TypicalPersons.BRYAN;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.clinicio.commons.exceptions.IllegalValueException;
import seedu.clinicio.model.patient.Nric;
import seedu.clinicio.testutil.Assert;

public class XmlAdaptedPatientTest {
    private final String invalidNric = "S123456";

    private final String validName = BRYAN.getName().toString();
    private final String validPhone = BRYAN.getPhone().toString();
    private final String validEmail = BRYAN.getEmail().toString();
    private final String validAddress = BRYAN.getAddress().toString();
    private final String VALID_NRIC = BRYAN.getNric().toString();
    private final List<XmlAdaptedMedicalProblem> validMedProbs = BRYAN.getMedicalProblems().stream()
            .map(XmlAdaptedMedicalProblem::new)
            .collect(Collectors.toList());
    private final List<XmlAdaptedMedication> validMeds = BRYAN.getMedications().stream()
            .map(XmlAdaptedMedication::new)
            .collect(Collectors.toList());
    private final List<XmlAdaptedAllergy> validAllergies = BRYAN.getAllergies().stream()
            .map(XmlAdaptedAllergy::new)
            .collect(Collectors.toList());
    private final XmlAdaptedStaff validPrefDoc = new XmlAdaptedStaff(ADAM);
    private final XmlAdaptedAppointment validAppt = new XmlAdaptedAppointment(AMY_APPT);

    /*@Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        XmlAdaptedPatient patient = new XmlAdaptedPatient(BRYAN);
        System.out.println(patient.toModelType());
        assertEquals(BRYAN, patient.toModelType());
    }*/

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(validName, invalidNric, validPhone, validEmail, validAddress,
                        validMedProbs, validMeds, validAllergies, false,
                        validPrefDoc, validAppt);
        String expectedMessage = Nric.MESSAGE_NRIC_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }
    /*
    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(validName, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_MED_PROBS, VALID_MEDS, VALID_ALLERGIES, false,
                        VALID_PREF_DOC, VALID_APPT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Nric.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    } */

    /*@Test
    public void toModelType_invalidMedicalProblems_throwsIllegalValueException() {
        List<XmlAdaptedMedicalProblem> invalidMedProbs = new ArrayList<>(VALID_MED_PROBS);
        invalidMedProbs.add(new XmlAdaptedMedicalProblem(INVALID_MED_PROB));
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(validName, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, invalidMedProbs, VALID_MEDS, VALID_ALLERGIES,
                        false, VALID_PREF_DOC, VALID_APPT);
        Assert.assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_invalidMedications_throwsIllegalValueException() {
        List<XmlAdaptedMedication> invalidMeds = new ArrayList<>(VALID_MEDS);
        invalidMeds.add(new XmlAdaptedMedication(INVALID_MED));
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(validName, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_MED_PROBS, invalidMeds, VALID_ALLERGIES,
                        false, VALID_PREF_DOC, VALID_APPT);
        Assert.assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAllergies_throwsIllegalValueException() {
        List<XmlAdaptedAllergy> invalidAllergies = new ArrayList<>(VALID_ALLERGIES);
        invalidAllergies.add(new XmlAdaptedAllergy(INVALID_MED));
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(validName, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_MED_PROBS, VALID_MEDS, invalidAllergies,
                        false, VALID_PREF_DOC, VALID_APPT);
        Assert.assertThrows(IllegalValueException.class, patient::toModelType);
    }*/
}
