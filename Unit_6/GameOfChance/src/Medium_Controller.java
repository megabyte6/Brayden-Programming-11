import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Medium_Controller {
    @FXML
    private Button button_back;

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

    public void returnHome(ActionEvent actionEvent) {
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        openWindow("Start.fxml", "Bingo", currentStage);
    }
}
