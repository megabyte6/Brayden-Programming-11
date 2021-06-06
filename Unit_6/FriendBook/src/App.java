import java.io.File;
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

    private String settingFilePath = "./settings.txt";
    private ArrayList<String> userDataFilePaths = new ArrayList<String>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Store the number of groups to the database
        // FriendDatabase.setStringArray("friendGroups", new String[]{"AGroup", "AnotherGroup"});

        // File settingsFile = new File(settingFilePath);
        // if (settingsFile.exists()) {

            // File userData = new File(userDataFile);
            // if (userData.exists()) {
            //     FileOutputStream fileOutputStream = new FileOutputStream(userData);
            //     ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            //     // Read Friends from file


            //     fileOutputStream.close();
            //     objectOutputStream.close();
            // }
        // }

        // Create the missing settings file
        // try {
        //     settingsFile.createNewFile();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        FriendDatabase.setStringArray("groupIDs", new String[]{"mainGroup"});
        FriendDatabase.setString("groupID", "mainGroup");
        FriendDatabase.newFriendGroup(FriendDatabase.getString("groupID"), 
                new Friend("John", "Glasscot"),
                new Friend("Sebastian", "Stan"),
                new Friend("Tony", "Stark"));
        primaryStage.setTitle("Friend Book");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("layout/FriendBook.fxml"))));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // Store the number of friend groups to the settings file
        // FileWriter settingsFileWriter = new FileWriter(settingFilePath);
        // settingsFileWriter.write("Groups:" + FriendDatabase.getStringArray("friendGroups"));
        // settingsFileWriter.close();

        // for (int i = 0; i < userDataFilePaths.size(); i++) {
        //     FileWriter dataWriter = new FileWriter(userDataFilePaths.get(i));
        //     dataWriter.write();
        // }

        // FileOutputStream fileOutputStream = new FileOutputStream(userData);
        // ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        
        // // Write Friends to file
        // for (int i = 0; i < FriendDatabase.friendArraySize(); i++) {
        //     objectOutputStream.writeObject(FriendDatabase.getFriend(i));
        // }

        // objectOutputStream.close();
        // fileOutputStream.close();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
