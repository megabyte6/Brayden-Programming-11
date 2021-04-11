package GameUIs.Easy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Easy_Controller {
    @FXML
    private Button button_back;

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
