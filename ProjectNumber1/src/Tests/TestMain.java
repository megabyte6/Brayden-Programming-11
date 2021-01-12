public class TestMain {
    public static void main(String[] args) {
        if (args[0].equals("test1")) {
            if (args.length == 2 && args[1].equals("show-all")) {
                testProblemOne(true);
            } else {
                testProblemOne(false);
            }
        } else if (args[0].equals("test2")) {
            if (args.length == 2 && args[1].equals("show-all")) {
                testProblemTwo(true);
            } else {
                testProblemTwo(false);
            }
        } else if (args[0].equals("test3")) {
            if (args.length == 2 && args[1].equals("show-all")) {
                testProblemThree(true);
            } else {
                testProblemThree(false);
            }
        } else {
            System.out.println("The input was not recognised");
        }
    }
    public static void testProblemOne(boolean showAllInfo) {
        String output = "ProblemOne result: No errors";
        for (int i = 0; i < StringTests.problemOne.length; i++) {
            if (showAllInfo) {
                System.out.println("Failed: " + (Main.problemOne(StringTests.problemOne[i]) != StringTests.problemOneExpected[i]));
                System.out.println(Main.problemOne(StringTests.problemOne[i]));
            }
            if (Main.problemOne(StringTests.problemOne[i]) != StringTests.problemOneExpected[i]) {
                output = "ProblemOne result: Error detected";
                break;
            }
        }
        System.out.println(output);
    }
    public static void testProblemTwo(boolean showAllInfo) {
        String output = "ProblemTwo result: No errors";
        for (int i = 0; i < StringTests.problemTwo.length; i++) {
            if (showAllInfo) {
                System.out.println("Failed: " + (Main.problemTwo(StringTests.problemTwo[i]) != StringTests.problemTwoExpected[i]));
                System.out.println(Main.problemTwo(StringTests.problemTwo[i]));
            }
            if (Main.problemTwo(StringTests.problemTwo[i]) != StringTests.problemTwoExpected[i]) {
                output = "ProblemTwo result: Error detected";
                break;
            }
        }
        System.out.println(output);
    }
    public static void testProblemThree(boolean showAllInfo) {
        String output = "ProblemThree result: No errors";
        for (int i = 0; i < StringTests.problemThree.length; i++) {
            if (showAllInfo) {
                System.out.println("Failed: " + !StringTests.problemThreeExpected[i].equals(Main.problemThree(StringTests.problemThree[i])));
                System.out.println(Main.problemThree(StringTests.problemThree[i]));
            }
            if (!StringTests.problemThreeExpected[i].equals(Main.problemThree(StringTests.problemThree[i]))) {
                output = "ProblemThree result: Error detected";
                break;
            }
        }
        System.out.println(output);
    }
}
