import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert.assertEquals;
import org.junit.Assert.assertFalse;
import org.junit.Assert.assertTrue;

public class TestCustomer {
    // TODO : Run tests for the Customer class
    Customer testCustomer;

    @Before
    public void setup() {
        this.testCustomer = new Customer("Johnathen", 23432423, 550.25, 50.0);
    }

    @Test
    // Effects: Test deposit method to make sure that deposits work properly
    public void testDeposit() {
        assertEquals(400.0, testCustomer.deposit(400.0, new Date(), "Checking"));
        assertEquals(500.0, testCustomer.deposit(500.0, new Date(), "Saving"));
    }

    @Test
    // Effects: Test withdraw method to make sure that withdrawing works correctly
    public void testWithdraw() {
        assertEquals(356.5, testCustomer.withdraw(356.5, new Date(), "Checking"));
        assertEquals(553.0, testCustomer.withdraw(553.0, new Date(), "Saving"));
        // Check if random account names work
        assertEquals(0.0, testCustomer.withdraw(55.0, new Date(), "saving"));
        assertEquals(0.0, testCustomer.withdraw(34.0, new Date(), "vhalojhf"));
        // Check if withdrawing past the overflow works
        assertEquals(0.0, testCustomer.withdraw(1000.0, new Date(), "Checking"));
        assertEquals(0.0, testCustomer.withdraw(500.0, new Date(), "Saving"));
    }
}
