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

public class MainController implements Initializable {
    @FXML
    private ComboBox<String> comboBox_gameMode;
    @FXML
    private Button button_play;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox_gameMode.getItems().addAll(
                "Single Player",
                "Multiplayer");
    }

    public void play(ActionEvent actionEvent) throws Exception {
        // Close old window
        Stage oldStage = (Stage) button_play.getScene().getWindow();
        oldStage.close();

        // Try to open new window
        try {
            Stage gameStage = new Stage();
            gameStage.setTitle("Guess the Number");

            // Check if single or multiplayer mode is selected
            if ("Single Player".equals(comboBox_gameMode.getValue())) {
                gameStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("SinglePlayer.fxml"))));
            } else if ("Multiplayer".equals(comboBox_gameMode.getValue())) {
                gameStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Multiplayer.fxml"))));
            }
            gameStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
