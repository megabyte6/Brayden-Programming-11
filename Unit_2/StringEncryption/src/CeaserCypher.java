import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

/**
* This program uses a Ceaser Cypher to encode/decode a string
* this is a standalone app
*
* @author  Brayden Chan
* @version 1.0
* @since   2020-11-19
*/

public class CeaserCypher {
    static boolean debugMode = false;
    public static void main(String args[]) {
        // Declare variables
        String inputStr = "";
        String outputStr = "";
        int shift = 0;
        boolean inputEncrypted = false;
        boolean useBasicShift = false;

        // Declare objects
        CeaserCypher ceaserCypher = new CeaserCypher();

        // Analyze user input
        for (int i = 0; i < args.length; i++) {
            if ((args[i].equals("-h") || args[i].equals("-?")) || args[i].equals("--help")) {
                // Print help message
                System.out.println("\nThis program uses a ceaser cypher to encrypt/decrypt messages");
                System.out.println("\nUsage:\tjava CeaserCypher [-? | -h | --help]");
                System.out.println("\tjava CeaserCypher (-e <string> -s <shift>) [--basic-shift]");
                System.out.println("\tjava CeaserCypher (-d <string>) [-s <shift> --basic-shift]");
                System.out.println("\nThe following options are available:");
                System.out.println("  -e, --encrypt\t\tString to be encrypted");
                System.out.println("\t\t\t(Cannot be used with '-d')");
                System.out.println("  -d, --decrypt\t\tString to be decrypted");
                System.out.println("\t\t\t(Cannot be used with '-e')");
                System.out.println("  -s, --shift\t\tShift number");
                System.out.println("  --use-basic-shift\tShifts single letter words making encryption less secure");
                System.out.println("  -h, -?, --help\tShow help");
                System.exit(1);
            } else if (args[i].equals("-v") || args[i].equals("--version")) {
                // Show version
                System.out.println("v1.0");
                System.exit(1);
            } else if (args[i].equals("-e") || args[i].equals("--encrypt")) {
                // Check if user is trying to encrypt and decrypt at the same time
                if (!inputStr.equals("")) {
                    System.out.println("You tried to encrypt and decrypt at the same time.\n'-e' and '-d' cannot be used together.");
                    System.exit(1);
                }
                // Try to set inputStr
                try {
                    inputStr = args[i+1];
                } catch (Exception e) {
                    System.out.println("The string to be encoded seems to be missing. Please try again.");
                    System.exit(1);
                }
                i++;
            } else if (args[i].equals("-d") || args[i].equals("--decrypt")) {
                // Check if user is trying to encrypt and decrypt at the same time
                if (!inputStr.equals("")) {
                    System.out.println("You tried to encrypt and decrypt at the same time.\n'-e' and '-d' cannot be used together.");
                    System.exit(1);
                }
                // Try to set inputStr
                try {
                    inputStr = args[i+1];
                    inputEncrypted = true;
                } catch (Exception e) {
                    System.out.println("The string to be encoded seems to be missing. Please try again.");
                    System.exit(1);
                }
                i++;
            } else if (args[i].equals("-s") || args[i].equals("--shift")) {
                // Try to set shift
                try {
                    shift = Integer.parseInt(args[i+1]);
                } catch (Exception e) {
                    System.out.println("The shift is not a number. Please use a positive integer for the shift.");
                    System.exit(1);
                }
                // Check if the shift is too large
                if (shift > 25) {
                    System.out.println("The shift is too large. Use a number between 0 and 26 (exclusive) as there are only 26 letters of the alphabet.");
                    System.exit(1);
                }
                i++;
            } else if (args[i].equals("--use-basic-shift")) {
                // Set the basic shift setting
                useBasicShift = true;
            } else if (args[i].equals("--debug-mode")) {
                // To resolve errors
                debugMode = true;
            } else {
                // Output error message and end program
                System.out.println("Invald argument(s). Please use '--help' for help");
                System.exit(1);
            }
        }
        // ------------------------------
        if (debugMode) {
            System.out.println("Finished analyzing input");
            System.out.println("input: " + inputStr);
            System.out.println("shift: " + shift);
            System.out.println("Input encrypted: " + inputEncrypted);
            System.out.println("Using basic shift: " + useBasicShift);
        }
        // ------------------------------
        // Normalize input
        inputStr = inputStr.toLowerCase();

        // Decide whether to encode or decode the string
        // Then encode or decode the string

        // Encrypt string
        if (inputStr != "" && !inputEncrypted) {
            // ------------------------------
            if (debugMode) System.out.println("Encrypting string");
            // ------------------------------
            // Make sure that the shift is not 0
            if (shift == 0) {
                System.out.println("The shift is not specified. Please specify the shift.");
                System.exit(1);
            }
            // Check if basicShift is enabled then encrypt the string
            if (useBasicShift) {
                outputStr = ceaserCypher.encrypt(inputStr, shift, true);
            } else {
                outputStr = ceaserCypher.encrypt(inputStr, shift);
            }
        // Decrypt string
        } else if (inputStr != "" && inputEncrypted) {
            // ------------------------------
            if (debugMode) System.out.println("Decrypting string");
            // ------------------------------
            // Check if no shift was specified
            if (shift == 0) {
                // Check if basicShift is enabled then decrypt the string
                if (useBasicShift) {
                    outputStr = ceaserCypher.decrypt(inputStr, true);
                } else {
                    outputStr = ceaserCypher.decrypt(inputStr);
                }
            } else {
                // Check if basicShift is enabled then decrypt the string
                if (useBasicShift) {
                    outputStr = ceaserCypher.decrypt(inputStr, shift, true);
                } else {
                    outputStr = ceaserCypher.decrypt(inputStr, shift);
                }
            }
        } else {
            // Output input error
            System.out.println("You didn't give any arguments. Please add and some arguments. Add '--help' for help");
            System.exit(1);
        }
        // Print out the final result
        System.out.println(outputStr);
    }


