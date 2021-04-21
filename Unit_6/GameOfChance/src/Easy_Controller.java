// I know that this is not following typical coding conventions but when I
// tried to use a for loop, the GridPane.getChildren() kept giving me an
// ObservableList of null nodes

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Easy_Controller {
    private boolean gameInitialized = false;
    public int timerDuration = 10;
    // Goes [column][row]
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

    // All of the cells in the GridPane
    // See line 1 for more information
    @FXML
    Label player_0_0;
    @FXML
    Label player_0_1;
    @FXML
    Label player_0_2;
    @FXML
    Label player_0_3;
    @FXML
    Label player_0_4;
    @FXML
    Label player_1_0;
    @FXML
    Label player_1_1;
    @FXML
    Label player_1_2;
    @FXML
    Label player_1_3;
    @FXML
    Label player_1_4;
    @FXML
    Label player_2_0;
    @FXML
    Label player_2_1;
    @FXML
    Label player_free;
    @FXML
    Label player_2_3;
    @FXML
    Label player_2_4;
    @FXML
    Label player_3_0;
    @FXML
    Label player_3_1;
    @FXML
    Label player_3_2;
    @FXML
    Label player_3_3;
    @FXML
    Label player_3_4;
    @FXML
    Label player_4_0;
    @FXML
    Label player_4_1;
    @FXML
    Label player_4_2;
    @FXML
    Label player_4_3;
    @FXML
    Label player_4_4;

    @FXML
    Label computer_0_0;
    @FXML
    Label computer_0_1;
    @FXML
    Label computer_0_2;
    @FXML
    Label computer_0_3;
    @FXML
    Label computer_0_4;
    @FXML
    Label computer_1_0;
    @FXML
    Label computer_1_1;
    @FXML
    Label computer_1_2;
    @FXML
    Label computer_1_3;
    @FXML
    Label computer_1_4;
    @FXML
    Label computer_2_0;
    @FXML
    Label computer_2_1;
    @FXML
    Label computer_free;
    @FXML
    Label computer_2_3;
    @FXML
    Label computer_2_4;
    @FXML
    Label computer_3_0;
    @FXML
    Label computer_3_1;
    @FXML
    Label computer_3_2;
    @FXML
    Label computer_3_3;
    @FXML
    Label computer_3_4;
    @FXML
    Label computer_4_0;
    @FXML
    Label computer_4_1;
    @FXML
    Label computer_4_2;
    @FXML
    Label computer_4_3;
    @FXML
    Label computer_4_4;

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

    // Set the timer duration
    public void setTimerDuration(int duration) {
        this.timerDuration = duration;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Methods for processing user interactions with the game
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // Initialize game board when user starts the game
    public void overlayFunction() {
        if (!gameInitialized) {
            // Create array to store states of the player's and computer's cells
            for (int col = 0; col < 5; col++) {
                for (int row = 0; row < 5; col++) {
                    if (col == 5 && row == 5) {
                        playerCellStates[col][row] = true;
                        computerCellStates[col][row] = true;
                    } else {
                        playerCellStates[col][row] = false;
                        computerCellStates[col][row] = false;
                    }
                }
            }

            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            // Add an event handler to every cell in the player's bingo card
            // See line 1 for more information
            player_0_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][0] == true) {
                        player_0_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[0][0] = false;
                    } else {
                        player_0_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[0][0] = true;
                    }
                }
            });
            player_0_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][1] == true) {
                        player_0_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[0][1] = false;
                    } else {
                        player_0_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[0][1] = true;
                    }
                }
            });
            player_0_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][2] == true) {
                        player_0_2.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[0][2] = false;
                    } else {
                        player_0_2.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[0][2] = true;
                    }
                }
            });
            player_0_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][3] == true) {
                        player_0_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[0][3] = false;
                    } else {
                        player_0_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[0][3] = true;
                    }
                }
            });
            player_0_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][4] == true) {
                        player_0_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[0][4] = false;
                    } else {
                        player_0_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[0][4] = true;
                    }
                }
            });
            player_1_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][0] == true) {
                        player_1_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[1][0] = false;
                    } else {
                        player_1_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[1][0] = true;
                    }
                }
            });
            player_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][1] == true) {
                        player_1_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[1][1] = false;
                    } else {
                        player_1_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[1][1] = true;
                    }
                }
            });
            player_1_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][2] == true) {
                        player_1_2.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[1][2] = false;
                    } else {
                        player_1_2.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[1][2] = true;
                    }
                }
            });
            player_1_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][3] == true) {
                        player_1_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[1][3] = false;
                    } else {
                        player_1_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[1][3] = true;
                    }
                }
            });
            player_1_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][4] == true) {
                        player_1_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[1][4] = false;
                    } else {
                        player_1_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[1][4] = true;
                    }
                }
            });
            player_2_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][0] == true) {
                        player_2_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[2][0] = false;
                    } else {
                        player_2_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[2][0] = true;
                    }
                }
            });
            player_2_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][1] == true) {
                        player_2_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[2][1] = false;
                    } else {
                        player_2_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[2][1] = true;
                    }
                }
            });
            player_2_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][3] == true) {
                        player_2_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[2][3] = false;
                    } else {
                        player_2_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[2][3] = true;
                    }
                }
            });
            player_2_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][4] == true) {
                        player_2_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[2][4] = false;
                    } else {
                        player_2_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[2][4] = true;
                    }
                }
            });
            player_3_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][0] == true) {
                        player_3_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[3][0] = false;
                    } else {
                        player_3_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[3][0] = true;
                    }
                }
            });
            player_3_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][1] == true) {
                        player_3_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[3][1] = false;
                    } else {
                        player_3_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[3][1] = true;
                    }
                }
            });
            player_3_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][2] == true) {
                        player_3_2.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[3][2] = false;
                    } else {
                        player_3_2.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[3][2] = true;
                    }
                }
            });
            player_3_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][3] == true) {
                        player_3_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[3][3] = false;
                    } else {
                        player_3_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[3][3] = true;
                    }
                }
            });
            player_3_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][4] == true) {
                        player_3_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[3][4] = false;
                    } else {
                        player_3_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[3][4] = true;
                    }
                }
            });
            player_4_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][0] == true) {
                        player_4_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[4][0] = false;
                    } else {
                        player_4_0.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[4][0] = true;
                    }
                }
            });
            player_4_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][1] == true) {
                        player_4_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[4][1] = false;
                    } else {
                        player_4_1.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[4][1] = true;
                    }
                }
            });
            player_4_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][2] == true) {
                        player_4_2.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[4][2] = false;
                    } else {
                        player_4_2.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[4][2] = true;
                    }
                }
            });
            player_4_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][3] == true) {
                        player_4_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[4][3] = false;
                    } else {
                        player_4_3.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[4][3] = true;
                    }
                }
            });
            player_4_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][4] == true) {
                        player_4_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: transparent");
                        playerCellStates[4][4] = false;
                    } else {
                        player_4_4.setStyle(
                                "-fx-border-color: black; -fx-background-color: lightgrey");
                        playerCellStates[4][4] = true;
                    }
                }
            });
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

            /*
            for (int col = 0; col < 5; col++) {
                for (int row = 0; row < 5; row++) {
                    playerCells[col][row] = getNodeByRowColumnIndex(row, col, gridPane_player);
                }
            }

            // Initialize a multidimensional array for every node on the player's bingo card
            for (Node cell : gridPane_player.getChildren()) {
                System.out.println(cell.toString());
                System.out.println(GridPane.getColumnIndex(cell));
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
*/
            gameInitialized = false;
        }
    }

    // This is called when the user clicks the back button
    public void returnHome(ActionEvent actionEvent) {
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        openWindow("Start.fxml", "Bingo", currentStage);
    }
}
