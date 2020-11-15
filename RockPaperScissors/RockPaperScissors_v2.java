/*
Thanks to wynand1004 for the ascii art images
Link to ascii art: https://gist.github.com/wynand1004/b5c521ea8392e9c6bfe101b025c39abe
*/
public class RockPaperScissors_v2 {
    public static void main(String[] args) {
        String computerChoice = "";
        String playerChoice = "";
        boolean isFinished = false;
    }
    
    public static void printChoice(String choice) {
        if (choice.equals("rock")) {
            System.out.println("    _______");
            System.out.println("---'   ____)");
            System.out.println("      (_____)");
            System.out.println("      (_____)");
            System.out.println("      (____)");
            System.out.println("---.__(___)");
        } else if (choice.equals("paper")) {
            System.out.println("     _______");
            System.out.println("---'    ____)____");
            System.out.println("           ______)");
            System.out.println("          _______)");
            System.out.println("         _______)");
            System.out.println("---.__________)");
        } else if (choice.equals("scissors")) {
            System.out.println("    _______");
            System.out.println("---'   ____)____");
            System.out.println("          ______)");
            System.out.println("       __________)");
            System.out.println("      (____)");
            System.out.println("---.__(___)");
        }
    }
}