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

public class StartController implements Initializable {
    // Variables represent elements in the UI
    @FXML
    private AnchorPane anchorPane_root;
    @FXML
    private ComboBox<String> comboBox_gameMode;
    @FXML
    private Button button_play;
    @FXML
    private Button button_instructions;
    @FXML
    private Button button_scoreBoard;
    
    // This method is run when the stage is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox_gameMode.getItems().addAll(
                "Easy",
                "Medium",
                "Hard");
        comboBox_gameMode.getSelectionModel().selectFirst();
    }

    public Stage openWindow(String fxmlFile, String titleName) {
        // Try to open new window
        Stage newStage = new Stage();
        newStage.setTitle(titleName);
        try {
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlFile))));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newStage;
    }

    public Stage openWindow(String fxmlFile, String titleName, Stage currentStage) {        
        // Close old window
        currentStage.close();

        // Try to open new window
        Stage newStage = new Stage();
        newStage.setTitle(titleName);
        try {
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlFile))));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newStage;
    }

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

    public void setFullScreen() {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        if (currentStage.isFullScreen()) {
            currentStage.setFullScreen(false);
        } else {
            currentStage.setFullScreen(true);
        }
    }

    public void setFullScreen(boolean fullScreen) {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        currentStage.setFullScreen(fullScreen);
    }

    // Code for play button
    public void play(ActionEvent actionEvent) throws Exception {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();

        // Check if easy, medium, or hard mode is selected
        if ("Easy".equals(comboBox_gameMode.getValue())) {
            openWindow("Easy.fxml", "Bingo", currentStage);
        } else if ("Medium".equals(comboBox_gameMode.getValue())) {
            openWindow("Medium.fxml", "Bingo", currentStage);
        } else if ("Hard".equals(comboBox_gameMode.getValue())) {
            openWindow("Hard.fxml", "Bingo", currentStage);
        }
    }

    public void openInstructions(ActionEvent actionEvent) throws Exception {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        Stage newStage = openWindow("Instructions.fxml", "Instructions", currentStage);
        if (currentStage.isFullScreen()) {
            newStage.setFullScreen(true);
        }
    }

    public void openScoreBoard(ActionEvent actionEvent) throws Exception {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        openWindow("HighScores.fxml", "Score Board", currentStage);
    }
}
