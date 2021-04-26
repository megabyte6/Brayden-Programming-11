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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Easy_Controller {
    // Set to true for debugging info
    private Random random = new Random();

    private boolean gameInitialized = false;
    private boolean gamePaused = true;
    private int timerDuration = 1; // DataStore.getInteger("timerDuration");
    private int turnsTaken = 0;
    
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
    private int[][] playerCellValues = new int[5][5];
    private int[][] computerCellValues = new int[5][5];
    private boolean[][] playerCellStates = new boolean[5][5];
    private boolean[][] computerCellStates = new boolean[5][5];

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

        // Show the new stage
        newStage.show();

        return newStage;
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

    // Check the user's choices
    // Returns: true if no errors, otherwise return false

    // player can be:
    // "player" for the player
    // "computer" for the computer
    private boolean checkCard(String player) {
        String currentLetter;
        
        /*
        Table of IDs in each card

        00|10|20|30|40
        01|11|21|31|41
        02|12|22|32|42
        03|13|23|33|34
        04|14|24|34|44
        */

        // Create an array to store the occurrences of each number
        
        // doubleNum, sumToFour,
        // col_0, col_1, col_2, col_3, col_4,
        // row_0, row_1, row_2, row_3, row_4
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
        
        // Loop through every cell on the card
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 5; row++) {
                if (player.equals("player")) {
                    // If the cell has been selected
                    if (playerCellStates[col][row] == true) {
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

                        // Check if the player selected a cell that was not
                        // called
                        if (!numbersCalled.contains(
                                currentLetter + " " + playerCellValues[col][row]) &&
                                !(col == 2 && row == 2)) {
                            return false;
                        }

                        if (col == row) {
                            occurrences[0]++;
                        }
                        if (col + row == 4) {
                            occurrences[1]++;
                        }
                        occurrences[col + 2]++;
                        occurrences[row + 7]++;
                    }

                } else if (player.equals("computer")) {
                    // If the cell has been selected
                    if (computerCellStates[col][row] == true) {
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

    private void gameOver() {
        // gameState == 1 means that the player won
        if (gameState == 1) {
            System.out.println("Player wins");
            
            // Pause the UI
            setPause(true);

            // Change the overlay's text
            // button_overlay.setText("You Win! Press r to play again or ESC to go back home");

        // gameState == -1 means that the computer won
        } else if (gameState == -1) {
            // TODO : Show the player's incorrect choices
            System.out.println("Player loses");

            // If the user has 5 in a row, show the player's incorrect choices
            // Otherwise, computer must have won. So stop the game and show
            // options to play again or exit

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

                        turnsTaken++;
                        seconds = 0;
                    }

                    // When the timer is halfway through, the computer will
                    // take its turn
                    if (seconds >= (timerDuration / 2)) {
                        // Computer takes a turn
                        computerTurn();

                        // Check the computer's card to see if it won
                        if (checkCard("computer")) {
                            gameState = -1;
                            gameOver();
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
                            label_timer.setText("Timer: " + SECONDS);
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



    // Check computer's card for matches
    private void computerTurn() {
        // Get the current drawn value
        String[] drawnValue = (label_number.getText()).split(" ");
        String letter = drawnValue[0];
        int number = Integer.parseInt(drawnValue[1]);

        // Select the one that matches (if any)
        if (letter.equals("B")) {
            if (computerCellValues[0][0] == number) {
                computer_0_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][0] = true;
            } else if (computerCellValues[0][1] == number) {
                computer_0_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][1] = true;
            } else if (computerCellValues[0][2] == number) {
                computer_0_2.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][2] = true;
            } else if (computerCellValues[0][3] == number) {
                computer_0_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][3] = true;
            } else if (computerCellValues[0][4] == number) {
                computer_0_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[0][4] = true;
            }

        } else if (letter.equals("I")) {
            if (computerCellValues[1][0] == number) {
                computer_1_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][0] = true;
            } else if (computerCellValues[1][1] == number) {
                computer_1_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][1] = true;
            } else if (computerCellValues[1][2] == number) {
                computer_1_2.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][2] = true;
            } else if (computerCellValues[1][3] == number) {
                computer_1_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][3] = true;
            } else if (computerCellValues[1][4] == number) {
                computer_1_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[1][4] = true;
            }

        } else if (letter.equals("N")) {
            if (computerCellValues[2][0] == number) {
                computer_2_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[2][0] = true;
            } else if (computerCellValues[2][1] == number) {
                computer_2_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[2][1] = true;
            } else if (computerCellValues[2][3] == number) {
                computer_2_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[2][3] = true;
            } else if (computerCellValues[2][4] == number) {
                computer_2_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[2][4] = true;
            }

        } else if (letter.equals("G")) {
            if (computerCellValues[3][0] == number) {
                computer_3_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][0] = true;
            } else if (computerCellValues[3][1] == number) {
                computer_3_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][1] = true;
            } else if (computerCellValues[3][2] == number) {
                computer_3_2.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][2] = true;
            } else if (computerCellValues[3][3] == number) {
                computer_3_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][3] = true;
            } else if (computerCellValues[3][4] == number) {
                computer_3_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[3][4] = true;
            }

        } else if (letter.equals("O")) {
            if (computerCellValues[4][0] == number) {
                computer_4_0.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][0] = true;
            } else if (computerCellValues[4][1] == number) {
                computer_4_1.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][1] = true;
            } else if (computerCellValues[4][2] == number) {
                computer_4_2.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][2] = true;
            } else if (computerCellValues[4][3] == number) {
                computer_4_3.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][3] = true;
            } else if (computerCellValues[4][4] == number) {
                computer_4_4.setStyle("-fx-background-color: lightgrey");
                computerCellStates[4][4] = true;
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

                    // Set the states of each cell
                    if (col == 2 && row == 2) {
                        playerCellStates[col][row] = true;
                        computerCellStates[col][row] = true;
                    } else {
                        playerCellStates[col][row] = false;
                        computerCellStates[col][row] = false;
                    }
                }
            }

            // Set the randomly generated values for the player's card
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

            // Set the randomly generated values for the computer's card
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

            // Show the cards
            player_0_0.setText(String.valueOf(playerCellValues[0][0]));
            player_0_1.setText(String.valueOf(playerCellValues[0][1]));
            player_0_2.setText(String.valueOf(playerCellValues[0][2]));
            player_0_3.setText(String.valueOf(playerCellValues[0][3]));
            player_0_4.setText(String.valueOf(playerCellValues[0][4]));
            player_1_0.setText(String.valueOf(playerCellValues[1][0]));
            player_1_1.setText(String.valueOf(playerCellValues[1][1]));
            player_1_2.setText(String.valueOf(playerCellValues[1][2]));
            player_1_3.setText(String.valueOf(playerCellValues[1][3]));
            player_1_4.setText(String.valueOf(playerCellValues[1][4]));
            player_2_0.setText(String.valueOf(playerCellValues[2][0]));
            player_2_1.setText(String.valueOf(playerCellValues[2][1]));
            player_2_3.setText(String.valueOf(playerCellValues[2][3]));
            player_2_4.setText(String.valueOf(playerCellValues[2][4]));
            player_3_0.setText(String.valueOf(playerCellValues[3][0]));
            player_3_1.setText(String.valueOf(playerCellValues[3][1]));
            player_3_2.setText(String.valueOf(playerCellValues[3][2]));
            player_3_3.setText(String.valueOf(playerCellValues[3][3]));
            player_3_4.setText(String.valueOf(playerCellValues[3][4]));
            player_4_0.setText(String.valueOf(playerCellValues[4][0]));
            player_4_1.setText(String.valueOf(playerCellValues[4][1]));
            player_4_2.setText(String.valueOf(playerCellValues[4][2]));
            player_4_3.setText(String.valueOf(playerCellValues[4][3]));
            player_4_4.setText(String.valueOf(playerCellValues[4][4]));

            computer_0_0.setText(String.valueOf(computerCellValues[0][0]));
            computer_0_1.setText(String.valueOf(computerCellValues[0][1]));
            computer_0_2.setText(String.valueOf(computerCellValues[0][2]));
            computer_0_3.setText(String.valueOf(computerCellValues[0][3]));
            computer_0_4.setText(String.valueOf(computerCellValues[0][4]));
            computer_1_0.setText(String.valueOf(computerCellValues[1][0]));
            computer_1_1.setText(String.valueOf(computerCellValues[1][1]));
            computer_1_2.setText(String.valueOf(computerCellValues[1][2]));
            computer_1_3.setText(String.valueOf(computerCellValues[1][3]));
            computer_1_4.setText(String.valueOf(computerCellValues[1][4]));
            computer_2_0.setText(String.valueOf(computerCellValues[2][0]));
            computer_2_1.setText(String.valueOf(computerCellValues[2][1]));
            computer_2_3.setText(String.valueOf(computerCellValues[2][3]));
            computer_2_4.setText(String.valueOf(computerCellValues[2][4]));
            computer_3_0.setText(String.valueOf(computerCellValues[3][0]));
            computer_3_1.setText(String.valueOf(computerCellValues[3][1]));
            computer_3_2.setText(String.valueOf(computerCellValues[3][2]));
            computer_3_3.setText(String.valueOf(computerCellValues[3][3]));
            computer_3_4.setText(String.valueOf(computerCellValues[3][4]));
            computer_4_0.setText(String.valueOf(computerCellValues[4][0]));
            computer_4_1.setText(String.valueOf(computerCellValues[4][1]));
            computer_4_2.setText(String.valueOf(computerCellValues[4][2]));
            computer_4_3.setText(String.valueOf(computerCellValues[4][3]));
            computer_4_4.setText(String.valueOf(computerCellValues[4][4]));

            // Show the current drawn number
            label_number.setText(numbersCalled.get(numbersCalled.size() - 1));

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
            // TODO : Add code to restart game and save score
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
        
        // Make sure that the player can't see the cards

        // Cannot only make the overlay opaque because the UI is on the topmost
        // layer

        // Clear the text showing the current drawn number
        label_number.setText("");

        // Clear all of the text on the player's card
        player_0_0.setText("");
        player_0_1.setText("");
        player_0_2.setText("");
        player_0_3.setText("");
        player_0_4.setText("");
        player_1_0.setText("");
        player_1_1.setText("");
        player_1_2.setText("");
        player_1_3.setText("");
        player_1_4.setText("");
        player_2_0.setText("");
        player_2_1.setText("");
        player_2_3.setText("");
        player_2_4.setText("");
        player_3_0.setText("");
        player_3_1.setText("");
        player_3_2.setText("");
        player_3_3.setText("");
        player_3_4.setText("");
        player_4_0.setText("");
        player_4_1.setText("");
        player_4_2.setText("");
        player_4_3.setText("");
        player_4_4.setText("");

        // Clear all of the text on the computer's card
        computer_0_0.setText("");
        computer_0_1.setText("");
        computer_0_2.setText("");
        computer_0_3.setText("");
        computer_0_4.setText("");
        computer_1_0.setText("");
        computer_1_1.setText("");
        computer_1_2.setText("");
        computer_1_3.setText("");
        computer_1_4.setText("");
        computer_2_0.setText("");
        computer_2_1.setText("");
        computer_2_3.setText("");
        computer_2_4.setText("");
        computer_3_0.setText("");
        computer_3_1.setText("");
        computer_3_2.setText("");
        computer_3_3.setText("");
        computer_3_4.setText("");
        computer_4_0.setText("");
        computer_4_1.setText("");
        computer_4_2.setText("");
        computer_4_3.setText("");
        computer_4_4.setText("");
    }

    // Check user choices when the bingo button is pressed
    public void bingo() {
        if (checkCard("player")) {
            gameState = 1;
            gameOver();
        } else {
            gameState = -1;
            gameOver();
        }
    }
}
