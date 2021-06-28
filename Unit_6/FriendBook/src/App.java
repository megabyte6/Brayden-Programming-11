import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
I am aware that storing the Friend objects in a String format is not very
efficient or practical but this course requires me to do it using this
method.
*/

public class App extends Application {

    private File settingsFile = new File("data/settings");
    private ArrayList<String> groupPaths = new ArrayList<String>();

    @Override
    public void start(Stage primaryStage) throws Exception, IOException {
        // Use for reading files
        String line;

        if (settingsFile.exists() && settingsFile.isFile()) {

            // Read settings file
            BufferedReader settingsReader = new BufferedReader(new FileReader(settingsFile));
            while ((line = settingsReader.readLine()) != null) {
                groupPaths.add(line);
            }
            settingsReader.close();

            // Get rid of extra whitespace
            for (int i = 0; i < groupPaths.size(); i++) {
                if (groupPaths.get(i).length() == 0) groupPaths.remove(i);
            }

            ArrayList<String> friendGroupNames = new ArrayList<>();

            for (int i = 0; i < groupPaths.size(); i++) {
                File friendGroupFile = new File(groupPaths.get(i));

                if (friendGroupFile.exists()
                        && friendGroupFile.isFile()
                        && friendGroupFile.canRead()) {
                    BufferedReader groupReader = new BufferedReader(new FileReader(friendGroupFile));
                    ArrayList<String> data = new ArrayList<>();

                    // Read Friend objects from file
                    while ((line = groupReader.readLine()) != null) {
                        data.add(line);
                    }

                    // Get the groupName and remove the header
                    String groupName = data.remove(0);
                    if (data.get(0).length() == 0) data.remove(0);
                    // Create new friend group
                    FriendDatabase.newFriendGroup(groupName);
                    friendGroupNames.add(groupName);
                    // Create Friend objects and store them into a group
                    Friend tempFriend = new Friend();
                    for (String item : data) {
                        if (item.length() == 0) {
                            FriendDatabase.addFriend(groupName, tempFriend);
                            tempFriend = new Friend();
                            continue;
                        } else if (item.startsWith("FirstName:")) {
                            item = item.substring("FirstName:".length());
                            tempFriend.setFirstName(item);
                        } else if (item.startsWith("LastName:")) {
                            item = item.substring("LastName:".length());
                            tempFriend.setLastName(item);
                        } else if (item.equals("BirthDate:")) {
                            item = item.substring("BirthDate:".length());
                            String[] birthDate = item.split("/");
                            tempFriend.setBirthDate(
                                    Integer.parseInt(birthDate[0]),
                                    Integer.parseInt(birthDate[1]),
                                    Integer.parseInt(birthDate[2]));
                        } else if (item.equals("Height:")) {
                            item = item.substring("Height:".length());
                            String[] height = item.split(":");
                            tempFriend.setHeight(Double.parseDouble(height[1]), height[0]); 
                        } else if (item.equals("Gender:")) {
                            item = item.substring("Gender:".length());
                            tempFriend.setGender(item);
                        } else if (item.equals("Other:")) {
                            item = item.substring("Other:".length());
                            tempFriend.setOtherInfo(item);
                        }
                    }
                    groupReader.close();

                } else {
                    groupPaths.remove(i);
                    i--;
                }
            }

            // Add all the group names to the database
            FriendDatabase.setStringArray("groupIDs", friendGroupNames.toArray(new String[0]));
            // Set the selected group
            FriendDatabase.setString("groupID", FriendDatabase.getStringArray("groupIDs")[0]);

        } else {
            // Create the missing settings file
            try {
                settingsFile = new File("data");
                settingsFile.mkdir();
                settingsFile = new File("data/settings");
                settingsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Add all the group names to the database
            FriendDatabase.setStringArray("groupIDs", new String[]{"All Friends"});
            // Set the selected group
            FriendDatabase.setString("groupID", FriendDatabase.getStringArray("groupIDs")[0]);



            String[] tempArray = new String[FriendDatabase.getStringArray("groupIDs").length + 2];
            for (int i = 0; i < FriendDatabase.getStringArray("groupIDs").length; i++) {
                tempArray[i] = FriendDatabase.getStringArray("groupIDs")[i];
            }
            tempArray[tempArray.length - 2] = "My Friends";
            tempArray[tempArray.length - 1] = "Other Friends";
            FriendDatabase.changeStringArray("groupIDs", tempArray);
            FriendDatabase.newFriendGroup("My Friends",
                    new Friend("George", "Glasscot"),
                    new Friend("Sebastian", "Stan"),
                    new Friend("Tony", "Stark"));
            FriendDatabase.newFriendGroup("Other Friends",
                    new Friend("Adam", "Levine"),
                    new Friend("Selena", "Gomez"),
                    new Friend("George", "Segal"),
                    new Friend("Hayley", "Orrantia"));
        }

        primaryStage.setTitle("Friend Book");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("layout/FriendBook.fxml"))));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // Store the number of friend groups to the settings file
        BufferedWriter settingsWriter = new BufferedWriter(new FileWriter(settingsFile));
        String[] groupNames = FriendDatabase.getStringArray("groupIDs");
        for (String name : groupNames) {
            settingsWriter.write("data/" + name + "\n");
        }
        settingsWriter.close();

        for (int i = 0; i < groupNames.length; i++) {
            File friendGroupFile = new File("data/" + groupNames[i]);
            if (!friendGroupFile.exists() || !friendGroupFile.isFile()) {
                friendGroupFile.createNewFile();
            } else {
                new BufferedWriter(new FileWriter("data/" + groupNames[i])).close();
            }

            BufferedWriter friendWriter = new BufferedWriter(
                    new FileWriter(friendGroupFile.getAbsolutePath(), true));
            friendWriter.write(groupNames[i] + "\n\n");
            if (FriendDatabase.friendGroupSize(groupNames[i]) == 0) {
                friendWriter.close();
                continue;
            }

            for (int j = 0; j < FriendDatabase.friendGroupSize(groupNames[i]); j++) {
                Friend friend = FriendDatabase.getFriend(groupNames[i], j);
                friendWriter.write("FirstName:" + friend.getFirstName() + "\n"
                        + "LastName:" + friend.getLastName() + "\n");
                if (friend.isBirthDateSet())
                        friendWriter.write("BirthDate:" + friend.getBirthDate() + "\n");
                if (friend.isHeightSet())
                        friendWriter.write("Height:" + friend.getHeightUnit() + ":"
                                + friend.getHeight(friend.getHeightUnit()) + "\n");
                if (friend.isGenderSet())
                        friendWriter.write("Gender:" + friend.getGender() + "\n");
                if (friend.isOtherInfoSet())
                        friendWriter.write("Other:" + friend.getOtherInfo() + "\n");
                friendWriter.write("\n");
            }
            friendWriter.close();
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
