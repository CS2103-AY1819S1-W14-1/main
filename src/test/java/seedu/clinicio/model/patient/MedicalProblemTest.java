package seedu.clinicio.model.patient;

import org.junit.Test;
import seedu.clinicio.testutil.Assert;

public class MedicalProblemTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new MedicalProblem(null));
    }

    @Test
    public void constructor_invalidMedicalProblem_throwsIllegalArgumentException() {
        String invalidMedicalProblem = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new MedicalProblem(invalidMedicalProblem));
    }
}
