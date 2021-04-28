import java.util.HashMap;

// This class is used purely for the purpose of sharing information between
// controllers

public class DataStore {
    // Variables to share information between controllers
    private static HashMap<String, Integer> integers = new HashMap<String, Integer>();
    private static HashMap<String, Float> floats = new HashMap<String, Float>();
    private static HashMap<String, Double> doubles = new HashMap<String, Double>();
    private static HashMap<String, Boolean> booleans = new HashMap<String, Boolean>();
    private static HashMap<String, Character> chars = new HashMap<String, Character>();
    private static HashMap<String, String> strings = new HashMap<String, String>();

    // HashMaps with arrays to share information between controllers
    private static HashMap<String, Integer[]> integerArray = new HashMap<String, Integer[]>();
    private static HashMap<String, Float[]> floatArray = new HashMap<String, Float[]>();
    private static HashMap<String, Double[]> doubleArray = new HashMap<String, Double[]>();
    private static HashMap<String, Boolean[]> booleanArray = new HashMap<String, Boolean[]>();
    private static HashMap<String, Character[]> charArray = new HashMap<String, Character[]>();
    private static HashMap<String, String[]> stringArray = new HashMap<String, String[]>();
    
    // Make sure that no objects can be created
    private DataStore(){}

    // Add getters and setters

    public static int getInteger(String id) {
        return DataStore.integers.get(id);
    }

    // Return ID
    public static String setInteger(String id, int value) {
        DataStore.integers.put(id, value);
        return id;
    }

    public static float getFloat(String id) {
        return DataStore.floats.get(id);
    }

    // Return ID
    public static String setFloat(String id, float value) {
        DataStore.floats.put(id, value);
        return id;
    }

    public static double getDouble(String id) {
        return DataStore.doubles.get(id);
    }

    // Return ID
    public static String setDouble(String id, double value) {
        DataStore.doubles.put(id, value);
        return id;
    }

    public static boolean getBoolean(String id) {
        return DataStore.booleans.get(id);
    }

    // Return ID
    public static String setBoolean(String id, boolean value) {
        DataStore.booleans.put(id, value);
        return id;
    }

    public static char getChar(String id) {
        return DataStore.chars.get(id);
    }

    // Return ID
    public static String setChar(String id, char value) {
        DataStore.chars.put(id, value);
        return id;
    }

    public static String getString(String id) {
        return DataStore.strings.get(id);
    }

    // Return ID
    public static String setString(String id, String value) {
        DataStore.strings.put(id, value);
        return id;
    }

    public static Integer[] getIntegerArray(String id) {
        return DataStore.integerArray.get(id);
    }

    // Return ID
    // Can only accept a value of type Integer instead of int because ArrayList
    // will only accept a value of type Integer
    public static String setIntegerArray(String id, Integer[] value) {
        DataStore.integerArray.put(id, value);
        return id;
    }

    public static Float[] getFloatArray(String id) {
        return DataStore.floatArray.get(id);
    }

    // Return ID
    // Can only accept a value of type Float instead of float because ArrayList
    // will only accept a value of type Float
    public static String setFloatArray(String id, Float[] value) {
        DataStore.floatArray.put(id, value);
        return id;
    }

    public static Double[] getDoubleArray(String id) {
        return DataStore.doubleArray.get(id);
    }

    // Return ID
    // Can only accept a value of type Double instead of double because
    // ArrayList will only accept a value of type Double
    public static String setDoubleArray(String id, Double[] value) {
        DataStore.doubleArray.put(id, value);
        return id;
    }

    public static Boolean[] getBooleanArray(String id) {
        return DataStore.booleanArray.get(id);
    }

    // Return ID
    // Can only accept a value of type Boolean instead of boolean because
    // ArrayList will only accept a value of type Boolean
    public static String setBooleanArray(String id, Boolean[] value) {
        DataStore.booleanArray.put(id, value);
        return id;
    }

    public static Character[] getCharArray(String id) {
        return DataStore.charArray.get(id);
    }

    // Return ID
    // Can only accept a value of type Character instead of char because
    // ArrayList will only accept a value of type Character
    public static String setCharArray(String id, Character[] value) {
        DataStore.charArray.put(id, value);
        return id;
    }

    public static String[] getStringArray(String id) {
        return DataStore.stringArray.get(id);
    }

    // Return ID
    public static String setStringArray(String id, String[] value) {
        DataStore.stringArray.put(id, value);
        return id;
    }
}
