import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.github.megabyte6.utils.Database;

public class FriendDatabase extends Database {

    private static HashMap<String, ArrayList<Friend>> friendList = new HashMap<>();
    // private static ArrayList<Friend> friends = new ArrayList<Friend>();

    protected FriendDatabase() {}

    /**
     * Check if the group of Friend objects exist
     * @param groupID The ID of the group to check
     * @return {@code true} if there is an group corresponding with the ID
     *         given, otherwise returns {@code false}
     */
    public static boolean friendGroupExists(String groupID) {
        return FriendDatabase.friendList.get(groupID) == null ? false : true;
    }

    /**
     * @param groupID The ID of the group to find
     * @return An array of Friends
     */
    public static Friend[] getFriendGroup(String groupID) {
        if (!FriendDatabase.friendGroupExists(groupID)) return null;
        return (Friend[]) FriendDatabase.friendList.get(groupID).toArray();
    }

    /**
     * Create a new group of Friends
     * @param groupID   The ID of the new group
     * @param friends   An ArrayList of Friend objects to add to the group
     * @return {@code true} if the group was successfully added or
     *         {@code false} if the group with that ID already exists
     */
    public static boolean newFriendGroup(String groupID, ArrayList<Friend> friends) {
        if (!FriendDatabase.friendGroupExists(groupID)) return false;
        FriendDatabase.friendList.put(groupID, friends);
        return true;
    }

    /**
     * Create a new group of Friends
     * @param groupID   The ID of the new group
     * @param friends   Friend objects to add to the group
     * @return {@code true} if the group was successfully added or
     *         {@code false} if the group with that ID already exists
     */
    public static boolean newFriendGroup(String groupID, Friend... friends) {
        if (!FriendDatabase.friendGroupExists(groupID)) return false;
        FriendDatabase.friendList.put(groupID, new ArrayList<Friend>());
        Arrays.asList(friends)
                .forEach(e -> FriendDatabase.friendList.get(groupID).add(e));
        return true;
    }

    /**
     * Change the data in the group corresponding to the ID given
     * @param groupID   The ID of the group to change
     * @param friends   An ArrayList of Friend objects to replace with
     * @return {@code true} if the group was replaced successfully, otherwise
     *         returns {@code false}
     */
    public static boolean changeFriendGroup(String groupID, ArrayList<Friend> friends) {
        if (FriendDatabase.friendGroupExists(groupID)) return false;
        FriendDatabase.friendList.put(groupID, friends);
        return true;
    }

    /**
     * Change the data in the group corresponding to the ID given
     * @param groupID   The ID of the group to change
     * @param friends   Friend objects to replace with
     * @return {@code true} if the group was replaced successfully, otherwise
     *         returns {@code false}
     */
    public static boolean changeFriendGroup(String groupID, Friend... friends) {
        if (FriendDatabase.friendGroupExists(groupID)) return false;
        FriendDatabase.friendList.put(groupID, new ArrayList<Friend>());
        Arrays.asList(friends)
                .forEach(e -> FriendDatabase.friendList.get(groupID).add(e));
        return true;
    }

    /**
     * Change the ID of a group
     * @param groupID   The ID of the group to change
     * @param newID     The ID change to
     * @return {@code true} if the ID was replaced successfully or
     *         {@code false} if the group doesn't exist
     */
    public static boolean changeFriendGroupID(String groupID, String newID) {
        if (!FriendDatabase.friendGroupExists(groupID)) return false;
        FriendDatabase.friendList.put(newID, FriendDatabase.friendList.get(groupID));
        return true;
    }

    /**
     * Remove a group of Friend objects
     * @param groupID
     * @return The value previously corresponding to {@code groupID}, or
     *         {@code null} if there was no value corresponding to
     *         {@code groupID}. (A {@code null} return can also indicate that
     *         the {@code null} was previously associated with {@code groupID})
     */
    public static ArrayList<Friend> removeFriendGroup(String groupID) {
        return FriendDatabase.friendList.remove(groupID);
    }

    /**
     * Get the size of the group of Friends
     * @param groupID The ID of the group to get the size of
     * @return The length of the array. Will return -1 if there is no group
     *         corresponding to {@code groupID}
     */
    public static int friendGroupSize(String groupID) {
        if (!FriendDatabase.friendGroupExists(groupID)) return -1;
        return FriendDatabase.friendList.get(groupID).size();
    }

    /**
     * Get a Friend from the group specified
     * @param index     The index of the Friend to get
     * @param groupID   The group to check
     * @return The Friend object that matches the index. Will return
     *         {@code null} if the index is out of range ( 
     *         {@code index < 0 || index >= size()}) or if the group
     *         corresponding to {@code groupID} doesn't exist
     */
    public static Friend getFriend(String groupID, int index) {
        if (!FriendDatabase.friendGroupExists(groupID)) return null;
        return FriendDatabase.friendList.get(groupID).get(index);
    }

    /**
     * Add a Friend to end of the group specified
     * @param groupID   The ID of the group to add it to
     * @param friend    The friend to add
     */
    public static void addFriend(String groupID, Friend friend) {
        FriendDatabase.friendList.get(groupID).add(friend);
    }

    /**
     * Insert a Friend to an index in the array of Friends
     * @param friend    The Friend to be stored
     * @param groupID   The group to add the Friend object to
     * @param index     The index to store the new Friend
     * @return {@code true} if the new Friend object was successfully inserted
     *         or {@code false} if the {@code groupID} given doesn't correspond
     *         to a group.
     */
    public static boolean insertFriend(Friend friend, String groupID, int index) {
        if (!FriendDatabase.friendGroupExists(groupID)) return false;
        FriendDatabase.friendList.get(groupID).add(index, friend);
        return true;
    }

    /**
     * Replace a Friend object with another
     * @param groupID   The group to modify
     * @param index     The index of the Friend to replace
     * @param newFriend The new Friend object
     * @return The Friend object previously at {@code index}. Will return
     *         {@code null} if there is no group corresponding to
     *         {@code groupID} or if {@code index} is out of range (
     *         {@code (index < 0 || index >= size())})
     */
    public static Friend replaceFriend(String groupID, int index, Friend newFriend) {
        if (!FriendDatabase.friendGroupExists(groupID)) return null;
        return FriendDatabase.friendList.get(groupID).set(index, newFriend);
    }

    /**
     * Remove a Friend object from the specified group
     * @param groupID   The group containing the Friend object
     * @param index     The index of the Friend to remove
     * @return The Friend object previously at {@code index}. Will return
     *         {@code null} if there is no group corresponding to
     *         {@code groupID} or if {@code index} is out of range (
     *         {@code (index < 0 || index >= size())})
     */
    public static Friend removeFriend(String groupID, int index) {
        if (!FriendDatabase.friendGroupExists(groupID)) return null;
        return FriendDatabase.friendList.get(groupID).remove(index);
    }

    /**
     * Remove a Friend object from the specified group
     * @param groupID   The group containing the Friend object
     * @param friend    The Friend object to remove
     * @return {@code false} if the group corresponding to the {@code groupID}
     *         doesn't exist or if the group doesn't contain the specified
     *         Friend
     */
    public static boolean removeFriend(String groupID, Friend friend) {
        if (!FriendDatabase.friendGroupExists(groupID)) return false;
        return FriendDatabase.friendList.get(groupID).remove(friend);
    }
}
