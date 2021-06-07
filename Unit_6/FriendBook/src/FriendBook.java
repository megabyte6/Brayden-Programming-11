import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FriendBook implements Initializable {

    private final Supplier<String> friendGroupID = () ->
            FriendDatabase.getString("groupID");
    private final Supplier<Friend[]> friendGroup = () ->
            FriendDatabase.getFriendGroup(friendGroupID.get());
    private final Supplier<Integer> friendGroupSize = () ->
            FriendDatabase.friendGroupSize(friendGroupID.get());

    @FXML
    private ListView<Friend> listView_friendList = new ListView<Friend>();

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        FriendDatabase.setInteger("friendIndex", -1);

        // Initialize ListView
        for (int i = 0; i < friendGroupSize.get(); i++) {
            listView_friendList.getItems().add(
                    FriendDatabase.getFriend(friendGroupID.get(), i));
        }
        listView_friendList.getSelectionModel().selectFirst();

        displayInfo();
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
        if (listView_friendList.getItems().size() != friendGroupSize.get()) {
            // Reload all items
            listView_friendList.getItems().clear();
            for (int i = 0; i < friendGroupSize.get(); i++) {
                listView_friendList.getItems().add(
                        FriendDatabase.getFriend(friendGroupID.get(), i));
            }
            return;
        }
        for (int i = 0; i < friendGroupSize.get(); i++) {
            if (!listView_friendList.getItems().get(i)
                    .equals(FriendDatabase.getFriend(friendGroupID.get(), i))) {
                listView_friendList.getItems().set(i,
                        FriendDatabase.getFriend(friendGroupID.get(), i));
            }
        }
    }

    public void displayInfo() {
        Friend friend = listView_friendList.getSelectionModel().getSelectedItem();
        if (listView_friendList.getSelectionModel().getSelectedIndex() == -1) return;
        // Check if the values were set and if so, display them
        if (friend.isNameSet()) label_name.setText(friend.getName());
        if (friend.isAgeSet()) label_age.setText(String.valueOf(friend.getAge()));
        if (friend.isBirthDateSet()) {
            String birthDate = "";
            switch (friend.getBirthMonth()) {
                case 1: birthDate += "Jan "; break;
                case 2: birthDate += "Feb "; break;
                case 3: birthDate += "Mar "; break;
                case 4: birthDate += "Apr "; break;
                case 5: birthDate += "May "; break;
                case 6: birthDate += "Jun "; break;
                case 7: birthDate += "Jul "; break;
                case 8: birthDate += "Aug "; break;
                case 9: birthDate += "Sep "; break;
                case 10: birthDate += "Oct "; break;
                case 11: birthDate += "Nov "; break;
                case 12: birthDate += "Dec "; break;
            }
            birthDate += friend.getBirthDay() + ", ";
            birthDate += friend.getBirthYear();

            label_birthday.setText(birthDate);
        }
        if (friend.isHeightSet()) {
            label_height.setText(friend.getHeightUnit().equals("cm")
                    ? friend.getHeight("cm") + " cm"
                    : friend.getHeight("in") + " inches");
        }
        if (friend.isGenderSet()) label_gender.setText(friend.getGender());
        if (friend.isOtherInfoSet()) label_otherInfo.setText(friend.getOtherInfo());
    }

    public void createFriend() {
        FriendDatabase.changeInteger("friendIndex", -1);
        openWindow("layout/EditFriend.fxml", "CreateFriend");
    }

    public void editFriend() {
        int selectedIndex = listView_friendList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Please select a friend to edit", ButtonType.OK)
                    .showAndWait();
            return;
        }
        FriendDatabase.changeInteger("friendIndex", selectedIndex);
        openWindow("layout/EditFriend.fxml", "EditFriend");
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
            FriendDatabase.removeFriend(friendGroupID.get(), selectedFriendIndex);
        }
    }

    public void moveItemUp() {
        int selectedIndex = listView_friendList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Select a friend to move", ButtonType.OK)
                    .showAndWait();
            return;
        } else if (selectedIndex == 0) return;
        Friend removedFriend = FriendDatabase.removeFriend(friendGroupID.get(), selectedIndex);
        FriendDatabase.insertFriend(removedFriend, friendGroupID.get(), selectedIndex - 1);
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
        Friend removedFriend = FriendDatabase.removeFriend(friendGroupID.get(), selectedIndex);
        FriendDatabase.insertFriend(removedFriend, friendGroupID.get(), selectedIndex + 1);
        refresh();
        listView_friendList.getSelectionModel().select(selectedIndex + 1);
    }
}
