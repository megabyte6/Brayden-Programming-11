import java.util.Random;
import java.util.Scanner;

/*
This program plays rock-paper-scissors with you.
Just follow what is printed to the console.
*/

public class RockPaperScissors_WithLoop {
    // Main method to run main code
    public static void main(String[] args) {
        // Declare variables
        char computerChoice;
        char playerChoice;
        char acceptedResponses[] = {'r', 'p', 's'};
        int wins = 0;
        int losses = 0;
        boolean isPlaying = true;
        // Create a scanner
        Scanner scanIn = new Scanner(System.in);
        // Create random object
        Random rand = new Random();

        // Beginning of game
        // Print rules of the game
        System.out.println("Choose rock paper or scissors. 'r' for rock, 'p' for paper and 's' for scissors. To exit choose 'x'");
        while (isPlaying) {
            System.out.println("**********************************************");
            System.out.println("Wins " + wins + "\tLosses: " + losses);
            System.out.println("Player's Choice:");
            // Get player input
            try {
                playerChoice = scanIn.next().charAt(0);
            } catch (Exception e) {
                System.out.println("Invalad input. Please choose one of the options listed. Remember not to include quotes.");
                continue;
            }
            // Get computer choice
            computerChoice = acceptedResponses[rand.nextInt(3)];
            // Figure out who won
            if (playerChoice == 'x') {
                System.out.println("Thank you for playing!");
                isPlaying = false;
                continue;
            } else if (playerChoice == 'r') {
                if (computerChoice == 'r') {
                    System.out.println("Draw");
                    System.out.println("Computer Choice: rock");
                } else if (computerChoice == 'p') {
                    System.out.println("You Lose!");
                    System.out.println("Computer Choice: paper");
                    losses += 1;
                } else if (computerChoice == 's') {
                    System.out.println("You Win!");
                    System.out.println("Computer Choice: scissors");
                    wins += 1;
                }
                System.out.println("\tPlayer Choice: rock");
            } else if (playerChoice == 'p') {
                if (computerChoice == 'r') {
                    System.out.println("You Win!");
                    System.out.println("Computer Choice: rock");
                    wins += 1;
                } else if (computerChoice == 'p') {
                    System.out.println("Draw!");
                    System.out.println("Computer Choice: paper");
                } else if (computerChoice == 's') {
                    System.out.println("You Lose!");
                    System.out.println("Computer Choice: scissors");
                    losses += 1;
                }
                System.out.println("\tPlayer Choice: paper");
            } else if (playerChoice == 's') {
                if (computerChoice == 'r') {
                    System.out.println("You Lose!");
                    System.out.println("Computer Choice: rock");
                    losses += 1;
                } else if (computerChoice == 'p') {
                    System.out.println("You Win!");
                    System.out.println("Computer Choice: paper");
                    wins += 1;
                } else if (computerChoice == 's') {
                    System.out.println("Draw!");
                    System.out.println("Computer Choice: scissors");
                }
                System.out.println("\tPlayer Choice: scissors");
            } else {
                System.out.println("Invalad input. Please choose one of the options listed. Remember not to include quotes.");
            }
        }
        scanIn.close();
    }
}