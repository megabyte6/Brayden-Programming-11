import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FriendTest {
    Friend testFriend;
    
    @Before
    public void setup(){
        testFriend = new Friend();
        testFriend.setFirstName("Paul");
        testFriend.setLastName("Zaremba");
        testFriend.setBirthDate(2000, 5, 17);
        testFriend.setGender("Male");
        testFriend.setHeight(177.8, "cm");
    }

    @Test
    public void testAge(){
        assertEquals(testFriend.getAge(), 21);

        testFriend.setBirthDate(1995, 5, 17);

        assertEquals(testFriend.getAge(), 26);
    }

    @Test
    public void testItemMissing() {
        assertTrue(testFriend.isGenderSet());
        assertFalse(testFriend.isOtherInfoSet());

        testFriend.setGender("");
        testFriend.setOtherInfo("A friend");

        assertFalse(testFriend.isGenderSet());
        assertTrue(testFriend.isOtherInfoSet());
        assertEquals(testFriend.getOtherInfo(), "A friend");
    }
}
