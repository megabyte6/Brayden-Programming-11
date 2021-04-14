import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HighScores_Controller {
    @FXML
    private Button button_back;

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

    public void returnHome(ActionEvent actionEvent) {
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        openWindow("Start.fxml", currentStage, "Bingo");
    }
}
