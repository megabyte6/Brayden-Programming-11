import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FriendBook implements Initializable {

    @FXML
    private SplitPane splitPane_root;

    @FXML
    private ListView<Friend> listView_friendList = new ListView<Friend>();
    @FXML
    private Button button_refresh;
    @FXML
    private Button button_moveItemUp;
    @FXML
    private Button button_moveItemDown;

    @FXML
    private Label label_name;
    @FXML
    private Label label_age;
    @FXML
    private Label label_birthday;
    @FXML
    private Label label_height;
    @FXML
    private Label label_gender;
    @FXML
    private Label label_otherInfo;
    @FXML
    private Button button_createFriend;
    @FXML
    private Button button_editFriend;
    @FXML
    private Button button_deleteFriend;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        FriendDatabase.setInteger("friendIndex", -1);

        // TODO : Get friends from file
        FriendDatabase.addFriend(new Friend("John", "Glasscot"));
        FriendDatabase.addFriend(new Friend("Sebastian", "Stan"));
        FriendDatabase.addFriend(new Friend("Tony", "Stark"));

        // Initialize ListView
        for (int i = 0; i < FriendDatabase.friendArraySize(); i++) {
            listView_friendList.getItems().add(FriendDatabase.getFriend(i));
        }
        listView_friendList.getSelectionModel().selectFirst();
    }

    private Stage openWindow(String fxmlFile, String titleName) {
        Stage tempStage = new Stage();
        tempStage.setTitle(titleName);

        // Try to get fxml file for UI
        try {
            tempStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlFile))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tempStage.show();

        return tempStage;
    }

    public void processKey(KeyEvent key) {
        if (key.isControlDown()) {
            if (key.getCode() == KeyCode.R) {
                refresh();
            } else if (key.getCode() == KeyCode.N) {
                createFriend();
            } else if (key.getCode() == KeyCode.E) {
                editFriend();
            }
        } else if (key.getCode() == KeyCode.DELETE) {
            deleteFriend();
        } 
    }

    public void refresh() {
        // Check if the ListView and array of Friend objects are out of sync
        // Should not happen normally
        if (listView_friendList.getItems().size() != FriendDatabase.friendArraySize()) {
            // Reload all items
            listView_friendList.getItems().clear();
            for (int i = 0; i < FriendDatabase.friendArraySize(); i++) {
                listView_friendList.getItems().add(FriendDatabase.getFriend(i));
            }
        }
        for (int i = 0; i < FriendDatabase.friendArraySize(); i++) {
            if (!(listView_friendList.getItems().get(i))
                    .equals(FriendDatabase.getFriend(i))) {
                listView_friendList.getItems().set(i, FriendDatabase.getFriend(i));
            }
        }
    }

    public void createFriend() {
        FriendDatabase.changeInteger("friendIndex", -1);
        openWindow("fxml/EditFriend.fxml", "CreateFriend");
    }

    public void editFriend() {
        int selectedIndex = listView_friendList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Please select a friend to edit", ButtonType.OK)
                    .showAndWait();
            return;
        }
        FriendDatabase.changeInteger("friendIndex", selectedIndex);
        openWindow("fxml/EditFriend.fxml", "EditFriend");
    }

    public void deleteFriend() {
        int selectedFriendIndex = listView_friendList.getSelectionModel().getSelectedIndex();
        int newSelectedIndex = listView_friendList.getItems().size() - 2;
        if (selectedFriendIndex == -1) {
            new Alert(AlertType.NONE, "Please select a friend to remove", ButtonType.OK)
                    .showAndWait();
            return;
        }

        Alert deleteFriend = new Alert(
                AlertType.NONE,
                "Are you sure you want to delete this friend?",
                ButtonType.YES, ButtonType.NO);
        deleteFriend.showAndWait();

        if (deleteFriend.getResult() == ButtonType.YES) {
            listView_friendList.getSelectionModel().select(newSelectedIndex);
            listView_friendList.getItems().remove(selectedFriendIndex);
            FriendDatabase.removeFriend(selectedFriendIndex);
        }
    }

    public void moveItemUp() {
        int selectedIndex = listView_friendList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Select a friend to move", ButtonType.OK)
                    .showAndWait();
            return;
        } else if (selectedIndex == 0) return;
        Friend removedFriend = FriendDatabase.removeFriend(selectedIndex);
        FriendDatabase.insertFriend(removedFriend, selectedIndex - 1);
        refresh();
        listView_friendList.getSelectionModel().select(selectedIndex - 1);
    }

    public void moveItemDown() {
        int selectedIndex = listView_friendList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Select a friend to move", ButtonType.OK)
                    .showAndWait();
            return;
        } else if (selectedIndex == listView_friendList.getItems().size() - 1) return;
        Friend removedFriend = FriendDatabase.removeFriend(selectedIndex);
        FriendDatabase.insertFriend(removedFriend, selectedIndex + 1);
        refresh();
        listView_friendList.getSelectionModel().select(selectedIndex + 1);
    }
}
