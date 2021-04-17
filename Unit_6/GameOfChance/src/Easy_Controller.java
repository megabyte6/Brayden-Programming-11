import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Easy_Controller implements Initializable {
    // Goes [col][row]
    private Node[][] playerCells = new Node[5][5];
    private Node[][] computerCells = new Node[5][5];
    private boolean[][] playerCellStates = new boolean[5][5];
    private boolean[][] computerCellStates = new boolean[5][5];

    @FXML
    private GridPane gridPane_root;
    @FXML
    private GridPane gridPane_player;
    @FXML
    private GridPane gridPane_computer;
    @FXML
    private Button button_back;

    // Run when Easy_Controller.java is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize a multidimensional array for every node on the player's bingo card
        for (Node cell : gridPane_player.getChildren()) {
            System.out.println(cell.toString());
            playerCells[GridPane.getColumnIndex(cell)][GridPane.getRowIndex(cell)] = (Label) cell;
        }

        // Initialize a multidimensional array for every node on the computer's bingo card
        for (Node cell : gridPane_computer.getChildren()) {
            computerCells[GridPane.getColumnIndex(cell)][GridPane.getRowIndex(cell)] = cell;
        }

        // Create array to store states of the player's and computer's cells
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 5; row++) {
                playerCellStates[col][row] = false;
                computerCellStates[col][row] = false;

                playerCells[col][row].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (playerCellStates[1][1] == true) {
                            ((Label) playerCells[col][row]).setStyle("-fx-border-color: black; -fx-background-color: transparent");
                            playerCellStates[1][1] = false;
                        } else {
                            playerCells[col][row].setStyle("-fx-border-color: black; -fx-background-color: lightgrey");
                            playerCellStates[1][1] = true;
                        }
                    }
                });
            }
        }

        // Add event handlers
        // for (int col = 0; col < 5; col++) {
        //     for (int row = 0; row < 5; row++) {
        //         playerCells[col][row].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
        //             @Override
        //             public void handle(MouseEvent mouseEvent) {
        //                 if (playerCellStates[1][1] == true) {
        //                     playerCells[col][row].setStyle("-fx-border-color: black; -fx-background-color: transparent");
        //                     playerCellStates[1][1] = false;
        //                 } else {
        //                     playerCells[col][row].setStyle("-fx-border-color: black; -fx-background-color: lightgrey");
        //                     playerCellStates[1][1] = true;
        //                 }
        //             }
        //         });
        //     }
        // }
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

    public void setFullScreen() {
        Stage currentStage = (Stage) gridPane_root.getScene().getWindow();
        if (currentStage.isFullScreen()) {
            currentStage.setFullScreen(false);
        } else {
            currentStage.setFullScreen(true);
        }
    }

    public void setFullScreen(boolean fullScreen) {
        Stage currentStage = (Stage) gridPane_root.getScene().getWindow();
        currentStage.setFullScreen(fullScreen);
    }

    public void returnHome(ActionEvent actionEvent) {
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        openWindow("Start.fxml", "Bingo", currentStage);
    }
}
