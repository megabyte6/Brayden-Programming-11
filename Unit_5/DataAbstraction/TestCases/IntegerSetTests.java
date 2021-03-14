import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerSetTests {
    IntegerSet testSet;

    @Before
    public void setup(){
        testSet = new IntegerSet();
    }

    @Test
    public void testInsertNotThere(){
        //check number is not already in set
        assertEquals(testSet.size(), 0);
        assertFalse(testSet.contains(3));
        //insert a number
        testSet.insert(3);
        //check the number is in the set
        assertEquals(testSet.size(), 1);
        assertTrue(testSet.contains(3));
    }

    @Test
    public void testInsertAlreadyThere() {
        int currentSize = testSet.size();
        // Check if the number is already there
        assertEquals(testSet.size(), 1);
        assertTrue(testSet.contains(4));
        // Insert a number
        testSet.insert(4);
        // Check that there is only one of the number
        assertEquals(currentSize, testSet.size());
    }
}
