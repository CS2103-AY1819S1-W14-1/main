package seedu.clinicio.storage;

import static org.junit.Assert.assertEquals;

import static seedu.clinicio.storage.XmlAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
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
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_NRIC = "S123456";
    private static final String INVALID_MED_PROB = "#cancer";
    private static final String INVALID_MED = "#tremedol";
    private static final String INVALID_ALLERGY = "#sand";

    private static final String VALID_NAME = BRYAN.getName().toString();
    private static final String VALID_PHONE = BRYAN.getPhone().toString();
    private static final String VALID_EMAIL = BRYAN.getEmail().toString();
    private static final String VALID_ADDRESS = BRYAN.getAddress().toString();
    private static final String VALID_NRIC = BRYAN.getNric().toString();
    private static final List<XmlAdaptedMedicalProblem> VALID_MED_PROBS = BRYAN.getMedicalProblems().stream()
            .map(XmlAdaptedMedicalProblem::new)
            .collect(Collectors.toList());
    private static final List<XmlAdaptedMedication> VALID_MEDS = BRYAN.getMedications().stream()
            .map(XmlAdaptedMedication::new)
            .collect(Collectors.toList());
    private static final List<XmlAdaptedAllergy> VALID_ALLERGIES = BRYAN.getAllergies().stream()
            .map(XmlAdaptedAllergy::new)
            .collect(Collectors.toList());
    private static final XmlAdaptedStaff VALID_PREF_DOC = new XmlAdaptedStaff(ADAM);
    private static final XmlAdaptedAppointment VALID_APPT = new XmlAdaptedAppointment(AMY_APPT);

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        XmlAdaptedPatient patient = new XmlAdaptedPatient(BRYAN);
        assertEquals(BRYAN, patient.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(VALID_NAME, INVALID_NRIC, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_MED_PROBS, VALID_MEDS, VALID_ALLERGIES, false,
                        VALID_PREF_DOC, VALID_APPT);
        String expectedMessage = Nric.MESSAGE_NRIC_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(VALID_NAME, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_MED_PROBS, VALID_MEDS, VALID_ALLERGIES, false,
                        VALID_PREF_DOC, VALID_APPT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Nric.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    /*@Test
    public void toModelType_invalidMedicalProblems_throwsIllegalValueException() {
        List<XmlAdaptedMedicalProblem> invalidMedProbs = new ArrayList<>(VALID_MED_PROBS);
        invalidMedProbs.add(new XmlAdaptedMedicalProblem(INVALID_MED_PROB));
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, invalidMedProbs, VALID_MEDS, VALID_ALLERGIES,
                        false, VALID_PREF_DOC, VALID_APPT);
        Assert.assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_invalidMedications_throwsIllegalValueException() {
        List<XmlAdaptedMedication> invalidMeds = new ArrayList<>(VALID_MEDS);
        invalidMeds.add(new XmlAdaptedMedication(INVALID_MED));
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_MED_PROBS, invalidMeds, VALID_ALLERGIES,
                        false, VALID_PREF_DOC, VALID_APPT);
        Assert.assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAllergies_throwsIllegalValueException() {
        List<XmlAdaptedAllergy> invalidAllergies = new ArrayList<>(VALID_ALLERGIES);
        invalidAllergies.add(new XmlAdaptedAllergy(INVALID_MED));
        XmlAdaptedPatient patient =
                new XmlAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_MED_PROBS, VALID_MEDS, invalidAllergies,
                        false, VALID_PREF_DOC, VALID_APPT);
        Assert.assertThrows(IllegalValueException.class, patient::toModelType);
    }*/
}
