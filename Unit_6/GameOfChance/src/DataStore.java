import java.util.ArrayList;

// This class is used purely for the purpose of sharing information between
// controllers

public class DataStore {
    // Variables to share information between controllers
    private static ArrayList<String> strings = new ArrayList<String>();
    private static ArrayList<Boolean> booleans = new ArrayList<Boolean>();
    private static ArrayList<Integer> integers = new ArrayList<Integer>();
    private static ArrayList<Double> doubles = new ArrayList<Double>();

    public static int timerDuration;
    
    // Make sure that no objects can be created
    private DataStore(){}

    // Add getters and setters
    public static String getString(int index) {
        return DataStore.strings.get(index);
    }

    // Return index
    public static int addString(String value) {
        DataStore.strings.add(value);
        return DataStore.strings.size() - 1;
    }

    public static boolean getBoolean(int index) {
        return DataStore.booleans.get(index);
    }

    // Return index
    public static int addBoolean(boolean value) {
        DataStore.booleans.add(value);
        return DataStore.booleans.size() - 1;
    }

    public static int getInteger(int index) {
        return DataStore.integers.get(index);
    }

    // Return index
    public static int addInteger(int value) {
        DataStore.integers.add(value);
        return DataStore.integers.size() - 1;
    }

    public static double getDouble(int index) {
        return DataStore.doubles.get(index);
    }

    // Return index
    public static int addDouble(double value) {
        DataStore.doubles.add(value);
        return DataStore.doubles.size() - 1;
    }
}
