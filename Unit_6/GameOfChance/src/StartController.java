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
    
    // This method is run when the stage is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox_gameMode.getItems().addAll(
                "Easy",
                "Medium",
                "Hard");
        comboBox_gameMode.getSelectionModel().selectFirst();
    }

    // Code for play button
    public void play(ActionEvent actionEvent) throws Exception {
        // Close old window
        Stage currentStage = (Stage) button_play.getScene().getWindow();
        currentStage.close();

        // Try to open new window
        try {
            Stage gameStage = new Stage();
            gameStage.setTitle("Bingo");

            // Check if easy, medium, or hard mode is selected
            if ("Easy".equals(comboBox_gameMode.getValue())) {
                gameStage.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("Easy.fxml"))));
            } else if ("Medium".equals(comboBox_gameMode.getValue())) {
                gameStage.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("Medium.fxml"))));
            } else if ("Hard".equals(comboBox_gameMode.getValue())) {
                gameStage.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("Hard.fxml"))));
            }

            gameStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openInstructions(ActionEvent actionEvent) throws Exception {
        // Close current window
        Stage currentStage = (Stage) button_instructions.getScene().getWindow();
        currentStage.close();

        // Try to open new window
        try {
            Stage instructionStage = new Stage();
            instructionStage.setTitle("Instructions");
            instructionStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Instructions.fxml"))));
            instructionStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
