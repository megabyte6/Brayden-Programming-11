import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Start the main UI
        primaryStage.setTitle("Friend Book");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("layout/FriendBook.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
