import java.util.Arrays;

public class Main {
    public static int problemOne(String s){
        String vowels[] = {"a", "e", "i", "o", "u"};
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Arrays.asList(vowels).contains(String.valueOf(s.charAt(i)))) {
                answer++;
            }
        }
        return answer;
    }
    public static int problemTwo(String s){
        int answer = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.substring(i, i + 3).equals("bob")) {
                answer++;
            }
        }
        return answer;
    }
    public static String problemThree(String s){
        String answer;
        for (int i = s.length(); i > 0; i--) {
            for (int i2 = 0; i2 < s.length() - i + 1; i2++) {
                answer = s.substring(i2, i2 + i);
                if (isInAlphabeticalOrder(answer)) {
                    return answer;
                }
            }
        }
        return "None of the results matched";
    }
    static boolean isInAlphabeticalOrder(String s) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (alphabet.indexOf(String.valueOf(s.charAt(i))) > index) {
                index = alphabet.indexOf(String.valueOf(s.charAt(i)));
            } else if (alphabet.indexOf(String.valueOf(s.charAt(i))) < index) {
                return false;
            }
        }
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
