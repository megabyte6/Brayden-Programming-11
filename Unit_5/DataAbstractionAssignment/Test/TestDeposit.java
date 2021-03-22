import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDeposit {
    // TODO : Run tests on TestDeposit to check for errors
    Deposit checkingDeposit, savingDeposit;

    @Before
    public void setup() {
        this.checkingDeposit = new Deposit(30.0, new Date(), "Checking");
        this.savingDeposit = new Deposit(158.5, new Date(), "Saving");
    }
    
    @Test
    // Effect: Tests to check if the Deposit class can correctly create a string from the
    // information it contains
    // Note: I have tested the date with the actual date but replaced it after with 'new Date()' to
    // make testing easier later
    public void testToString() {
        assertEquals("Deposit of: $30.0 Date: " + new Date() + " into account: Checking", checkingDeposit.toString());
        assertEquals("Deposit of: $158.5 Date: " + new Date() + " into account: Saving", savingDeposit.toString());
    }
}
