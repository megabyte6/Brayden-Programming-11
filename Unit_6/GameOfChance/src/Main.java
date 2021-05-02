import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the default difficulty and timer settings
        DataStore.addInteger("defaultDifficulty", 0);
        DataStore.addInteger("defaultTimerLength", 2);

        primaryStage.setTitle("Bingo");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Start.fxml"))));
        primaryStage.show();
    }
}
