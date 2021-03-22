import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestWithdraw {
    // TODO : Run tests on TestWithdraw to check for errors
    Deposit checkingWithdrawal, savingWithdrawal;

    @Before
    public void setup() {
        this.checkingWithdrawal = new Deposit(30.0, new Date(), "Checking");
        this.savingWithdrawal = new Deposit(158.5, new Date(), "Saving");
    }

    // Note: I did test the date with the actual date but replaced it after with 'new Date()' to
    // make it easier to test later
    @Test
    public void testToString() {
        assertEquals("Withdrawal of: $30.0 Date: " + new Date() + " into account: Checking", checkingWithdrawal.toString());
        assertEquals("Withdrawal of: $158.5 Date: " + new Date() + " into account: Saving", savingWithdrawal.toString());
    }
}
