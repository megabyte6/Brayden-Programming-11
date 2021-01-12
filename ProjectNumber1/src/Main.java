import java.util.Arrays;

public class Main {
    public static int problemOne(String s) {
        // Declares variables
        String vowels[] = {"a", "e", "i", "o", "u"};
        int answer = 0;
        // Loops through each letter in the input and
        // checks if that character is a vowel
        for (int i = 0; i < s.length(); i++) {
            if (Arrays.asList(vowels).contains(String.valueOf(s.charAt(i)))) {
                // Increment the answer variable by 1
                answer++;
            }
        }
        return answer;
    }
    public static int problemTwo(String s) {
        // Declares variable
        int answer = 0;
        // Loops through each 3-letter string in the input
        // and checks if that 3-letter string is equal to "bob"
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.substring(i, i + 3).equals("bob")) {
                // Increment the answer variable by 1
                answer++;
            }
        }
        return answer;
    }
    public static String problemThree(String s) {
        // Declares variable
        String answer;
        // Loops through the different length substrings in the input string
        for (int i = s.length(); i > 0; i--) {
            // For each length, loop through each n-letter substrings
            // where n is equal to i in the previous loop
            for (int i2 = 0; i2 < s.length() - i + 1; i2++) {
                // Set answer to the current substring to be tested
                answer = s.substring(i2, i2 + i);
                // Test weather answer is in alphabetical order
                if (isInAlphabeticalOrder(answer)) {
                    return answer;
                }
            }
        }
        // If none match, return string below
        return "None of the results matched";
    }
    static boolean isInAlphabeticalOrder(String s) {
        // Declare variables
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int index = 0;
        // Loop through each letter in the input string
        for (int i = 0; i < s.length(); i++) {
            // Checks if the index position of the current letter is greater
            // than the index position of the last letter
            if (alphabet.indexOf(String.valueOf(s.charAt(i))) > index) {
                // Update the index varaible to the new highest index
                index = alphabet.indexOf(String.valueOf(s.charAt(i)));
            } else if (alphabet.indexOf(String.valueOf(s.charAt(i))) < index) {
                return false;
            }
        }
        // If everything checks out, the input string must be in alphabetical order
        return true;
    }
    public static void main(String[] args) {
        /*
        Set s to a string and run your method using s as the parameter
        Run your method in a println statement to determine what the output was
        Once you think you have it working try running the tests.
        The tests will put your method through several different Strings to test
        all possible cases.  If you have 100% success then there is no bugs in your methods.
         */
        String s;
    }
}
