import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EditFriend implements Initializable {

    private final String BORDER_CLEAR = "-fx-border-color: transparent";
    private final String BORDER_RED = "-fx-border-color: red";

    private int friendIndex = FriendDatabase.getInteger("friendIndex");

    @FXML
    private GridPane gridPane_root;
    @FXML
    private Button button_save;

    @FXML
    private TextField textField_firstName;
    @FXML
    private TextField textField_lastName;
    @FXML
    private TextField textField_birthday_year;
    @FXML
    private ComboBox<String> comboBox_birthday_month;
    @FXML
    private ComboBox<String> comboBox_birthday_day;
    @FXML
    private TextField textField_height;
    @FXML
    private ComboBox<String> comboBox_height_unit;
    @FXML
    private ComboBox<String> comboBox_gender;
    @FXML
    private TextField textField_otherInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate Combobox with items

        // Populate month Combobox with months
        comboBox_birthday_month.getItems().addAll("January", "Febuary", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        // Populate day Combobox with days
        comboBox_birthday_day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31");

        // Populate height units Combobox with options
        comboBox_height_unit.getItems().addAll("Centimeters", "Inches");
        comboBox_birthday_day.getSelectionModel().selectFirst();

        // Populate gender Combobox with items
        comboBox_gender.getItems().addAll("Male", "Female");
        // Make the gender Combobox editable for people who don't define
        // themselves as one of the two primary genders
        comboBox_gender.setEditable(true);

        // Check if the friend already exists
        if (friendIndex != -1) {
            // Edit friend instead of creating one

            Friend friend = FriendDatabase.getFriend(friendIndex);

            // Set the name
            textField_firstName.setText(friend.getFirstName());
            textField_lastName.setText(friend.getLastName());

            // Set the birthday
            textField_birthday_year.setText(friend.getBirthYear() == 0 ? "" : String.valueOf(friend.getBirthYear()));
            comboBox_birthday_month.getSelectionModel().select(friend.getBirthMonth() - 1);
            comboBox_birthday_day.getSelectionModel().select(friend.getBirthDay() - 1);

            // Set the height
            if (friend.getHeight() != 0.0) {
                textField_height.setText(String.valueOf(friend.getHeight()));
                comboBox_height_unit.getSelectionModel().select((friend.getHeightUnit()).equals("cm") ? 0 : 1);
            }

            // Set the gender
            switch (friend.getGender()) {
                case "Male":
                    comboBox_gender.getSelectionModel().selectFirst();
                    break;
                case "Female":
                    comboBox_gender.getSelectionModel().selectLast();
                    break;
                default:
                    comboBox_gender.setValue(friend.getGender());
            }

            // Set other info
            textField_otherInfo.setText(friend.getOtherFeatures());
        }
    }

    public void saveFriend() {
        // Add values to FriendDatabase to keep track of which input boxes were
        // filled out
        FriendDatabase.setBoolean("FirstName", false);
        FriendDatabase.setBoolean("LastName", false);
        FriendDatabase.setBoolean("BirthDate", false);
        FriendDatabase.setBoolean("Height", false);
        FriendDatabase.setBoolean("Gender", false);
        FriendDatabase.setBoolean("OtherInfo", false);

        // Make sure that the required name fields aren't empty
        if (!(textField_firstName.getText()).equals("") && !(textField_lastName.getText()).equals("")) {

            FriendDatabase.changeBoolean("FirstName", true);
            FriendDatabase.changeBoolean("LastName", true);
            textField_firstName.setStyle(BORDER_CLEAR);
            textField_lastName.setStyle(BORDER_CLEAR);

            // Check if birthday information was given
            if (!(textField_birthday_year.getText()).equals("")
                    && !comboBox_birthday_month.getSelectionModel().isEmpty()
                    && !comboBox_birthday_day.getSelectionModel().isEmpty()) {

                FriendDatabase.changeBoolean("BirthDate", true);
                textField_birthday_year.setStyle(BORDER_CLEAR);

                // Check if the year given is an integer
                try {
                    Integer.parseInt(textField_birthday_year.getText());
                } catch (NumberFormatException e) {
                    FriendDatabase.changeBoolean("BirthDate", false);
                    textField_birthday_year.setStyle(BORDER_RED);
                }
            } else {
                FriendDatabase.changeBoolean("BirthDate", false);
            }

            // Check if a height was given
            if (!(textField_height.getText()).equals("")) {
                FriendDatabase.changeBoolean("Height", true);
                textField_height.setStyle(BORDER_CLEAR);

                // Check if the height given is a double
                try {
                    Double.parseDouble(textField_height.getText());
                } catch (NumberFormatException e) {
                    FriendDatabase.changeBoolean("Height", false);
                    textField_height.setStyle(BORDER_RED);
                }
            } else {
                FriendDatabase.changeBoolean("Height", false);
            }

            // Check if the gender was given
            if (comboBox_gender.getSelectionModel().getSelectedItem() != null
                    && !(comboBox_gender.getSelectionModel().getSelectedItem()).equals("")) {
                FriendDatabase.changeBoolean("Gender", true);
            } else {
                FriendDatabase.changeBoolean("Gender", false);
            }

            // Check if other info was given
            if (!(textField_otherInfo.getText()).equals("")) {
                FriendDatabase.changeBoolean("OtherInfo", true);
            } else {
                FriendDatabase.changeBoolean("OtherInfo", false);
            }

        } else {
            if ((textField_firstName.getText()).equals("")) {
                FriendDatabase.changeBoolean("FirstName", false);
                textField_firstName.setStyle(BORDER_RED);
            }
            if ((textField_lastName.getText()).equals("")) {
                FriendDatabase.changeBoolean("LastName", false);
                textField_lastName.setStyle(BORDER_RED);
            }
        }

        // Only submit the info if it is valid
        if (FriendDatabase.getBoolean("FirstName") == true
                && FriendDatabase.getBoolean("LastName") == true) {
            // Create a new Friend object
            Friend friend = new Friend(textField_firstName.getText(),
                    textField_lastName.getText());

            // Check if birthday information was given
            if (FriendDatabase.getBoolean("BirthDate") == true) {
                friend.setBirthDate(Integer.parseInt(textField_birthday_year.getText()),
                        comboBox_birthday_month.getSelectionModel().getSelectedIndex() + 1,
                        comboBox_birthday_day.getSelectionModel().getSelectedIndex() + 1);
            }

            // Check if height information was given
            if (FriendDatabase.getBoolean("Height") == true) {
                friend.setHeight(Integer.parseInt(textField_height.getText()),
                        comboBox_height_unit.getSelectionModel().getSelectedIndex() == 0
                        ? "cm"
                        : "in");
            }

            // Check if gender information was given
            if (FriendDatabase.getBoolean("Gender") == true) {
                friend.setGender(comboBox_gender.getSelectionModel().getSelectedItem());
            }

            // Check if other information was given
            if (FriendDatabase.getBoolean("OtherInfo") == true) {
                friend.setOtherFeatures(textField_otherInfo.getText());
            }

            // Add Friend to the array of Friends
            if (friendIndex == -1) {
                FriendDatabase.addFriend(friend);
            } else {
                FriendDatabase.replaceFriend(friend, friendIndex);
            }
        }
    }

}
