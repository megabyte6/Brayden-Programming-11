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
    @FXML
    private Label player_1_1;
    @FXML
    private Button button_back;

    // Run when Easy_Controller.java is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                player_1_1.setStyle("-fx-background-color: grey");                
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
