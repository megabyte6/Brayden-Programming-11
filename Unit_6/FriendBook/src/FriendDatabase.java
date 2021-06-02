import java.util.ArrayList;

import com.github.megabyte6.utils.Database;

public class FriendDatabase extends Database {

    private static ArrayList<Friend> friends = new ArrayList<Friend>();

    protected FriendDatabase() {}

    /**
     * Get the array of Friends
     * 
     * @return
     * The array of Friends
     */
    static Friend[] getFriendArray() {
        return (Friend[]) friends.toArray();
    }
    
    /**
     * Add a Friend to end of the array
     * 
     * @param friend    The friend to add
     * @return
     * Index of the new Friend. Will return -1 if the Friend was unsuccessfully
     * added
     */
    public static int addFriend(Friend friend) {
        boolean noError = FriendDatabase.friends.add(friend);

        // Check for errors
        if (!noError) return -1;

        return FriendDatabase.friends.size() - 1;
    }
    
    /**
     * Get a Friend from the array of Friends
     * 
     * @param index     The index of the Friend to get
     * @return
     * The Friend that matches the index
     */
    public static Friend getFriend(int index) {
        return FriendDatabase.friends.get(index);
    }

    /**
     * Insert a Friend to an index in the array of Friends
     * 
     * @param friend    The Friend to be stored
     * @param index     The index to store the new Friend
     * @return
     * The index of the new Friend.
     */
    public static int insertFriend(Friend friend, int index) {
        FriendDatabase.friends.add(index, friend);
        return index;
    }

    /**
     * Replace a Friend
     * 
     * @param newFriend The new Friend
     * @param index     The index of the Friend to replace
     */
    public static void replaceFriend(Friend newFriend, int index) {
        FriendDatabase.friends.set(index, newFriend);
    }

    /**
     * Remove a Friend from the array of Friends
     * 
     * @param index     The index of the Friend to remove
     * @return
     * The removed Friend
     */
    public static Friend removeFriend(int index) {
        return FriendDatabase.friends.remove(index);
    }

    /**
     * Get the size of the array of Friends
     * 
     * @return
     * The length of the array
     */
    public static int friendArraySize() {
        return FriendDatabase.friends.size();
    }
}