    private String encrypt(String uncodedStr, int shift) {
        // ------------------------------
        if (debugMode) System.out.println("Encrypt_1 activated");
        // ------------------------------
        CeaserCypher ceaserCypher = new CeaserCypher();
        return ceaserCypher.shiftString(uncodedStr, shift, false, false);
    }


    private String encrypt(String uncodedStr, int shift, boolean useBasicShift) {
        // ------------------------------
        if (debugMode) System.out.println("Encrypt_2 activated");
        // ------------------------------
        String encryptedStr;
        CeaserCypher ceaserCypher = new CeaserCypher();
        if (useBasicShift) {
            encryptedStr = ceaserCypher.shiftString(uncodedStr, shift, true, false);
        } else {
            encryptedStr = ceaserCypher.shiftString(uncodedStr, shift, false, false);
        }
        return encryptedStr;
    }


    private String decrypt(String encodedStr) {
        // ------------------------------
        if (debugMode) System.out.println("Decrypt_1 activated");
        // ------------------------------
        String encryptedStr = "";
        CeaserCypher ceaserCypher = new CeaserCypher();
        for (int i = 1; i < 26; i++) {
            encryptedStr = encryptedStr + ceaserCypher.shiftString(encodedStr, i, false, false) + "\n";
        }
		return encryptedStr;
    }


    private String decrypt(String encodedStr, int shift) {
        // ------------------------------
        if (debugMode) System.out.println("Decrypt_2 activated");
        // ------------------------------
        CeaserCypher ceaserCypher = new CeaserCypher();
        return ceaserCypher.shiftString(encodedStr, shift, false, true);
    }


    private String decrypt(String encodedStr, boolean useBasicShift) {
        // ------------------------------
        if (debugMode) System.out.println("Decrypt_3 activated");
        // ------------------------------
        String encryptedStr = "";
        CeaserCypher ceaserCypher = new CeaserCypher();
        if (useBasicShift) {
            for (int i = 1; i < 26; i++) {
                encryptedStr = encryptedStr + ceaserCypher.shiftString(encodedStr, i, true, false) + "\n";
            }
        } else {
            for (int i = 1; i < 26; i++) {
                encryptedStr = encryptedStr + ceaserCypher.shiftString(encodedStr, i, false, false) + "\n";
            }
        }
        return encryptedStr;
    }


    private String decrypt(String encodedStr, int shift, boolean useBasicShift) {
        // ------------------------------
        if (debugMode) System.out.println("Decrypt_4 activated");
        // ------------------------------
        String decryptedStr;
        CeaserCypher ceaserCypher = new CeaserCypher();
        if (useBasicShift) {
            decryptedStr = ceaserCypher.shiftString(encodedStr, shift, true, true);
        } else {
            decryptedStr = ceaserCypher.shiftString(encodedStr, shift, false, true);
        }
        return decryptedStr;
    }


