// I know that this is not following typical coding conventions but when I
// tried to use a for loop, the GridPane.getChildren() kept giving me an
// ObservableList of null nodes

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Medium_Controller {
    // Set to true for debugging info
    private Random random = new Random();

    private boolean gameInitialized = false;
    private boolean gamePaused = true;
    private int timerDuration = DataStore.getInteger("timerDuration");

    private boolean autopilotMode = false;
    
    // Record the state of the game
    //  1: Player won
    //  0: No winner yet
    // -1: Player lost
    private int gameState = 0;

    // Variable to count seconds
    private int seconds = 0;

    // Keeps a list of numbers called to check player's choices later
    // store as an ArrayList of Strings for comparisons later
    // Format: "B 10"
    private ArrayList<String> numbersCalled = new ArrayList<String>();

    // Goes [column][row]
    private int[][] player1CellValues = new int[5][5];
    private int[][] player2CellValues = new int[5][5];
    private int[][] computer1CellValues = new int[5][5];
    private int[][] computer2CellValues = new int[5][5];
    private boolean[][] player1CellStates = new boolean[5][5];
    private boolean[][] player2CellStates = new boolean[5][5];
    private boolean[][] computer1CellStates = new boolean[5][5];
    private boolean[][] computer2CellStates = new boolean[5][5];

    // Components in the UI
    @FXML
    AnchorPane anchorPane_root;
    @FXML
    GridPane gridPane_root;

    @FXML
    Button button_back;
    @FXML
    Button button_fullScreen;
    @FXML
    Button button_overlay;
    @FXML
    Button button_pause;
    @FXML
    Button button_bingo;

    @FXML
    Label label_number;
    @FXML
    Label label_timer;

    // All of the cells in the GridPane
    // See line 1 for more information
    @FXML
    Label player1_0_0;
    @FXML
    Label player1_0_1;
    @FXML
    Label player1_0_2;
    @FXML
    Label player1_0_3;
    @FXML
    Label player1_0_4;
    @FXML
    Label player1_1_0;
    @FXML
    Label player1_1_1;
    @FXML
    Label player1_1_2;
    @FXML
    Label player1_1_3;
    @FXML
    Label player1_1_4;
    @FXML
    Label player1_2_0;
    @FXML
    Label player1_2_1;
    @FXML
    Label player1_free;
    @FXML
    Label player1_2_3;
    @FXML
    Label player1_2_4;
    @FXML
    Label player1_3_0;
    @FXML
    Label player1_3_1;
    @FXML
    Label player1_3_2;
    @FXML
    Label player1_3_3;
    @FXML
    Label player1_3_4;
    @FXML
    Label player1_4_0;
    @FXML
    Label player1_4_1;
    @FXML
    Label player1_4_2;
    @FXML
    Label player1_4_3;
    @FXML
    Label player1_4_4;

    @FXML
    Label player2_0_0;
    @FXML
    Label player2_0_1;
    @FXML
    Label player2_0_2;
    @FXML
    Label player2_0_3;
    @FXML
    Label player2_0_4;
    @FXML
    Label player2_1_0;
    @FXML
    Label player2_1_1;
    @FXML
    Label player2_1_2;
    @FXML
    Label player2_1_3;
    @FXML
    Label player2_1_4;
    @FXML
    Label player2_2_0;
    @FXML
    Label player2_2_1;
    @FXML
    Label player2_free;
    @FXML
    Label player2_2_3;
    @FXML
    Label player2_2_4;
    @FXML
    Label player2_3_0;
    @FXML
    Label player2_3_1;
    @FXML
    Label player2_3_2;
    @FXML
    Label player2_3_3;
    @FXML
    Label player2_3_4;
    @FXML
    Label player2_4_0;
    @FXML
    Label player2_4_1;
    @FXML
    Label player2_4_2;
    @FXML
    Label player2_4_3;
    @FXML
    Label player2_4_4;

    @FXML
    Label computer1_0_0;
    @FXML
    Label computer1_0_1;
    @FXML
    Label computer1_0_2;
    @FXML
    Label computer1_0_3;
    @FXML
    Label computer1_0_4;
    @FXML
    Label computer1_1_0;
    @FXML
    Label computer1_1_1;
    @FXML
    Label computer1_1_2;
    @FXML
    Label computer1_1_3;
    @FXML
    Label computer1_1_4;
    @FXML
    Label computer1_2_0;
    @FXML
    Label computer1_2_1;
    @FXML
    Label computer1_free;
    @FXML
    Label computer1_2_3;
    @FXML
    Label computer1_2_4;
    @FXML
    Label computer1_3_0;
    @FXML
    Label computer1_3_1;
    @FXML
    Label computer1_3_2;
    @FXML
    Label computer1_3_3;
    @FXML
    Label computer1_3_4;
    @FXML
    Label computer1_4_0;
    @FXML
    Label computer1_4_1;
    @FXML
    Label computer1_4_2;
    @FXML
    Label computer1_4_3;
    @FXML
    Label computer1_4_4;

    @FXML
    Label computer2_0_0;
    @FXML
    Label computer2_0_1;
    @FXML
    Label computer2_0_2;
    @FXML
    Label computer2_0_3;
    @FXML
    Label computer2_0_4;
    @FXML
    Label computer2_1_0;
    @FXML
    Label computer2_1_1;
    @FXML
    Label computer2_1_2;
    @FXML
    Label computer2_1_3;
    @FXML
    Label computer2_1_4;
    @FXML
    Label computer2_2_0;
    @FXML
    Label computer2_2_1;
    @FXML
    Label computer2_free;
    @FXML
    Label computer2_2_3;
    @FXML
    Label computer2_2_4;
    @FXML
    Label computer2_3_0;
    @FXML
    Label computer2_3_1;
    @FXML
    Label computer2_3_2;
    @FXML
    Label computer2_3_3;
    @FXML
    Label computer2_3_4;
    @FXML
    Label computer2_4_0;
    @FXML
    Label computer2_4_1;
    @FXML
    Label computer2_4_2;
    @FXML
    Label computer2_4_3;
    @FXML
    Label computer2_4_4;

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

        // Show the new stage
        newStage.show();

        return newStage;
    }

    private void setPause(boolean pause) {
        if (pause) {
            // Disable the game UI
            gridPane_root.setDisable(true);

            // Enable overylay
            button_overlay.setDisable(false);
            button_overlay.setVisible(true);

            // Bring the overlay to the front
            button_overlay.toFront();

            // Change the font size of the overlay
            button_overlay.setStyle("-fx-font-size: 48");

        } else {
            // Disable the game UI
            gridPane_root.setDisable(false);

            // Enable overylay
            button_overlay.setDisable(true);
            button_overlay.setVisible(false);

            // Bring the overlay to the front
            button_overlay.toBack();

        }
        
        // Set the opacity
        button_overlay.setOpacity(1);

        // Change the font size of the overlay
        button_overlay.setStyle("-fx-font-size: 48");

        this.gamePaused = pause;
    }

    private int randomNum(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    // Generate random values for letter and number
    // Returns a letter and a number separated by a space
    private String generateRandomNumberLetter() {
        String result = "";
        String letter;
        int randomNumber = 0;

        // Loop until new number
        do {
            // Set result to "" at the beginning of each loop
            result = "";

            // Generate random number
            // add it to the list of called numbers
            // return the letter-number combo
            switch (random.nextInt(5)) {
                case 0:
                    letter = "B ";
                    randomNumber = randomNum(1, 20);
                    result += letter + randomNumber;
                    break;
                case 1:
                    letter = "I ";
                    randomNumber = randomNum(20, 40);
                    result += letter + randomNumber;
                    break;
                case 2:
                    letter = "N ";
                    randomNumber = randomNum(40, 60);
                    result += letter + randomNumber;
                    break;
                case 3:
                    letter = "G ";
                    randomNumber = randomNum(60, 80);
                    result += letter + randomNumber;
                    break;
                default:
                    letter = "O ";
                    randomNumber = randomNum(80, 99);
                    result += letter + randomNumber;
                    break;
            }
        } while (numbersCalled.contains(letter + randomNumber));

        // Add the new number to the list of numbers drawn
        numbersCalled.add(letter + randomNumber);

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

    // Check if the cardss has 5 in a row
    // Returns: true if the given player's cards has 5 in a row
    // otherwise returns false

    // player can be:
    // "player" for the player
    // "computer" for the computer
    private boolean fiveInARow(String player) {
        // Table of IDs in each cards

        // 00|10|20|30|40
        // 01|11|21|31|41
        // 02|12|22|32|42
        // 03|13|23|33|34
        // 04|14|24|34|44

        // Create an array to store the occurrences of each number
        int[] occurrences = new int[]{
                0, // Two of the same number
                0, // Sums to 4
                0, // Starts with 0
                0, // Starts with 1
                0, // Starts with 2
                0, // Starts with 3
                0, // Starts with 4
                0, // Ends with 0
                0, // Ends with 1
                0, // Ends with 2
                0, // Ends with 3
                0};// Ends with 4

        // Loop through every cell on the cards
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 5; row++) {
                // Check if the current cell has been selected
                if (player.equals("player1") && player1CellStates[col][row] == true) {
                    if (col == row) {
                        occurrences[0]++;
                    }
                    if (col + row == 4) {
                        occurrences[1]++;
                    }
                    occurrences[col + 2]++;
                    occurrences[row + 7]++;

                } else if (player.equals("player2") && player2CellStates[col][row] == true) {
                    if (col == row) {
                        occurrences[0]++;
                    }
                    if (col + row == 4) {
                        occurrences[1]++;
                    }
                    occurrences[col + 2]++;
                    occurrences[row + 7]++;

                } else if (player.equals("computer1") && computer1CellStates[col][row] == true) {
                    if (col == row) {
                        occurrences[0]++;
                    }
                    if (col + row == 4) {
                        occurrences[1]++;
                    }
                    occurrences[col + 2]++;
                    occurrences[row + 7]++;

                } else if (player.equals("computer2") && computer2CellStates[col][row] == true) {
                    if (col == row) {
                        occurrences[0]++;
                    }
                    if (col + row == 4) {
                        occurrences[1]++;
                    }
                    occurrences[col + 2]++;
                    occurrences[row + 7]++;
                }
            }
        }

        // Check if there is a 5 in a row
        for (int i = 0; i < occurrences.length; i++) {
            // If there is 5 in a row, return true
            if (occurrences[i] == 5) {
                return true;
            }
        }

        return false;
    }

    // Check the player's choices
    // Returns: true if no errors, otherwise return false
    private boolean checkPlayercards() {
        // Create a variable to store the current letter
        String currentLetter;

        if (fiveInARow("player1") || fiveInARow("player2")) {
            // Loop through every cell on the player's cards
            for (int col = 0; col < 5; col++) {
                for (int row = 0; row < 5; row++) {
                    // Get the current letter
                    switch (col) {
                        case 0:
                            currentLetter = "B";
                            break;
                        case 1:
                            currentLetter = "I";
                            break;
                        case 2:
                            currentLetter = "N";
                            break;
                        case 3:
                            currentLetter = "G";
                            break;
                        case 4:
                            currentLetter = "O";
                            break;
                        default:
                            currentLetter = "";
                    }

                    if (player1CellStates[col][row] == true) {
                        // Check if the cell that the player selected was not
                        // called
                        if (!numbersCalled.contains(
                                currentLetter + " " + player1CellValues[col][row]) &&
                                !(col == 2 && row == 2)) {
                            return false;
                        }

                    }

                    if (player2CellStates[col][row] == true) {
                        // Check if the cell that the player selected was not
                        // called
                        if (!numbersCalled.contains(
                                currentLetter + " " + player2CellValues[col][row]) &&
                                !(col == 2 && row == 2)) {
                            return false;
                        }
                    }
                }
            }

            // If it looped through every cell without error, the player's cards
            // must be accurate
            return true;
        }

        // If the player didn't get 5 in a row, the player cannot possibly win
        return false;
    }

    private void gameOver() {
        // gameState == 1 means that the player won
        if (gameState == 1) {
            // Pause the UI
            setPause(true);

            // Change the overlay's text
            button_overlay.setText("You Win!\n\nPress r to play again or ESC to go back home");

            // Change the overlay's text size
            button_overlay.setStyle("-fx-font-size: 36");

            // Change the overlay's opacity to show the UI behind it
            button_overlay.setOpacity(0.75);

        // gameState == -1 means that the computer won
        } else if (gameState == -1) {
            // Create a variable to store the current letter
            String currentLetter;

            // If the user has 5 in a row, show the player's incorrect choices
            // Otherwise, computer must have won. So stop the game and show
            // options to play again or exit

            if (fiveInARow("player1") || fiveInARow("player2")) {
                // Find cells that the player selected that were never drawn
                for (int col = 0; col < 5; col++) {
                    for (int row = 0; row < 5; row++) {
                        // Get the current letter
                        switch (col) {
                            case 0:
                                currentLetter = "B";
                                break;
                            case 1:
                                currentLetter = "I";
                                break;
                            case 2:
                                currentLetter = "N";
                                break;
                            case 3:
                                currentLetter = "G";
                                break;
                            case 4:
                                currentLetter = "O";
                                break;
                            default:
                                currentLetter = "";
                        }

                        // Find selected cells
                        if (player1CellStates[col][row] == true) {
                            // Check if the cell that the player selected was
                            // never called
                            if (!numbersCalled.contains(
                                    currentLetter + " " + player1CellValues[col][row]) &&
                                    !(col == 2 && row == 2)) {

                                // Change that cell's background color
                                if (col == 0 && row == 0) {
                                    player1_0_0.setStyle("-fx-background-color: red");
                                } else if (col == 0 && row == 1) {
                                    player1_0_1.setStyle("-fx-background-color: red");
                                } else if (col == 0 && row == 2) {
                                    player1_0_2.setStyle("-fx-background-color: red");
                                } else if (col == 0 && row == 3) {
                                    player1_0_3.setStyle("-fx-background-color: red");
                                } else if (col == 0 && row == 4) {
                                    player1_0_4.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 0) {
                                    player1_1_0.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 1) {
                                    player1_1_1.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 2) {
                                    player1_1_2.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 3) {
                                    player1_1_3.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 4) {
                                    player1_1_4.setStyle("-fx-background-color: red");
                                } else if (col == 2 && row == 0) {
                                    player1_2_0.setStyle("-fx-background-color: red");
                                } else if (col == 2 && row == 1) {
                                    player1_2_1.setStyle("-fx-background-color: red");
                                } else if (col == 2 && row == 3) {
                                    player1_2_3.setStyle("-fx-background-color: red");
                                } else if (col == 2 && row == 4) {
                                    player1_2_4.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 0) {
                                    player1_3_0.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 1) {
                                    player1_3_1.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 2) {
                                    player1_3_2.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 3) {
                                    player1_3_3.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 4) {
                                    player1_3_4.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 0) {
                                    player1_4_0.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 1) {
                                    player1_4_1.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 2) {
                                    player1_4_2.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 3) {
                                    player1_4_3.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 4) {
                                    player1_4_4.setStyle("-fx-background-color: red");
                                }
                            }

                        } else if (player2CellStates[col][row] == true) {
                            // Check if the cell that the player selected was
                            // never called
                            if (!numbersCalled.contains(
                                    currentLetter + " " + player2CellValues[col][row]) &&
                                    !(col == 2 && row == 2)) {

                                // Change that cell's background color
                                if (col == 0 && row == 0) {
                                    player2_0_0.setStyle("-fx-background-color: red");
                                } else if (col == 0 && row == 1) {
                                    player2_0_1.setStyle("-fx-background-color: red");
                                } else if (col == 0 && row == 2) {
                                    player2_0_2.setStyle("-fx-background-color: red");
                                } else if (col == 0 && row == 3) {
                                    player2_0_3.setStyle("-fx-background-color: red");
                                } else if (col == 0 && row == 4) {
                                    player2_0_4.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 0) {
                                    player2_1_0.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 1) {
                                    player2_1_1.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 2) {
                                    player2_1_2.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 3) {
                                    player2_1_3.setStyle("-fx-background-color: red");
                                } else if (col == 1 && row == 4) {
                                    player2_1_4.setStyle("-fx-background-color: red");
                                } else if (col == 2 && row == 0) {
                                    player2_2_0.setStyle("-fx-background-color: red");
                                } else if (col == 2 && row == 1) {
                                    player2_2_1.setStyle("-fx-background-color: red");
                                } else if (col == 2 && row == 3) {
                                    player2_2_3.setStyle("-fx-background-color: red");
                                } else if (col == 2 && row == 4) {
                                    player2_2_4.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 0) {
                                    player2_3_0.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 1) {
                                    player2_3_1.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 2) {
                                    player2_3_2.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 3) {
                                    player2_3_3.setStyle("-fx-background-color: red");
                                } else if (col == 3 && row == 4) {
                                    player2_3_4.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 0) {
                                    player2_4_0.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 1) {
                                    player2_4_1.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 2) {
                                    player2_4_2.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 3) {
                                    player2_4_3.setStyle("-fx-background-color: red");
                                } else if (col == 4 && row == 4) {
                                    player2_4_4.setStyle("-fx-background-color: red");
                                }
                            }
                        }
                    }
                }

            } else if (fiveInARow("computer1") || fiveInARow("computer2")) {
                // Pause the UI
                setPause(true);

                // Change the overlay's text
                button_overlay.setText("You Lost!\n\nPress r to play again or ESC to go back home");

                // Change the overlay's text size
                button_overlay.setStyle("-fx-font-size: 36");

                // Change the overlay's opacity to show the UI behind it
                button_overlay.setOpacity(0.75);
            }

        // Anything else means that there was an unknown error
        } else {
            System.out.println("Error: No winner");
            System.exit(-1);
        }
    }



    private void play() {
        // Create a new timer thread so the timer won't freeze the UI
        Thread timer = new Thread(new Runnable(){
            @Override
            public void run() {
                // Create a variable to count seconds
                // int seconds = 0;

                // Timer loop
                while (!gamePaused) {
                    // If the time is up, generate a new letter and number,
                    // set seconds to 0, and add 1 to turnsTaken
                    if (seconds >= timerDuration) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                label_number.setText(generateRandomNumberLetter());
                            }
                        });

                        seconds = 0;
                    }

                    // When the timer is halfway through, the computer will
                    // take its turn
                    if (seconds >= (timerDuration / 2)) {
                        // Computer takes a turn
                        computerTurn();

                        // Check the computer's cards to see if it won
                        if (fiveInARow("computer1") || fiveInARow("computer2")) {
                            gameState = -1;

                            // Use Platform.runLater() to ensure that the
                            // commands are running on the application
                            // thread
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run() {
                                    gameOver();
                                };
                            });
                        }

                        // Check if the autopilot feature is on
                        if (autopilotMode) {
                            playerTurn();
                            if (checkPlayercards()) {
                                gameState = 1;

                                // Use Platform.runLater() to ensure that the
                                // commands are running on the application
                                // thread
                                Platform.runLater(new Runnable(){
                                    @Override
                                    public void run() {
                                        gameOver();
                                    };
                                });
                            }
                        }
                    }
                    
                    // Create a final variable to use in Platform.runLater()

                    // -1 is for showing seconds left because when you have 0.5
                    // seconds left, you should show 0 instead of 1
                    final int SECONDS = timerDuration - seconds - 1;

                    // Update the timer label to show seconds left using
                    // Platform.runLater() so the timer thread can change
                    // label_timer
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            // Update the time display on the UI
                            // Check if autopilot mode is enabled
                            if (autopilotMode) {
                                label_timer.setText("Timer: " + SECONDS + "\n\nAutopilot:\nenabled");
                            } else {
                                label_timer.setText("Timer: " + SECONDS);
                            }
                        }
                    });

                    // Try to sleep for 1 second
                    // Account for the drift of seconds while the code is running
                    try {
                        Thread.sleep(990);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }

                    // Add 1 to the number of seconds
                    seconds++;
                }
            }
        });

        // Start the timer
        timer.start();
    }



    // Check computer's cards for matches
    private void computerTurn() {
        // Get the current drawn value
        String[] drawnValue = (label_number.getText()).split(" ");
        String letter = drawnValue[0];
        int number = Integer.parseInt(drawnValue[1]);

        // Select the one that matches, if any
        if (letter.equals("B")) {
            if (computer1CellValues[0][0] == number) {
                computer1_0_0.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[0][0] = true;
            } else if (computer1CellValues[0][1] == number) {
                computer1_0_1.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[0][1] = true;
            } else if (computer1CellValues[0][2] == number) {
                computer1_0_2.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[0][2] = true;
            } else if (computer1CellValues[0][3] == number) {
                computer1_0_3.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[0][3] = true;
            } else if (computer1CellValues[0][4] == number) {
                computer1_0_4.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[0][4] = true;
            }

            if (computer2CellValues[0][0] == number) {
                computer2_0_0.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[0][0] = true;
            } else if (computer2CellValues[0][1] == number) {
                computer2_0_1.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[0][1] = true;
            } else if (computer2CellValues[0][2] == number) {
                computer2_0_2.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[0][2] = true;
            } else if (computer2CellValues[0][3] == number) {
                computer2_0_3.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[0][3] = true;
            } else if (computer2CellValues[0][4] == number) {
                computer2_0_4.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[0][4] = true;
            }

        } else if (letter.equals("I")) {
            if (computer1CellValues[1][0] == number) {
                computer1_1_0.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[1][0] = true;
            } else if (computer1CellValues[1][1] == number) {
                computer1_1_1.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[1][1] = true;
            } else if (computer1CellValues[1][2] == number) {
                computer1_1_2.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[1][2] = true;
            } else if (computer1CellValues[1][3] == number) {
                computer1_1_3.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[1][3] = true;
            } else if (computer1CellValues[1][4] == number) {
                computer1_1_4.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[1][4] = true;
            }

            if (computer2CellValues[1][0] == number) {
                computer2_1_0.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[1][0] = true;
            } else if (computer2CellValues[1][1] == number) {
                computer2_1_1.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[1][1] = true;
            } else if (computer2CellValues[1][2] == number) {
                computer2_1_2.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[1][2] = true;
            } else if (computer2CellValues[1][3] == number) {
                computer2_1_3.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[1][3] = true;
            } else if (computer2CellValues[1][4] == number) {
                computer2_1_4.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[1][4] = true;
            }

        } else if (letter.equals("N")) {
            if (computer1CellValues[2][0] == number) {
                computer1_2_0.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[2][0] = true;
            } else if (computer1CellValues[2][1] == number) {
                computer1_2_1.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[2][1] = true;
            } else if (computer1CellValues[2][3] == number) {
                computer1_2_3.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[2][3] = true;
            } else if (computer1CellValues[2][4] == number) {
                computer1_2_4.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[2][4] = true;
            }

            if (computer2CellValues[2][0] == number) {
                computer2_2_0.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[2][0] = true;
            } else if (computer2CellValues[2][1] == number) {
                computer2_2_1.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[2][1] = true;
            } else if (computer2CellValues[2][3] == number) {
                computer2_2_3.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[2][3] = true;
            } else if (computer2CellValues[2][4] == number) {
                computer2_2_4.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[2][4] = true;
            }

        } else if (letter.equals("G")) {
            if (computer1CellValues[3][0] == number) {
                computer1_3_0.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[3][0] = true;
            } else if (computer1CellValues[3][1] == number) {
                computer1_3_1.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[3][1] = true;
            } else if (computer1CellValues[3][2] == number) {
                computer1_3_2.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[3][2] = true;
            } else if (computer1CellValues[3][3] == number) {
                computer1_3_3.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[3][3] = true;
            } else if (computer1CellValues[3][4] == number) {
                computer1_3_4.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[3][4] = true;
            }

            if (computer2CellValues[3][0] == number) {
                computer2_3_0.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[3][0] = true;
            } else if (computer2CellValues[3][1] == number) {
                computer2_3_1.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[3][1] = true;
            } else if (computer2CellValues[3][2] == number) {
                computer2_3_2.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[3][2] = true;
            } else if (computer2CellValues[3][3] == number) {
                computer2_3_3.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[3][3] = true;
            } else if (computer2CellValues[3][4] == number) {
                computer2_3_4.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[3][4] = true;
            }

        } else if (letter.equals("O")) {
            if (computer1CellValues[4][0] == number) {
                computer1_4_0.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[4][0] = true;
            } else if (computer1CellValues[4][1] == number) {
                computer1_4_1.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[4][1] = true;
            } else if (computer1CellValues[4][2] == number) {
                computer1_4_2.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[4][2] = true;
            } else if (computer1CellValues[4][3] == number) {
                computer1_4_3.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[4][3] = true;
            } else if (computer1CellValues[4][4] == number) {
                computer1_4_4.setStyle("-fx-background-color: lightgrey");
                computer1CellStates[4][4] = true;
            }

            if (computer2CellValues[4][0] == number) {
                computer2_4_0.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[4][0] = true;
            } else if (computer2CellValues[4][1] == number) {
                computer2_4_1.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[4][1] = true;
            } else if (computer2CellValues[4][2] == number) {
                computer2_4_2.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[4][2] = true;
            } else if (computer2CellValues[4][3] == number) {
                computer2_4_3.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[4][3] = true;
            } else if (computer2CellValues[4][4] == number) {
                computer2_4_4.setStyle("-fx-background-color: lightgrey");
                computer2CellStates[4][4] = true;
            }
        }
    }



    // Check player's cards for matches
    private void playerTurn() {
        // Get the current drawn value
        String[] drawnValue = (label_number.getText()).split(" ");
        String letter = drawnValue[0];
        int number = Integer.parseInt(drawnValue[1]);

        // Select the one that matches, if any
        if (letter.equals("B")) {
            if (player1CellValues[0][0] == number) {
                player1_0_0.setStyle("-fx-background-color: lightgrey");
                player1CellStates[0][0] = true;
            } else if (player1CellValues[0][1] == number) {
                player1_0_1.setStyle("-fx-background-color: lightgrey");
                player1CellStates[0][1] = true;
            } else if (player1CellValues[0][2] == number) {
                player1_0_2.setStyle("-fx-background-color: lightgrey");
                player1CellStates[0][2] = true;
            } else if (player1CellValues[0][3] == number) {
                player1_0_3.setStyle("-fx-background-color: lightgrey");
                player1CellStates[0][3] = true;
            } else if (player1CellValues[0][4] == number) {
                player1_0_4.setStyle("-fx-background-color: lightgrey");
                player1CellStates[0][4] = true;
            }

            if (player2CellValues[0][0] == number) {
                player2_0_0.setStyle("-fx-background-color: lightgrey");
                player2CellStates[0][0] = true;
            } else if (player2CellValues[0][1] == number) {
                player2_0_1.setStyle("-fx-background-color: lightgrey");
                player2CellStates[0][1] = true;
            } else if (player2CellValues[0][2] == number) {
                player2_0_2.setStyle("-fx-background-color: lightgrey");
                player2CellStates[0][2] = true;
            } else if (player2CellValues[0][3] == number) {
                player2_0_3.setStyle("-fx-background-color: lightgrey");
                player2CellStates[0][3] = true;
            } else if (player2CellValues[0][4] == number) {
                player2_0_4.setStyle("-fx-background-color: lightgrey");
                player2CellStates[0][4] = true;
            }

        } else if (letter.equals("I")) {
            if (player1CellValues[1][0] == number) {
                player1_1_0.setStyle("-fx-background-color: lightgrey");
                player1CellStates[1][0] = true;
            } else if (player1CellValues[1][1] == number) {
                player1_1_1.setStyle("-fx-background-color: lightgrey");
                player1CellStates[1][1] = true;
            } else if (player1CellValues[1][2] == number) {
                player1_1_2.setStyle("-fx-background-color: lightgrey");
                player1CellStates[1][2] = true;
            } else if (player1CellValues[1][3] == number) {
                player1_1_3.setStyle("-fx-background-color: lightgrey");
                player1CellStates[1][3] = true;
            } else if (player1CellValues[1][4] == number) {
                player1_1_4.setStyle("-fx-background-color: lightgrey");
                player1CellStates[1][4] = true;
            }

            if (player2CellValues[1][0] == number) {
                player2_1_0.setStyle("-fx-background-color: lightgrey");
                player2CellStates[1][0] = true;
            } else if (player2CellValues[1][1] == number) {
                player2_1_1.setStyle("-fx-background-color: lightgrey");
                player2CellStates[1][1] = true;
            } else if (player2CellValues[1][2] == number) {
                player2_1_2.setStyle("-fx-background-color: lightgrey");
                player2CellStates[1][2] = true;
            } else if (player2CellValues[1][3] == number) {
                player2_1_3.setStyle("-fx-background-color: lightgrey");
                player2CellStates[1][3] = true;
            } else if (player2CellValues[1][4] == number) {
                player2_1_4.setStyle("-fx-background-color: lightgrey");
                player2CellStates[1][4] = true;
            }

        } else if (letter.equals("N")) {
            if (player1CellValues[2][0] == number) {
                player1_2_0.setStyle("-fx-background-color: lightgrey");
                player1CellStates[2][0] = true;
            } else if (player1CellValues[2][1] == number) {
                player1_2_1.setStyle("-fx-background-color: lightgrey");
                player1CellStates[2][1] = true;
            } else if (player1CellValues[2][3] == number) {
                player1_2_3.setStyle("-fx-background-color: lightgrey");
                player1CellStates[2][3] = true;
            } else if (player1CellValues[2][4] == number) {
                player1_2_4.setStyle("-fx-background-color: lightgrey");
                player1CellStates[2][4] = true;
            }

            if (player2CellValues[2][0] == number) {
                player2_2_0.setStyle("-fx-background-color: lightgrey");
                player2CellStates[2][0] = true;
            } else if (player2CellValues[2][1] == number) {
                player2_2_1.setStyle("-fx-background-color: lightgrey");
                player2CellStates[2][1] = true;
            } else if (player2CellValues[2][3] == number) {
                player2_2_3.setStyle("-fx-background-color: lightgrey");
                player2CellStates[2][3] = true;
            } else if (player2CellValues[2][4] == number) {
                player2_2_4.setStyle("-fx-background-color: lightgrey");
                player2CellStates[2][4] = true;
            }

        } else if (letter.equals("G")) {
            if (player1CellValues[3][0] == number) {
                player1_3_0.setStyle("-fx-background-color: lightgrey");
                player1CellStates[3][0] = true;
            } else if (player1CellValues[3][1] == number) {
                player1_3_1.setStyle("-fx-background-color: lightgrey");
                player1CellStates[3][1] = true;
            } else if (player1CellValues[3][2] == number) {
                player1_3_2.setStyle("-fx-background-color: lightgrey");
                player1CellStates[3][2] = true;
            } else if (player1CellValues[3][3] == number) {
                player1_3_3.setStyle("-fx-background-color: lightgrey");
                player1CellStates[3][3] = true;
            } else if (player1CellValues[3][4] == number) {
                player1_3_4.setStyle("-fx-background-color: lightgrey");
                player1CellStates[3][4] = true;
            }

            if (player2CellValues[3][0] == number) {
                player2_3_0.setStyle("-fx-background-color: lightgrey");
                player2CellStates[3][0] = true;
            } else if (player2CellValues[3][1] == number) {
                player2_3_1.setStyle("-fx-background-color: lightgrey");
                player2CellStates[3][1] = true;
            } else if (player2CellValues[3][2] == number) {
                player2_3_2.setStyle("-fx-background-color: lightgrey");
                player2CellStates[3][2] = true;
            } else if (player2CellValues[3][3] == number) {
                player2_3_3.setStyle("-fx-background-color: lightgrey");
                player2CellStates[3][3] = true;
            } else if (player2CellValues[3][4] == number) {
                player2_3_4.setStyle("-fx-background-color: lightgrey");
                player2CellStates[3][4] = true;
            }

        } else if (letter.equals("O")) {
            if (player1CellValues[4][0] == number) {
                player1_4_0.setStyle("-fx-background-color: lightgrey");
                player1CellStates[4][0] = true;
            } else if (player1CellValues[4][1] == number) {
                player1_4_1.setStyle("-fx-background-color: lightgrey");
                player1CellStates[4][1] = true;
            } else if (player1CellValues[4][2] == number) {
                player1_4_2.setStyle("-fx-background-color: lightgrey");
                player1CellStates[4][2] = true;
            } else if (player1CellValues[4][3] == number) {
                player1_4_3.setStyle("-fx-background-color: lightgrey");
                player1CellStates[4][3] = true;
            } else if (player1CellValues[4][4] == number) {
                player1_4_4.setStyle("-fx-background-color: lightgrey");
                player1CellStates[4][4] = true;
            }

            if (player2CellValues[4][0] == number) {
                player2_4_0.setStyle("-fx-background-color: lightgrey");
                player2CellStates[4][0] = true;
            } else if (player2CellValues[4][1] == number) {
                player2_4_1.setStyle("-fx-background-color: lightgrey");
                player2CellStates[4][1] = true;
            } else if (player2CellValues[4][2] == number) {
                player2_4_2.setStyle("-fx-background-color: lightgrey");
                player2CellStates[4][2] = true;
            } else if (player2CellValues[4][3] == number) {
                player2_4_3.setStyle("-fx-background-color: lightgrey");
                player2CellStates[4][3] = true;
            } else if (player2CellValues[4][4] == number) {
                player2_4_4.setStyle("-fx-background-color: lightgrey");
                player2CellStates[4][4] = true;
            }
        }
    }



    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Methods for processing user interactions with the game
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // Initialize game board when user starts the game
    public void overlayFunction() {
        if (!gameInitialized) {
            // Key combination to activate the autopilot mode for the player
            final KeyCombination autopilotKeyCombo = new KeyCodeCombination(
                    KeyCode.A,
                    KeyCombination.CONTROL_DOWN,
                    KeyCombination.ALT_DOWN,
                    KeyCombination.SHIFT_DOWN);
            anchorPane_root.getScene().addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (autopilotKeyCombo.match(event)) {
                        if (autopilotMode) {
                            autopilotMode = false;
                        } else {
                            autopilotMode = true;
                        }
                    }
                }
            });
            
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            // Add an event handler to every cell in the player's bingo cards
            // See line 1 for more information
            player1_0_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[0][0] == true) {
                        player1_0_0.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[0][0] = false;
                    } else {
                        player1_0_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[0][0] = true;
                    }
                }
            });
            player1_0_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[0][1] == true) {
                        player1_0_1.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[0][1] = false;
                    } else {
                        player1_0_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[0][1] = true;
                    }
                }
            });
            player1_0_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[0][2] == true) {
                        player1_0_2.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[0][2] = false;
                    } else {
                        player1_0_2.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[0][2] = true;
                    }
                }
            });
            player1_0_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[0][3] == true) {
                        player1_0_3.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[0][3] = false;
                    } else {
                        player1_0_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[0][3] = true;
                    }
                }
            });
            player1_0_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[0][4] == true) {
                        player1_0_4.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[0][4] = false;
                    } else {
                        player1_0_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[0][4] = true;
                    }
                }
            });
            player1_1_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[1][0] == true) {
                        player1_1_0.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[1][0] = false;
                    } else {
                        player1_1_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[1][0] = true;
                    }
                }
            });
            player1_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[1][1] == true) {
                        player1_1_1.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[1][1] = false;
                    } else {
                        player1_1_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[1][1] = true;
                    }
                }
            });
            player1_1_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[1][2] == true) {
                        player1_1_2.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[1][2] = false;
                    } else {
                        player1_1_2.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[1][2] = true;
                    }
                }
            });
            player1_1_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[1][3] == true) {
                        player1_1_3.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[1][3] = false;
                    } else {
                        player1_1_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[1][3] = true;
                    }
                }
            });
            player1_1_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[1][4] == true) {
                        player1_1_4.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[1][4] = false;
                    } else {
                        player1_1_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[1][4] = true;
                    }
                }
            });
            player1_2_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[2][0] == true) {
                        player1_2_0.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[2][0] = false;
                    } else {
                        player1_2_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[2][0] = true;
                    }
                }
            });
            player1_2_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[2][1] == true) {
                        player1_2_1.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[2][1] = false;
                    } else {
                        player1_2_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[2][1] = true;
                    }
                }
            });
            player1_2_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[2][3] == true) {
                        player1_2_3.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[2][3] = false;
                    } else {
                        player1_2_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[2][3] = true;
                    }
                }
            });
            player1_2_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[2][4] == true) {
                        player1_2_4.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[2][4] = false;
                    } else {
                        player1_2_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[2][4] = true;
                    }
                }
            });
            player1_3_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[3][0] == true) {
                        player1_3_0.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[3][0] = false;
                    } else {
                        player1_3_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[3][0] = true;
                    }
                }
            });
            player1_3_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[3][1] == true) {
                        player1_3_1.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[3][1] = false;
                    } else {
                        player1_3_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[3][1] = true;
                    }
                }
            });
            player1_3_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[3][2] == true) {
                        player1_3_2.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[3][2] = false;
                    } else {
                        player1_3_2.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[3][2] = true;
                    }
                }
            });
            player1_3_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[3][3] == true) {
                        player1_3_3.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[3][3] = false;
                    } else {
                        player1_3_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[3][3] = true;
                    }
                }
            });
            player1_3_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[3][4] == true) {
                        player1_3_4.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[3][4] = false;
                    } else {
                        player1_3_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[3][4] = true;
                    }
                }
            });
            player1_4_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[4][0] == true) {
                        player1_4_0.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[4][0] = false;
                    } else {
                        player1_4_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[4][0] = true;
                    }
                }
            });
            player1_4_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[4][1] == true) {
                        player1_4_1.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[4][1] = false;
                    } else {
                        player1_4_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[4][1] = true;
                    }
                }
            });
            player1_4_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[4][2] == true) {
                        player1_4_2.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[4][2] = false;
                    } else {
                        player1_4_2.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[4][2] = true;
                    }
                }
            });
            player1_4_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[4][3] == true) {
                        player1_4_3.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[4][3] = false;
                    } else {
                        player1_4_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[4][3] = true;
                    }
                }
            });
            player1_4_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player1CellStates[4][4] == true) {
                        player1_4_4.setStyle(
                                "-fx-background-color: transparent");
                        player1CellStates[4][4] = false;
                    } else {
                        player1_4_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player1CellStates[4][4] = true;
                    }
                }
            });



            player2_0_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[0][0] == true) {
                        player2_0_0.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[0][0] = false;
                    } else {
                        player2_0_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[0][0] = true;
                    }
                }
            });
            player2_0_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[0][1] == true) {
                        player2_0_1.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[0][1] = false;
                    } else {
                        player2_0_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[0][1] = true;
                    }
                }
            });
            player2_0_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[0][2] == true) {
                        player2_0_2.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[0][2] = false;
                    } else {
                        player2_0_2.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[0][2] = true;
                    }
                }
            });
            player2_0_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[0][3] == true) {
                        player2_0_3.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[0][3] = false;
                    } else {
                        player2_0_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[0][3] = true;
                    }
                }
            });
            player2_0_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[0][4] == true) {
                        player2_0_4.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[0][4] = false;
                    } else {
                        player2_0_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[0][4] = true;
                    }
                }
            });
            player2_1_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[1][0] == true) {
                        player2_1_0.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[1][0] = false;
                    } else {
                        player2_1_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[1][0] = true;
                    }
                }
            });
            player2_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[1][1] == true) {
                        player2_1_1.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[1][1] = false;
                    } else {
                        player2_1_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[1][1] = true;
                    }
                }
            });
            player2_1_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[1][2] == true) {
                        player2_1_2.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[1][2] = false;
                    } else {
                        player2_1_2.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[1][2] = true;
                    }
                }
            });
            player2_1_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[1][3] == true) {
                        player2_1_3.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[1][3] = false;
                    } else {
                        player2_1_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[1][3] = true;
                    }
                }
            });
            player2_1_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[1][4] == true) {
                        player2_1_4.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[1][4] = false;
                    } else {
                        player2_1_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[1][4] = true;
                    }
                }
            });
            player2_2_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[2][0] == true) {
                        player2_2_0.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[2][0] = false;
                    } else {
                        player2_2_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[2][0] = true;
                    }
                }
            });
            player2_2_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[2][1] == true) {
                        player2_2_1.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[2][1] = false;
                    } else {
                        player2_2_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[2][1] = true;
                    }
                }
            });
            player2_2_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[2][3] == true) {
                        player2_2_3.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[2][3] = false;
                    } else {
                        player2_2_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[2][3] = true;
                    }
                }
            });
            player2_2_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[2][4] == true) {
                        player2_2_4.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[2][4] = false;
                    } else {
                        player2_2_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[2][4] = true;
                    }
                }
            });
            player2_3_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[3][0] == true) {
                        player2_3_0.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[3][0] = false;
                    } else {
                        player2_3_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[3][0] = true;
                    }
                }
            });
            player2_3_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[3][1] == true) {
                        player2_3_1.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[3][1] = false;
                    } else {
                        player2_3_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[3][1] = true;
                    }
                }
            });
            player2_3_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[3][2] == true) {
                        player2_3_2.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[3][2] = false;
                    } else {
                        player2_3_2.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[3][2] = true;
                    }
                }
            });
            player2_3_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[3][3] == true) {
                        player2_3_3.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[3][3] = false;
                    } else {
                        player2_3_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[3][3] = true;
                    }
                }
            });
            player2_3_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[3][4] == true) {
                        player2_3_4.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[3][4] = false;
                    } else {
                        player2_3_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[3][4] = true;
                    }
                }
            });
            player2_4_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[4][0] == true) {
                        player2_4_0.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[4][0] = false;
                    } else {
                        player2_4_0.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[4][0] = true;
                    }
                }
            });
            player2_4_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[4][1] == true) {
                        player2_4_1.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[4][1] = false;
                    } else {
                        player2_4_1.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[4][1] = true;
                    }
                }
            });
            player2_4_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[4][2] == true) {
                        player2_4_2.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[4][2] = false;
                    } else {
                        player2_4_2.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[4][2] = true;
                    }
                }
            });
            player2_4_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[4][3] == true) {
                        player2_4_3.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[4][3] = false;
                    } else {
                        player2_4_3.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[4][3] = true;
                    }
                }
            });
            player2_4_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (player2CellStates[4][4] == true) {
                        player2_4_4.setStyle(
                                "-fx-background-color: transparent");
                        player2CellStates[4][4] = false;
                    } else {
                        player2_4_4.setStyle(
                                "-fx-background-color: lightgrey");
                        player2CellStates[4][4] = true;
                    }
                }
            });
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

            // Create array to store states of the player's and computer's cells
            // Make sure to not have duplicate numbers
            // Generate random numbers for cardss
            // B: 1 - 20
            // I: 20 - 40
            // N: 40 - 60
            // G: 60 - 80
            // O: 80 - 99
            for (int col = 0; col < 5; col++) {
                for (int row = 0; row < 5; row++) {
                    if (col == 0) {
                        while (existsInArray(player1CellValues, player1CellValues[col][row]) != 1) {
                            player1CellValues[col][row] = randomNum(1, 20);
                        }
                        while (existsInArray(player2CellValues, player2CellValues[col][row]) != 1) {
                            player2CellValues[col][row] = randomNum(1, 20);
                        }
                        while (existsInArray(computer1CellValues, computer1CellValues[col][row]) != 1) {
                            computer1CellValues[col][row] = randomNum(1, 20);
                        }
                        while (existsInArray(computer2CellValues, computer2CellValues[col][row]) != 1) {
                            computer2CellValues[col][row] = randomNum(1, 20);
                        }

                    } else if (col == 1) {
                        while (existsInArray(player1CellValues, player1CellValues[col][row]) != 1) {
                            player1CellValues[col][row] = randomNum(20, 40);
                        }
                        while (existsInArray(player2CellValues, player2CellValues[col][row]) != 1) {
                            player2CellValues[col][row] = randomNum(20, 40);
                        }
                        while (existsInArray(computer1CellValues, computer1CellValues[col][row]) != 1) {
                            computer1CellValues[col][row] = randomNum(20, 40);
                        }
                        while (existsInArray(computer2CellValues, computer2CellValues[col][row]) != 1) {
                            computer2CellValues[col][row] = randomNum(20, 40);
                        }

                    } else if (col == 2 && row != 2) {
                        while (existsInArray(player1CellValues, player1CellValues[col][row]) != 1) {
                            player1CellValues[col][row] = randomNum(40, 60);
                        }
                        while (existsInArray(player2CellValues, player2CellValues[col][row]) != 1) {
                            player2CellValues[col][row] = randomNum(40, 60);
                        }
                        while (existsInArray(computer1CellValues, computer1CellValues[col][row]) != 1) {
                            computer1CellValues[col][row] = randomNum(40, 60);
                        }
                        while (existsInArray(computer2CellValues, computer2CellValues[col][row]) != 1) {
                            computer2CellValues[col][row] = randomNum(40, 60);
                        }

                    } else if (col == 3) {
                        while (existsInArray(player1CellValues, player1CellValues[col][row]) != 1) {
                            player1CellValues[col][row] = randomNum(60, 80);
                        }
                        while (existsInArray(player2CellValues, player2CellValues[col][row]) != 1) {
                            player2CellValues[col][row] = randomNum(60, 80);
                        }
                        while (existsInArray(computer1CellValues, computer1CellValues[col][row]) != 1) {
                            computer1CellValues[col][row] = randomNum(60, 80);
                        }
                        while (existsInArray(computer2CellValues, computer2CellValues[col][row]) != 1) {
                            computer2CellValues[col][row] = randomNum(60, 80);
                        }

                    } else if (col == 4) {
                        while (existsInArray(player1CellValues, player1CellValues[col][row]) != 1) {
                            player1CellValues[col][row] = randomNum(80, 99);
                        }
                        while (existsInArray(player2CellValues, player2CellValues[col][row]) != 1) {
                            player2CellValues[col][row] = randomNum(80, 99);
                        }
                        while (existsInArray(computer1CellValues, computer1CellValues[col][row]) != 1) {
                            computer1CellValues[col][row] = randomNum(80, 99);
                        }
                        while (existsInArray(computer2CellValues, computer2CellValues[col][row]) != 1) {
                            computer2CellValues[col][row] = randomNum(80, 99);
                        }
                    }

                    // Set the states of each cell
                    if (col == 2 && row == 2) {
                        player1CellStates[col][row] = true;
                        player2CellStates[col][row] = true;
                        computer1CellStates[col][row] = true;
                        computer2CellStates[col][row] = true;
                    } else {
                        player1CellStates[col][row] = false;
                        player2CellStates[col][row] = false;
                        computer1CellStates[col][row] = false;
                        computer2CellStates[col][row] = false;
                    }
                }
            }

            // Set the randomly generated values for the player's cards
            player1_0_0.setText("" + player1CellValues[0][0]);
            player1_0_1.setText("" + player1CellValues[0][1]);
            player1_0_2.setText("" + player1CellValues[0][2]);
            player1_0_3.setText("" + player1CellValues[0][3]);
            player1_0_4.setText("" + player1CellValues[0][4]);
            player1_1_0.setText("" + player1CellValues[1][0]);
            player1_1_1.setText("" + player1CellValues[1][1]);
            player1_1_2.setText("" + player1CellValues[1][2]);
            player1_1_3.setText("" + player1CellValues[1][3]);
            player1_1_4.setText("" + player1CellValues[1][4]);
            player1_2_0.setText("" + player1CellValues[2][0]);
            player1_2_1.setText("" + player1CellValues[2][1]);
            player1_2_3.setText("" + player1CellValues[2][3]);
            player1_2_4.setText("" + player1CellValues[2][4]);
            player1_3_0.setText("" + player1CellValues[3][0]);
            player1_3_1.setText("" + player1CellValues[3][1]);
            player1_3_2.setText("" + player1CellValues[3][2]);
            player1_3_3.setText("" + player1CellValues[3][3]);
            player1_3_4.setText("" + player1CellValues[3][4]);
            player1_4_0.setText("" + player1CellValues[4][0]);
            player1_4_1.setText("" + player1CellValues[4][1]);
            player1_4_2.setText("" + player1CellValues[4][2]);
            player1_4_3.setText("" + player1CellValues[4][3]);
            player1_4_4.setText("" + player1CellValues[4][4]);

            player2_0_0.setText("" + player2CellValues[0][0]);
            player2_0_1.setText("" + player2CellValues[0][1]);
            player2_0_2.setText("" + player2CellValues[0][2]);
            player2_0_3.setText("" + player2CellValues[0][3]);
            player2_0_4.setText("" + player2CellValues[0][4]);
            player2_1_0.setText("" + player2CellValues[1][0]);
            player2_1_1.setText("" + player2CellValues[1][1]);
            player2_1_2.setText("" + player2CellValues[1][2]);
            player2_1_3.setText("" + player2CellValues[1][3]);
            player2_1_4.setText("" + player2CellValues[1][4]);
            player2_2_0.setText("" + player2CellValues[2][0]);
            player2_2_1.setText("" + player2CellValues[2][1]);
            player2_2_3.setText("" + player2CellValues[2][3]);
            player2_2_4.setText("" + player2CellValues[2][4]);
            player2_3_0.setText("" + player2CellValues[3][0]);
            player2_3_1.setText("" + player2CellValues[3][1]);
            player2_3_2.setText("" + player2CellValues[3][2]);
            player2_3_3.setText("" + player2CellValues[3][3]);
            player2_3_4.setText("" + player2CellValues[3][4]);
            player2_4_0.setText("" + player2CellValues[4][0]);
            player2_4_1.setText("" + player2CellValues[4][1]);
            player2_4_2.setText("" + player2CellValues[4][2]);
            player2_4_3.setText("" + player2CellValues[4][3]);
            player2_4_4.setText("" + player2CellValues[4][4]);

            // Set the randomly generated values for the computer's cards
            computer1_0_0.setText("" + computer1CellValues[0][0]);
            computer1_0_1.setText("" + computer1CellValues[0][1]);
            computer1_0_2.setText("" + computer1CellValues[0][2]);
            computer1_0_3.setText("" + computer1CellValues[0][3]);
            computer1_0_4.setText("" + computer1CellValues[0][4]);
            computer1_1_0.setText("" + computer1CellValues[1][0]);
            computer1_1_1.setText("" + computer1CellValues[1][1]);
            computer1_1_2.setText("" + computer1CellValues[1][2]);
            computer1_1_3.setText("" + computer1CellValues[1][3]);
            computer1_1_4.setText("" + computer1CellValues[1][4]);
            computer1_2_0.setText("" + computer1CellValues[2][0]);
            computer1_2_1.setText("" + computer1CellValues[2][1]);
            computer1_2_3.setText("" + computer1CellValues[2][3]);
            computer1_2_4.setText("" + computer1CellValues[2][4]);
            computer1_3_0.setText("" + computer1CellValues[3][0]);
            computer1_3_1.setText("" + computer1CellValues[3][1]);
            computer1_3_2.setText("" + computer1CellValues[3][2]);
            computer1_3_3.setText("" + computer1CellValues[3][3]);
            computer1_3_4.setText("" + computer1CellValues[3][4]);
            computer1_4_0.setText("" + computer1CellValues[4][0]);
            computer1_4_1.setText("" + computer1CellValues[4][1]);
            computer1_4_2.setText("" + computer1CellValues[4][2]);
            computer1_4_3.setText("" + computer1CellValues[4][3]);
            computer1_4_4.setText("" + computer1CellValues[4][4]);

            computer2_0_0.setText("" + computer2CellValues[0][0]);
            computer2_0_1.setText("" + computer2CellValues[0][1]);
            computer2_0_2.setText("" + computer2CellValues[0][2]);
            computer2_0_3.setText("" + computer2CellValues[0][3]);
            computer2_0_4.setText("" + computer2CellValues[0][4]);
            computer2_1_0.setText("" + computer2CellValues[1][0]);
            computer2_1_1.setText("" + computer2CellValues[1][1]);
            computer2_1_2.setText("" + computer2CellValues[1][2]);
            computer2_1_3.setText("" + computer2CellValues[1][3]);
            computer2_1_4.setText("" + computer2CellValues[1][4]);
            computer2_2_0.setText("" + computer2CellValues[2][0]);
            computer2_2_1.setText("" + computer2CellValues[2][1]);
            computer2_2_3.setText("" + computer2CellValues[2][3]);
            computer2_2_4.setText("" + computer2CellValues[2][4]);
            computer2_3_0.setText("" + computer2CellValues[3][0]);
            computer2_3_1.setText("" + computer2CellValues[3][1]);
            computer2_3_2.setText("" + computer2CellValues[3][2]);
            computer2_3_3.setText("" + computer2CellValues[3][3]);
            computer2_3_4.setText("" + computer2CellValues[3][4]);
            computer2_4_0.setText("" + computer2CellValues[4][0]);
            computer2_4_1.setText("" + computer2CellValues[4][1]);
            computer2_4_2.setText("" + computer2CellValues[4][2]);
            computer2_4_3.setText("" + computer2CellValues[4][3]);
            computer2_4_4.setText("" + computer2CellValues[4][4]);

            // Randomly generate a letter and a number
            label_number.setText(generateRandomNumberLetter());

            // Set the full screen state
            if (DataStore.getBoolean("fullScreenState")) {
                Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();
                currentStage.setFullScreen(DataStore.getBoolean("fullScreenState"));
            }

            // Remove overlay and enable the UI
            setPause(false);

            gameInitialized = true;

            // Start game
            play();

        } else if (gamePaused == true) {
            setPause(false);
            play();
        }
    }

    // Process key events in the root container
    public void processKey(KeyEvent key) {
        Stage currentStage = (Stage) anchorPane_root.getScene().getWindow();

        // Full screen window when F11 is pressed
        if (key.getCode() == KeyCode.F11) {
            if (currentStage.isFullScreen()) {
                currentStage.setFullScreen(false);
            } else {
                currentStage.setFullScreen(true);
            }

        } else if (gameState != 0 && key.getCode() == KeyCode.ESCAPE) {
            returnHome();

        } else if (gameState != 0 && key.getCode() == KeyCode.R) {
            openWindow("Medium.fxml", "Bingo", currentStage);
        }
    }

    // This is called when the user clicks the back button
    public void returnHome() {
        Stage currentStage = (Stage) button_back.getScene().getWindow();
        openWindow("Start.fxml", "Bingo", currentStage);
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

    // Pause game
    public void pause() {
        // Pause the UI
        setPause(true);

        // Change overlay text
        button_overlay.setText("Press a key to unpause");
    }

    // Check user choices when the bingo button is pressed
    public void bingo() {
        if (checkPlayercards()) {
            gameState = 1;

            // Use Platform.runLater() to ensure that gameOver() is running on
            // the application thread
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    gameOver();
                }
            });

        } else {
            gameState = -1;

            // Use Platform.runLater() to ensure that gameOver() is running on
            // the application thread
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    gameOver();
                }
            });
        }
    }
}
