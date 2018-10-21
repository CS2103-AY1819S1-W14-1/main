package seedu.clinicio.model.doctor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.clinicio.testutil.Assert;

//@@author jjlee050
public class IdTest {

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        int invalidId = -1;
        Assert.assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        //Invalid id (less than 0)
        assertFalse(Id.isValidId(-1)); //Negative
        assertFalse(Id.isValidId(-9478));
        assertFalse(Id.isValidId(-120 * 102));
        assertFalse(Id.isValidId((int) Math.pow(2, 32) + 1)); //More than 2 ^ 32

        //valid id
        assertTrue(Id.isValidId(0)); //Zero
        assertTrue(Id.isValidId(1)); // One
        assertTrue(Id.isValidId(20));
        assertTrue(Id.isValidId(015)); //Leading zeros.
        assertTrue(Id.isValidId(002040));
        assertTrue(Id.isValidId(350274));
        assertTrue(Id.isValidId((int) Math.pow(2, 16)));
        assertTrue(Id.isValidId((int) Math.pow(2, 32))); //Very large number
    }
}
