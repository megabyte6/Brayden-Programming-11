import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import com.github.megabyte6.utils.javafx.InputBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FriendBook implements Initializable {

    private final Supplier<String[]> groupIDs = () ->
            FriendDatabase.getStringArray("groupIDs");
    private final Supplier<String> friendGroupID = () ->
            FriendDatabase.getString("groupID");
    private final Supplier<Integer> friendGroupSize = () ->
            FriendDatabase.friendGroupSize(friendGroupID.get());

    private boolean refreshListenerAdded = false;

    @FXML
    private ComboBox<String> selectGroup;
    @FXML
    private ListView<Friend> friendList;

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

        // Initialize ComboBox
        for (String item : groupIDs.get()) {
            selectGroup.getItems().add(item);
        }
        selectGroup.getSelectionModel().selectFirst();

        // Initialize ListView
        for (int i = 0; i < friendGroupSize.get(); i++) {
            friendList.getItems().add(
                    FriendDatabase.getFriend(friendGroupID.get(), i));
        }
        friendList.getSelectionModel().selectFirst();

        displayInfo();
    }

    private Stage openWindow(String fxmlFile, String titleName) {
        // Check if the refresh listener was set
         if (!refreshListenerAdded) {
            Stage currentStage = (Stage) friendList.getScene().getWindow();
            currentStage.focusedProperty().addListener(
                    (observable, oldNode, newNode) -> refresh());
            refreshListenerAdded = true;
        }

        Stage tempStage = new Stage();
        tempStage.setTitle(titleName);
        try {
            tempStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlFile))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tempStage.show();

        return tempStage;
    }

    public void processKey(KeyEvent key) {
        if (key.isControlDown() && key.isShiftDown()) {
            if (key.getCode() == KeyCode.N) {
                createGroup();
            } else if (key.getCode() == KeyCode.E) {
                renameGroup();
            }
        } else if (key.isControlDown()) {
            if (key.getCode() == KeyCode.R) {
                refresh();
            } else if (key.getCode() == KeyCode.N) {
                createFriend();
            } else if (key.getCode() == KeyCode.E) {
                editFriend();
            }
        } else if (key.isShiftDown()) {
            if (key.getCode() == KeyCode.DELETE) {
                deleteGroup();
            }
        } else if (key.getCode() == KeyCode.DELETE) {
            deleteFriend();
        } 
    }

    public void refresh() {
        // Check if the ComboBox and array of IDs are out of sync
        if (selectGroup.getItems().size() != groupIDs.get().length) {
            selectGroup.getItems().clear();
            for (int i = 0; i < groupIDs.get().length; i++) {
                selectGroup.getItems().add(groupIDs.get()[i]);
            }
        } else {
            for (int i = 0; i < groupIDs.get().length; i++) {
                // Check if the the items in selectGroup match the database
                if (!selectGroup.getItems().get(i).equals(groupIDs.get()[i])) {
                    selectGroup.getItems().set(i, groupIDs.get()[i]);
                }
            }
        }
        selectGroup.getSelectionModel().select(friendGroupID.get());

        // Check if the ListView and array of Friend objects are out of sync
        if (friendList.getItems().size() != friendGroupSize.get()) {
            // Reload all items
            friendList.getItems().clear();
            for (int i = 0; i < friendGroupSize.get(); i++) {
                friendList.getItems().add(
                        FriendDatabase.getFriend(friendGroupID.get(), i));
            }
        } else {
            for (int i = 0; i < friendGroupSize.get(); i++) {
                // Check if the items displayed match the database
                if (!friendList.getItems().get(i)
                        .equals(FriendDatabase.getFriend(friendGroupID.get(), i))) {
                    friendList.getItems().set(i,
                            FriendDatabase.getFriend(friendGroupID.get(), i));
                }
            }
        }

        // Check which friend should be selected
        if (FriendDatabase.getInteger("friendIndex") != -1) {
            friendList.getSelectionModel().select(FriendDatabase.getInteger("friendIndex"));
        }

        displayInfo();
    }

    public void displayInfo() {
        Friend friend = friendList.getSelectionModel().getSelectedItem();
        if (friendList.getSelectionModel().getSelectedIndex() == -1) return;

        // Check if the values were set and if so, display them
        label_name.setText(friend.getName());
        label_age.setText(friend.isAgeSet() ? String.valueOf(friend.getAge()) : "");
        String birthDate = "";
        if (friend.isBirthDateSet()) {
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
        }
        label_birthday.setText(birthDate);
        if (friend.isHeightSet()) {
            label_height.setText(friend.getHeightUnit().equals("cm")
                    ? friend.getHeight("cm") + " cm"
                    : friend.getHeight("in") + " Inches");
        } else label_height.setText("");
        label_gender.setText(friend.getGender());
        label_otherInfo.setText(friend.getOtherInfo());
    }





    public void createFriend() {
        FriendDatabase.changeInteger("friendIndex", -1);
        openWindow("layout/EditFriend.fxml", "CreateFriend");
    }

    public void editFriend() {
        int selectedIndex = friendList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Please select a friend to edit", ButtonType.OK)
                    .showAndWait();
            return;
        }
        FriendDatabase.changeInteger("friendIndex", selectedIndex);
        openWindow("layout/EditFriend.fxml", "EditFriend");
    }

    public void deleteFriend() {
        int selectedFriendIndex = friendList.getSelectionModel().getSelectedIndex();
        int newSelectedIndex = friendList.getItems().size() - 2;
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
            friendList.getSelectionModel().select(newSelectedIndex);
            friendList.getItems().remove(selectedFriendIndex);
            FriendDatabase.removeFriend(friendGroupID.get(), selectedFriendIndex);
        }
    }

    public void moveItemUp() {
        int selectedIndex = friendList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Select a friend to move", ButtonType.OK)
                    .showAndWait();
            return;
        } else if (selectedIndex == 0) return;
        Friend removedFriend = FriendDatabase.removeFriend(friendGroupID.get(), selectedIndex);
        FriendDatabase.insertFriend(removedFriend, friendGroupID.get(), selectedIndex - 1);
        refresh();
        friendList.getSelectionModel().select(selectedIndex - 1);
    }

    public void moveItemDown() {
        int selectedIndex = friendList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Select a friend to move", ButtonType.OK)
                    .showAndWait();
            return;
        } else if (selectedIndex == friendList.getItems().size() - 1) return;
        Friend removedFriend = FriendDatabase.removeFriend(friendGroupID.get(), selectedIndex);
        FriendDatabase.insertFriend(removedFriend, friendGroupID.get(), selectedIndex + 1);
        refresh();
        friendList.getSelectionModel().select(selectedIndex + 1);
    }

    public void selectGroup() {
        if (selectGroup.getSelectionModel().getSelectedItem() == null) return;
        FriendDatabase.changeString("groupID",
                selectGroup.getSelectionModel().getSelectedItem());
        if (friendGroupSize.get() > 0) {
            FriendDatabase.changeInteger("friendIndex", 0);
        }
        refresh();
    }

    public void createGroup() {
        InputBox inputBox = new InputBox(
                "Enter a name for the new group:",
                "New Friend Group");
        inputBox.setHeight(175);
        inputBox.setWidth(300);
        inputBox.show();
        String result = inputBox.getResult();

        if (result.equals("")) return;

        String[] tempArray = new String[groupIDs.get().length + 1];
        for (int i = 0; i < groupIDs.get().length; i++) {
            tempArray[i] = groupIDs.get()[i];
        }
        tempArray[tempArray.length - 1] = result;
        FriendDatabase.changeStringArray("groupIDs", tempArray);
        FriendDatabase.changeString("groupID", result);
        FriendDatabase.newFriendGroup(result);
        refresh();
    }

    public void renameGroup() {
        InputBox inputBox = new InputBox(
                "Enter a new name for this group;",
                "Rename Friend Group");
        inputBox.setHeight(175);
        inputBox.setWidth(300);
        inputBox.setDefaultText(selectGroup.getSelectionModel().getSelectedItem());
        inputBox.show();
        String result = inputBox.getResult();

        if (result.equals("") || result.equals(friendGroupID.get())) return;

        int selectedIndex = selectGroup.getSelectionModel().getSelectedIndex();
        String[] tempArray = new String[groupIDs.get().length];
        for (int i = 0; i < groupIDs.get().length; i++) {
            if (i == selectedIndex) {
                tempArray[i] = result;
            } else {
                tempArray[i] = groupIDs.get()[i];
            }
        }
        FriendDatabase.changeStringArray("groupIDs", tempArray);
        refresh();
    }

    public void deleteGroup() {
        int selectedIndex = selectGroup.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(AlertType.NONE, "Please select a group to remove", ButtonType.OK)
                    .showAndWait();
            return;
        }

        Alert deleteGroup = new Alert(
                AlertType.NONE,
                "Are you sure you want to delete this group?",
                ButtonType.YES, ButtonType.NO);
        deleteGroup.showAndWait();

        if (deleteGroup.getResult() != ButtonType.YES) return;

        selectGroup.getSelectionModel().select(selectedIndex > 0
                ? selectedIndex - 1
                : 1);
        selectGroup.getItems().remove(selectedIndex);

        ArrayList<String> tempArray = new ArrayList<>();
        for (String item : groupIDs.get()) {
            // Check that the current ID isn't the ID to be removed
            if (!item.equals(friendGroupID.get())) {
                tempArray.add(item);
            }
        }
        FriendDatabase.changeStringArray("groupIDs", tempArray.toArray(new String[0]));
        FriendDatabase.removeFriendGroup(friendGroupID.get());
        FriendDatabase.changeString("groupID", groupIDs.get()[selectedIndex > 0
                ? selectedIndex - 1
                : 0]);
    }
}
