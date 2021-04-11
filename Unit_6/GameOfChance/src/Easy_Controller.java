import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Easy_Controller implements Initializable {
    boolean[][] playerCellStates = new boolean[5][5];

    @FXML
    private Label player_1_1;
    @FXML
    private Button button_back;

    // Run when Easy_Controller.java is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create array to store states of cells
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                playerCellStates[row][col] = false;
            }
        }
        // Add event handlers
        player_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                if (playerCellStates[1][1] == true) {
                    player_1_1.setStyle("-fx-border-color: black; -fx-background-color: transparent");
                    playerCellStates[1][1] = false;
                } else {
                    player_1_1.setStyle("-fx-border-color: black; -fx-background-color: lightgrey");
                    playerCellStates[1][1] = true;
                }
            }
        });
    }

    public void returnHome(ActionEvent actionEvent) {
        // Close current window
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        currentStage.close();

        // Try to open new window
        try {
            Stage homeStage = new Stage();
            homeStage.setTitle("Bingo");
            homeStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Start.fxml"))));
            homeStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
