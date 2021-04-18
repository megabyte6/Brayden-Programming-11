import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Easy_Controller {
    // Goes [column][row]
    private Node[][] playerCells = new Node[5][5];
    private Node[][] computerCells = new Node[5][5];
    private boolean[][] playerCellStates = new boolean[5][5];
    private boolean[][] computerCellStates = new boolean[5][5];

    // Components in the UI
    @FXML
    AnchorPane anchorPane_root;
    @FXML
    GridPane gridPane_player;
    @FXML
    GridPane gridPane_computer;
    @FXML
    Button button_back;
    @FXML
    Button button_overlay;

    // Use to open a new window
    public Stage openWindow(String fxmlFile, String titleName, Stage currentStage) {
        // Close old window
        currentStage.close();

        // Create a new stage
        Stage newStage = new Stage();
        newStage.setTitle(titleName);
        // Try to get fxml file for UI
        try {
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlFile))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        newStage.show();
        return newStage;
    }

    // Toggle full screen state
    public void setFullScreen() {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        if (currentStage.isFullScreen()) {
            currentStage.setFullScreen(false);
        } else {
            currentStage.setFullScreen(true);
        }
    }

    // Set full screen state
    public void setFullScreen(boolean fullScreen) {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
        currentStage.setFullScreen(fullScreen);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Methods for processing user interactions with the game
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // Initialize game board when user starts the game
    public void initializeGameBoard() {
        // Initialize a multidimensional array for every node on the player's bingo card
        for (Node cell : gridPane_player.getChildren()) {
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
            }
        }

        // Add event handlers for each cell
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 5; row++) {
                // Create final variables for the event handler
                final int tempCol = col;
                final int tempRow = row;

                playerCells[col][row].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (playerCellStates[1][1] == true) {
                            ((Label) playerCells[tempCol][tempRow]).setStyle(
                                    "-fx-border-color: black; -fx-background-color: transparent");
                            playerCellStates[1][1] = false;
                        } else {
                            playerCells[tempCol][tempRow].setStyle(
                                    "-fx-border-color: black; -fx-background-color: lightgrey");
                            playerCellStates[1][1] = true;
                        }
                    }
                });
            }
        }
    }

    // This is called when the user clicks the back button
    public void returnHome(ActionEvent actionEvent) {
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        openWindow("Start.fxml", "Bingo", currentStage);
    }
}