    private String shiftString(String inputStr, int shift, boolean shiftSingleLetterWords, boolean reverseShift) {
        // ------------------------------
        if (debugMode) {
            System.out.println("\nshiftString method activated");
            System.out.println("Shift string: " + inputStr);
            System.out.println("Shift: " + shift);
        }
        // ------------------------------

        // Declare variables
        String currentChar;
        int index = 0;
        String letters[];
        String numbers[];
        if (reverseShift) {
            letters = new String[]{
                "z","y","x","w","v","u","t","s","r","q","p","o","n",
                "m","l","k","j","i","h","g","f","e","d","c","b","a"};
            numbers = new String[]{"0","9","8","7","6","5","4","3","9","1"};
        } else {
            letters = new String[]{
                "a","b","c","d","e","f","g","h","i","j","k","l","m",
                "n","o","p","q","r","s","t","u","v","w","x","y","z"};
            numbers = new String[]{"1","2","3","4","5","6","7","8","9","0"};
        }
        List <String> lettersList = Arrays.asList(letters);
        List <String> numbersList = Arrays.asList(numbers);
        StringBuilder outputStr = new StringBuilder(inputStr.length());
        // ------------------------------
        if (debugMode) System.out.println("Finished declaring variables");
        // ------------------------------
        // Run if the input string is more than 1 character long
        if (inputStr.length() > 1) {
            // ------------------------------
            if (debugMode) System.out.println("Input greater than one character");
            // ------------------------------
            // Loop through all the characters given
            for (int i = 0; i < inputStr.length(); i++) {
                // Current character selected
                currentChar = String.valueOf(inputStr.charAt(i));
                // ------------------------------
                if (debugMode) {
                    if (i + 1 > inputStr.length() - 1) {
                        System.out.println("current character: " + currentChar);
                    } else {
                        System.out.println("current character: " + currentChar + "\nnext character: " + inputStr.charAt(i + 1));
                    }
                }
                // ------------------------------
                // Continue the loop if the character is a space
                if (currentChar.equals(" ")) {
                    outputStr.append(" ");
                    continue;
                // Check if the letters list contains the current alphanumeric character
                } else if (lettersList.contains(currentChar)) {
                    // ------------------------------
                    if (debugMode) System.out.println("Current character is a letter");
                    // ------------------------------
                    // Find the index position of the letter in the array
                    for (int i2 = 0; i2 < letters.length; i2++) {
                        if (debugMode) System.out.println(letters[i2] + " : " + currentChar.equals(letters[i2]));
                        if (currentChar.equals(letters[i2])) {
                            index = i2;
                            break;
                        }
                    }
                    // ------------------------------
                    if (debugMode) System.out.println("Index: " + index);
                    // ------------------------------
                    // Check if letter is a single letter word
                    // if so, skip letter
                    if (!shiftSingleLetterWords) {
                        if (currentChar.equals("a") || currentChar.equals("i")) {
                            if ((i == 0 && inputStr.charAt(i + 1) == ' ') || (inputStr.charAt(i - 1) == ' ' && inputStr.charAt(i + 1) == ' ')) {
                                outputStr.append(letters[index] + " ");
                                i++;
                                // ------------------------------
                                if (debugMode) System.out.println("Skipped single letter word");
                                // ------------------------------
                                continue;
                            }
                        }
                    }
                    // Check if output needs to wrap around
                    if ((index + shift) > (letters.length - 1)) {
                        index -= letters.length;
                        // ------------------------------
                        if (debugMode) System.out.println("Wrapped letter");
                        // ------------------------------
                    }
                    // Set the output
                    outputStr.append(letters[index + shift]);
                // Check if the numbers list contains the current alphanumeric character
                } else if (numbersList.contains(String.valueOf(currentChar))) {
                    // ------------------------------
                    if (debugMode) System.out.println("Current character is a number");
                    // ------------------------------
                    // Find the index position of then number in the array
                    for (int i2 = 0; i2 < numbers.length; i2++) {
                        if (currentChar.equals(numbers[i2])) {
                            index = i2;
                            break;
                        }
                    }
                    // ------------------------------
                    if (debugMode) System.out.println("Index: " + index);
                    // ------------------------------
                    // Check if output needs to wrap around
                    if ((index + shift) > (numbers.length - 1)) {
                        index -= numbers.length;
                        // ------------------------------
                        if (debugMode) System.out.println("Wrapped number");
                        // ------------------------------
                    }
                    // Set the output
                    outputStr.append(numbers[index + shift]);
                } else {
                    continue;
                }
                // ------------------------------
                if (debugMode) System.out.println("Loop run: " + (i + 1) + "\n");
                // ------------------------------
            }
        // Run if the input string is only one character long
        } else if (inputStr.length() == 1) {
            // ------------------------------
            if (debugMode) System.out.println("Input is one character long");
            // ------------------------------
            // Check if the letters list contains inputStr
            if (lettersList.contains(inputStr)) {
                // Find the index position of the letter in the array
                for (int i = 0; i < letters.length; i++) {
                    if (letters[i].equals(inputStr)) {
                        index = i;
                        break;
                    }
                }
                // Check if output needs to wrap around
                // Check if the index + shift is greater than 25
                if ((index + shift) > (letters.length - 1)) {
                    index -= letters.length;
                }
                // Set the output
                outputStr.append(letters[index + shift]);
            // Check if the numbers list contains inputStr
            } else if (numbersList.contains(inputStr)) {
                // Find the index position of the number in the array
                for (int i = 0; i < numbers.length; i++) {
                    if (numbers[i].equals(inputStr)) {
                        index = i;
                        break;
                    }
                }
                // Check if output needs to wrap around
                // Check if the index + shift is greater than 9
                if ((index + shift) > (numbers.length - 1)) {
                    index -= numbers.length;
                }
                // Set the output
                outputStr.append(numbers[index + shift]);
            }
        } else {
            outputStr.append("Input needs to be at least one character long");
        }
        // Return the output
        return outputStr.toString();
    }
}