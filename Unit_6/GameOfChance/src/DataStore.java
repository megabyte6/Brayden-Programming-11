import java.util.HashMap;

// This class is used purely for the purpose of sharing information between
// controllers

public class DataStore {
    // Variables to share information between controllers
    private static HashMap<String, String> strings = new HashMap<String, String>();
    private static HashMap<String, Boolean> booleans = new HashMap<String, Boolean>();
    private static HashMap<String, Integer> integers = new HashMap<String, Integer>();
    private static HashMap<String, Double> doubles = new HashMap<String, Double>();
    
    // Make sure that no objects can be created
    private DataStore(){}

    // Add getters and setters
    public static String getString(String id) {
        return DataStore.strings.get(id);
    }

    // Return ID
    public static String addString(String id, String value) {
        DataStore.strings.put(id, value);
        return id;
    }

    public static boolean getBoolean(String id) {
        return DataStore.booleans.get(id);
    }

    // Return ID
    public static String addBoolean(String id, boolean value) {
        DataStore.booleans.put(id, value);
        return id;
    }

    public static int getInteger(String id) {
        return DataStore.integers.get(id);
    }

    // Return ID
    public static String addInteger(String id, int value) {
        DataStore.integers.put(id, value);
        return id;
    }

    public static double getDouble(String id) {
        return DataStore.doubles.get(id);
    }

    // Return ID
    public static String addDouble(String id, double value) {
        DataStore.doubles.put(id, value);
        return id;
    }
}
