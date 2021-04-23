// I know that this is not following typical coding conventions but when I
// tried to use a for loop, the GridPane.getChildren() kept giving me an
// ObservableList of null nodes

import java.util.ArrayList;
import java.util.Random;

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

public class Easy_Controller implements Runnable {
    private Random random = new Random();

    private boolean gameInitialized = false;
    private boolean gamePaused = true;
    public int timerDuration = 10;
    private int turnsTaken = 0;

    // Keeps a list of numbers called to check player's choices later
    private ArrayList<Integer> numbersCalled = new ArrayList<Integer>();

    // Goes [column][row]
    private int[][] playerCellValues = new int[5][5];
    private int[][] computerCellValues = new int[5][5];
    private boolean[][] playerCellStates = new boolean[5][5];
    private boolean[][] computerCellStates = new boolean[5][5];

    // Components in the UI
    @FXML
    Button button_back;
    @FXML
    Button button_overlay;
    @FXML
    AnchorPane anchorPane_root;
    @FXML
    GridPane gridPane_root;

    @FXML
    Label label_number;
    @FXML
    Label label_timer;

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
    private Stage openWindow(String fxmlFile, String titleName, Stage currentStage) {
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

    private void setPause(boolean pause) {
        if (pause) {
            gridPane_root.setDisable(true);
            button_overlay.setDisable(false);
            button_overlay.setVisible(true);
        } else {
            gridPane_root.setDisable(false);
            button_overlay.setDisable(true);
            button_overlay.setVisible(false);
        }
        gamePaused = pause;
    }

    private int randomNum(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    // Generate random values for letter and number
    // Returns a letter and a number separated by a space
    private String generateRandomNumberLetter() {
        String result = "";
        int randomNumber;

        // Generate random number
        // add it to the list of called numbers
        // return the letter-number combo
        switch (random.nextInt(5)) {
            case 0:
                randomNumber = randomNum(1, 20);
                numbersCalled.add(randomNumber);
                result += "B " + randomNumber;
                break;
            case 1:
                randomNumber = randomNum(20, 40);
                numbersCalled.add(randomNumber);
                result += "I " + randomNumber;
                break;
            case 2:
                randomNumber = randomNum(40, 60);
                numbersCalled.add(randomNumber);
                result += "N " + randomNumber;
                break;
            case 3:
                randomNumber = randomNum(60, 80);
                numbersCalled.add(randomNumber);
                result += "G " + randomNumber;
                break;
            case 4:
                randomNumber = randomNum(80, 99);
                numbersCalled.add(randomNumber);
                result += "O " + randomNumber;
                break;
        }

        return result;
    }

    // Returns an int of num of occurrences
    private int existsInArray(int[][] array, int value) {
        int numOfOccurrences = 0;
        for (int[] row : array) {
            for (int item : row) {
                if (item == value) {
                    numOfOccurrences++;
                }
            }
        }
        return numOfOccurrences;
    }



    private void play() {
        // TODO : Add code to play method
        Thread timerThread = new Thread(new Easy_Controller());
        timerThread.start();
    }

    public void run() {
        // Create a seconds variable to count seconds
        int seconds = 0;

        while (!gamePaused) {
            // Try to sleep for 10 seconds
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

            // Add 1 to the number of seconds
            seconds++;

            // Update the time display on the UI
            label_timer.setText("" + seconds);

            // If it has been 10 seconds, generate a new letter and number and
            // set seconds to 0
            if (seconds == timerDuration) {
                label_number.setText(generateRandomNumberLetter());
                seconds = 0;
            }
        }
    }



    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Methods for processing user interactions with the game
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // Initialize game board when user starts the game
    public void overlayFunction() {
        if (!gameInitialized) {
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            // Add an event handler to every cell in the player's bingo card
            // See line 1 for more information
            player_0_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][0] == true) {
                        player_0_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][0] = false;
                    } else {
                        player_0_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][0] = true;
                    }
                }
            });
            player_0_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][1] == true) {
                        player_0_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][1] = false;
                    } else {
                        player_0_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][1] = true;
                    }
                }
            });
            player_0_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][2] == true) {
                        player_0_2.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][2] = false;
                    } else {
                        player_0_2.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][2] = true;
                    }
                }
            });
            player_0_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][3] == true) {
                        player_0_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][3] = false;
                    } else {
                        player_0_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][3] = true;
                    }
                }
            });
            player_0_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[0][4] == true) {
                        player_0_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[0][4] = false;
                    } else {
                        player_0_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[0][4] = true;
                    }
                }
            });
            player_1_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][0] == true) {
                        player_1_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][0] = false;
                    } else {
                        player_1_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][0] = true;
                    }
                }
            });
            player_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][1] == true) {
                        player_1_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][1] = false;
                    } else {
                        player_1_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][1] = true;
                    }
                }
            });
            player_1_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][2] == true) {
                        player_1_2.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][2] = false;
                    } else {
                        player_1_2.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][2] = true;
                    }
                }
            });
            player_1_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][3] == true) {
                        player_1_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][3] = false;
                    } else {
                        player_1_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][3] = true;
                    }
                }
            });
            player_1_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[1][4] == true) {
                        player_1_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[1][4] = false;
                    } else {
                        player_1_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[1][4] = true;
                    }
                }
            });
            player_2_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][0] == true) {
                        player_2_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[2][0] = false;
                    } else {
                        player_2_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[2][0] = true;
                    }
                }
            });
            player_2_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][1] == true) {
                        player_2_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[2][1] = false;
                    } else {
                        player_2_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[2][1] = true;
                    }
                }
            });
            player_2_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][3] == true) {
                        player_2_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[2][3] = false;
                    } else {
                        player_2_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[2][3] = true;
                    }
                }
            });
            player_2_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[2][4] == true) {
                        player_2_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[2][4] = false;
                    } else {
                        player_2_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[2][4] = true;
                    }
                }
            });
            player_3_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][0] == true) {
                        player_3_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][0] = false;
                    } else {
                        player_3_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][0] = true;
                    }
                }
            });
            player_3_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][1] == true) {
                        player_3_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][1] = false;
                    } else {
                        player_3_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][1] = true;
                    }
                }
            });
            player_3_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][2] == true) {
                        player_3_2.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][2] = false;
                    } else {
                        player_3_2.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][2] = true;
                    }
                }
            });
            player_3_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][3] == true) {
                        player_3_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][3] = false;
                    } else {
                        player_3_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][3] = true;
                    }
                }
            });
            player_3_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[3][4] == true) {
                        player_3_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[3][4] = false;
                    } else {
                        player_3_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[3][4] = true;
                    }
                }
            });
            player_4_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][0] == true) {
                        player_4_0.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][0] = false;
                    } else {
                        player_4_0.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][0] = true;
                    }
                }
            });
            player_4_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][1] == true) {
                        player_4_1.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][1] = false;
                    } else {
                        player_4_1.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][1] = true;
                    }
                }
            });
            player_4_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][2] == true) {
                        player_4_2.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][2] = false;
                    } else {
                        player_4_2.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][2] = true;
                    }
                }
            });
            player_4_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][3] == true) {
                        player_4_3.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][3] = false;
                    } else {
                        player_4_3.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][3] = true;
                    }
                }
            });
            player_4_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (playerCellStates[4][4] == true) {
                        player_4_4.setStyle(
                                "-fx-background-color: transparent");
                        playerCellStates[4][4] = false;
                    } else {
                        player_4_4.setStyle(
                                "-fx-background-color: lightgrey");
                        playerCellStates[4][4] = true;
                    }
                }
            });
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

            // Create array to store states of the player's and computer's cells
            // Make sure to not have duplicate numbers
            // Generate random numbers for cards
            // B: 1 - 20
            // I: 20 - 40
            // N: 40 - 60
            // G: 60 - 80
            // O: 80 - 99
            for (int col = 0; col < 5; col++) {
                for (int row = 0; row < 5; row++) {
                    if (col == 0) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(1, 20);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(1, 20);
                        }

                    } else if (col == 1) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(20, 40);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(20, 40);
                        }

                    } else if (col == 2 && row != 2) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(40, 60);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(40, 60);
                        }

                    } else if (col == 3) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(60, 80);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(60, 80);
                        }

                    } else if (col == 4) {
                        while (existsInArray(playerCellValues, playerCellValues[col][row]) != 1) {
                            playerCellValues[col][row] = randomNum(80, 99);
                        }
                        while (existsInArray(computerCellValues, computerCellValues[col][row]) != 1) {
                            computerCellValues[col][row] = randomNum(80, 99);
                        }
                    }

                    if (col == 5 && row == 5) {
                        playerCellStates[col][row] = true;
                        computerCellStates[col][row] = true;
                    } else {
                        playerCellStates[col][row] = false;
                        computerCellStates[col][row] = false;
                    }
                }
            }

            // Randomly generate numbers for player's card
            player_0_0.setText("" + playerCellValues[0][0]);
            player_0_1.setText("" + playerCellValues[0][1]);
            player_0_2.setText("" + playerCellValues[0][2]);
            player_0_3.setText("" + playerCellValues[0][3]);
            player_0_4.setText("" + playerCellValues[0][4]);
            player_1_0.setText("" + playerCellValues[1][0]);
            player_1_1.setText("" + playerCellValues[1][1]);
            player_1_2.setText("" + playerCellValues[1][2]);
            player_1_3.setText("" + playerCellValues[1][3]);
            player_1_4.setText("" + playerCellValues[1][4]);
            player_2_0.setText("" + playerCellValues[2][0]);
            player_2_1.setText("" + playerCellValues[2][1]);
            player_2_3.setText("" + playerCellValues[2][3]);
            player_2_4.setText("" + playerCellValues[2][4]);
            player_3_0.setText("" + playerCellValues[3][0]);
            player_3_1.setText("" + playerCellValues[3][1]);
            player_3_2.setText("" + playerCellValues[3][2]);
            player_3_3.setText("" + playerCellValues[3][3]);
            player_3_4.setText("" + playerCellValues[3][4]);
            player_4_0.setText("" + playerCellValues[4][0]);
            player_4_1.setText("" + playerCellValues[4][1]);
            player_4_2.setText("" + playerCellValues[4][2]);
            player_4_3.setText("" + playerCellValues[4][3]);
            player_4_4.setText("" + playerCellValues[4][4]);

            // Randomly generate numbers for computer's card
            computer_0_0.setText("" + computerCellValues[0][0]);
            computer_0_1.setText("" + computerCellValues[0][1]);
            computer_0_2.setText("" + computerCellValues[0][2]);
            computer_0_3.setText("" + computerCellValues[0][3]);
            computer_0_4.setText("" + computerCellValues[0][4]);
            computer_1_0.setText("" + computerCellValues[1][0]);
            computer_1_1.setText("" + computerCellValues[1][1]);
            computer_1_2.setText("" + computerCellValues[1][2]);
            computer_1_3.setText("" + computerCellValues[1][3]);
            computer_1_4.setText("" + computerCellValues[1][4]);
            computer_2_0.setText("" + computerCellValues[2][0]);
            computer_2_1.setText("" + computerCellValues[2][1]);
            computer_2_3.setText("" + computerCellValues[2][3]);
            computer_2_4.setText("" + computerCellValues[2][4]);
            computer_3_0.setText("" + computerCellValues[3][0]);
            computer_3_1.setText("" + computerCellValues[3][1]);
            computer_3_2.setText("" + computerCellValues[3][2]);
            computer_3_3.setText("" + computerCellValues[3][3]);
            computer_3_4.setText("" + computerCellValues[3][4]);
            computer_4_0.setText("" + computerCellValues[4][0]);
            computer_4_1.setText("" + computerCellValues[4][1]);
            computer_4_2.setText("" + computerCellValues[4][2]);
            computer_4_3.setText("" + computerCellValues[4][3]);
            computer_4_4.setText("" + computerCellValues[4][4]);

            // Randomly generate a letter and a number
            label_number.setText(generateRandomNumberLetter());

            // Remove overlay and enable the UI
            setPause(false);

            gameInitialized = true;
            play();
        }
    }

    // This is called when the user clicks the back button
    public void returnHome(ActionEvent actionEvent) {
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        openWindow("Start.fxml", "Bingo", currentStage);
    }
}
