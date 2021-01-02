import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

/*
This is the same as Rock-Paper-Scissors but with nicer graphics and options
Thanks to wynand1004 for the ascii art hands
Link to ascii art hands: https://gist.github.com/wynand1004/b5c521ea8392e9c6bfe101b025c39abe
*/

/*
    ____             __      ____                           _____      _                          
   / __ \____  _____/ /__   / __ \____ _____  ___  _____   / ___/_____(_)_____________  __________
  / /_/ / __ \/ ___/ //_/  / /_/ / __ `/ __ \/ _ \/ ___/   \__ \/ ___/ / ___/ ___/ __ \/ ___/ ___/
 / _, _/ /_/ / /__/ ,<    / ____/ /_/ / /_/ /  __/ /      ___/ / /__/ (__  |__  ) /_/ / /  (__  ) 
/_/ |_|\____/\___/_/|_|  /_/    \__,_/ .___/\___/_/      /____/\___/_/____/____/\____/_/  /____/  
                                    /_/                                                           
*/

public class RockPaperScissors_v2 {
    public static void main(String[] args) {
        String computerChoice;
        String playerChoice;
        boolean isFinished = false;
        Scanner scannerIn = new Scanner(System.in);
        Random rand = new Random();
        String acceptedResponses[] = {"rock", "paper", "scissors", "r", "p", "s", "quit", "exit", "goodbye", "good game", "well played"};

        // Print intro
        printAscii("intro");

        // Main game loop
        while (!isFinished) {
            // Add extra space
            System.out.println("---------------------------------------------------------");
            System.out.println("Enter 'rock', 'paper', 'scissors', or type 'quit' to quit");
            // Get player input
            playerChoice = scannerIn.nextLine();
            playerChoice = playerChoice.toLowerCase();

            // Analyze player input
            if (!Arrays.asList(acceptedResponses).contains(playerChoice)) {
                System.out.println("That is not one of the options given. Please use one of the options given. Remember not to include quotes.");
                // Add extra space
                System.out.println();
                continue;
            } else {
                // Add extra space
                System.out.println();
                switch (playerChoice) {
                    case "quit":
                        System.out.println("Goodbye");
                        isFinished = true;
                        continue;
                    case "exit":
                        System.out.println("Goodbye");
                        isFinished = true;
                        continue;
                    case "goodbye":
                        System.out.println("Bye! Have a good day.");
                        isFinished = true;
                        continue;
                    case "good game":
                        System.out.println("Same to you!");
                        isFinished = true;
                        continue;
                    case "well played":
                        System.out.println("Thank you, same to you!");
                        isFinished = true;
                        continue;
                    case "r":
                        playerChoice = "rock";
                    case "p":
                        playerChoice = "paper";
                    case "s":
                        playerChoice = "scissors";
                    default:
                }
            }

            // Computer randomly chooses an option
            computerChoice = acceptedResponses[rand.nextInt(3)];
            
            System.out.println("You chose:");
            printAscii(playerChoice);
            System.out.println();
            System.out.println("The computer chose:");
            printAscii(computerChoice);
            System.out.println();
            
            // Decide who won
            if (computerChoice.equals("rock")) {
                if (playerChoice.equals("rock")) {
                    System.out.println("It's a tie!");
                } else if (playerChoice.equals("paper")) {
                    System.out.println("You Won :)");
                } else if (playerChoice.equals("scissors")) {
                    System.out.println("You Lost :(");
                }
            } else if (computerChoice.equals("paper")) {
                if (playerChoice.equals("rock")) {
                    System.out.println("You Lost :(");
                } else if (playerChoice.equals("paper")) {
                    System.out.println("It's a tie!");
                } else if (playerChoice.equals("scissors")) {
                    System.out.println("You Won :)");
                }
            } else if (computerChoice.equals("scissors")) {
                if (playerChoice.equals("rock")) {
                    System.out.println("You Won :)");
                } else if (playerChoice.equals("paper")) {
                    System.out.println("You Lost :(");
                } else if (playerChoice.equals("scissors")) {
                    System.out.println("It's a tie!");
                }
            }
        }
        scannerIn.close();
    }
    
    public static void printAscii(String choice) {
        if (choice.equals("rock")) {
            System.out.println("    _______       ");
            System.out.println("---'   ____)      ");
            System.out.println("      (_____)     ");
            System.out.println("      (_____)     ");
            System.out.println("      (____)      ");
            System.out.println("---.__(___)       ");
        } else if (choice.equals("paper")) {
            System.out.println("     _______      ");
            System.out.println("---'    ____)____ ");
            System.out.println("           ______)");
            System.out.println("          _______)");
            System.out.println("         _______) ");
            System.out.println("---.__________)   ");
        } else if (choice.equals("scissors")) {
            System.out.println("    _______       ");
            System.out.println("---'   ____)____  ");
            System.out.println("          ______) ");
            System.out.println("       __________)");
            System.out.println("      (____)      ");
            System.out.println("---.__(___)       ");
        } else if (choice.equals("intro")) {
            System.out.println("    ____             __      ____                           _____      _                          ");
            System.out.println("   / __ \\____  _____/ /__   / __ \\____ _____  ___  _____   / ___/_____(_)_____________  __________");
            System.out.println("  / /_/ / __ \\/ ___/ //_/  / /_/ / __ `/ __ \\/ _ \\/ ___/   \\__ \\/ ___/ / ___/ ___/ __ \\/ ___/ ___/");
            System.out.println(" / _, _/ /_/ / /__/ ,<    / ____/ /_/ / /_/ /  __/ /      ___/ / /__/ (__  |__  ) /_/ / /  (__  ) ");
            System.out.println("/_/ |_|\\____/\\___/_/|_|  /_/    \\__,_/ .___/\\___/_/      /____/\\___/_/____/____/\\____/_/  /____/  ");
            System.out.println("                                    /_/                                                           ");
            System.out.println();
            System.out.println("Welcome to Rock-Paper-Scissors!");
            System.out.println("The rules are: rock beats scissors; scissors beats paper; paper beats rock");
        }
    }
}