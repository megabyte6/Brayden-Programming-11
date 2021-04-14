import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class StartController implements Initializable {
    // Variables represent elements in the UI
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

    // Returns 0 if completed successfully else returns 1
    public int openWindow(String fxmlFile, Stage currentStage, String titleName) {
        // Create variable for returning error number
        int errorNum = 0;
        
        // Close old window
        currentStage.close();

        // Try to open new window
        try {
            Stage newStage = new Stage();
            newStage.setTitle(titleName);
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlFile))));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            errorNum = 1;
        }
        return errorNum;
    }

    // Code for play button
    public void play(ActionEvent actionEvent) throws Exception {
        Stage currentStage = (Stage) button_play.getScene().getWindow();

        // Check if easy, medium, or hard mode is selected
        if ("Easy".equals(comboBox_gameMode.getValue())) {
            openWindow("Easy.fxml", currentStage, "Bingo");
        } else if ("Medium".equals(comboBox_gameMode.getValue())) {
            openWindow("Medium.fxml", currentStage, "Bingo");
        } else if ("Hard".equals(comboBox_gameMode.getValue())) {
            openWindow("Hard.fxml", currentStage, "Bingo");
        }
    }

    public void openInstructions(ActionEvent actionEvent) throws Exception {
        Stage currentStage = (Stage) button_instructions.getScene().getWindow();
        openWindow("Instructions.fxml", currentStage, "Instructions");
    }

    public void openScoreBoard(ActionEvent actionEvent) throws Exception {
        Stage currentStage = (Stage) button_scoreBoard.getScene().getWindow();
        openWindow("HighScores.fxml", currentStage, "Score Board");
    }
}
