import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Start_Controller implements Initializable {
    // Variables represent elements in the UI
    @FXML
    AnchorPane anchorPane_root;
    @FXML
    ComboBox<String> comboBox_gameMode;
    @FXML
    ComboBox<String> comboBox_timerLength;
    @FXML
    Button button_play;
    @FXML
    Button button_instructions;
    
    // This method is run when the stage is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add items to the ComboBox for different game difficulties and select the first one
        comboBox_gameMode.getItems().addAll(
                "Easy",
                "Medium",
                "Hard");
        comboBox_gameMode.getSelectionModel().selectFirst();

        // Add items to the ComboBox for different timer lengths and select the second item
        comboBox_timerLength.getItems().addAll(
                "3",
                "5",
                "10");
        comboBox_timerLength.getSelectionModel().selectLast();
    }

    // Use to open a new window
    private Stage openWindow(String fxmlFile, String titleName, Stage currentStage) {
        // Close old window
        currentStage.close();

        // Create a new stage
        Stage newStage = new Stage();
        newStage.setTitle(titleName);

        // Try to get fxml file for UI
        try {
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlFile))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Show the new stage
        newStage.show();

        return newStage;
    }

    // Process key events in the root container
    public void processKey(KeyEvent key) {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();

        // Full screen window when F11 is pressed
        if (key.getCode() == KeyCode.F11) {
            if (currentStage.isFullScreen()) {
                currentStage.setFullScreen(false);
            } else {
                currentStage.setFullScreen(true);
            }
        }
    }

    // Toggle full screen state
    public void setFullScreen() {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        if (currentStage.isFullScreen()) {
            currentStage.setFullScreen(false);
        } else {
            currentStage.setFullScreen(true);
        }
    }

    // Set full screen state
    public void setFullScreen(boolean fullScreen) {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        currentStage.setFullScreen(fullScreen);
        DataStore.addBoolean("fullScreen", fullScreen);
    }

    // Code for play button
    public void play(ActionEvent actionEvent) throws Exception {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        
        // Store the timer length selected
        DataStore.addInteger("timerDuration",
                Integer.parseInt(comboBox_timerLength.getValue()));

        // Store the full screen state of the application
        DataStore.addBoolean("fullScreenState", currentStage.isFullScreen());

        // Check if easy, medium, or hard mode is selected
        if ("Easy".equals(comboBox_gameMode.getValue())) {
            openWindow("Easy.fxml", "Bingo", currentStage);
        } else if ("Medium".equals(comboBox_gameMode.getValue())) {
            openWindow("Medium.fxml", "Bingo", currentStage);
        } else if ("Hard".equals(comboBox_gameMode.getValue())) {
            openWindow("Hard.fxml", "Bingo", currentStage);
        }
    }

    // Open the instructions window
    public void openInstructions(ActionEvent actionEvent) throws Exception {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        Stage newStage = openWindow("Instructions.fxml", "Instructions", currentStage);
        if (currentStage.isFullScreen()) {
            newStage.setFullScreen(true);
        }
    }
}
