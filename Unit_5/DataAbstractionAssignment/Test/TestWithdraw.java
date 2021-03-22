import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestWithdraw {
    // TODO : Run tests on TestWithdraw to check for errors
    Deposit checkingWithdrawal, savingWithdrawal;

    @Before
    public void setup() {
        this.checkingWithdrawal = new Withdraw(30.0, new Date(), "Checking");
        this.savingWithdrawal = new Withdraw(158.5, new Date(), "Saving");
    }

    @Test
    // Effect: Tests to check if the Withdraw class can correctly create a string from the
    // information it contains
    // Note: I did test the date with the actual date but replaced it after with 'new Date()' to
    // make it easier to test later
    public void testToString() {
        assertEquals("Withdrawal of: $30.0 Date: " + new Date() + " into account: Checking", checkingWithdrawal.toString());
        assertEquals("Withdrawal of: $158.5 Date: " + new Date() + " into account: Saving", savingWithdrawal.toString());
    }
}
