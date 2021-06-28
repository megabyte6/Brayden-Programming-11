package com.github.megabyte6.utils;

import java.util.HashMap;

// This class is used purely for the purpose of sharing information between
// controllers

public class Database {
    // Variables to share information between controllers
    private static HashMap<String, Integer> integers = new HashMap<String, Integer>();
    private static HashMap<String, Long> longs = new HashMap<String, Long>();
    private static HashMap<String, Float> floats = new HashMap<String, Float>();
    private static HashMap<String, Double> doubles = new HashMap<String, Double>();
    private static HashMap<String, Boolean> booleans = new HashMap<String, Boolean>();
    private static HashMap<String, Character> chars = new HashMap<String, Character>();
    private static HashMap<String, String> strings = new HashMap<String, String>();

    // HashMaps with arrays to share information between controllers
    private static HashMap<String, Integer[]> integerArray = new HashMap<String, Integer[]>();
    private static HashMap<String, Long[]> longArray = new HashMap<String, Long[]>();
    private static HashMap<String, Float[]> floatArray = new HashMap<String, Float[]>();
    private static HashMap<String, Double[]> doubleArray = new HashMap<String, Double[]>();
    private static HashMap<String, Boolean[]> booleanArray = new HashMap<String, Boolean[]>();
    private static HashMap<String, Character[]> charArray = new HashMap<String, Character[]>();
    private static HashMap<String, String[]> stringArray = new HashMap<String, String[]>();

    // Make sure that no objects can be created
    protected Database() {}

    // Add getters and setters

    /**
     * Get the int that corresponds with the ID given
     * 
     * @param id    The ID of the value to obtain
     * @return
     * The int that matches the ID
     */
    public static int getInteger(String id) {
        return Database.integers.get(id);
    }

    /**
     * Store a new value
     * 
     * @param id    The ID of the value
     * @param value The value to be stored
     * @return
     * The ID given
     */
    public static String setInteger(String id, int value) {
        Database.integers.put(id, value);
        return id;
    }

    /**
     * Change a value
     * 
     * @param id        The ID of the value to change
     * @param newValue  The new value to change to
     * @return
     * {@code true} if the value with the ID given exists, or {@code false} if
     * the ID given doesn't
     */
    public static boolean changeInteger(String id, int newValue) {
        if (Database.integers.get(id) == null) {
            return false;
        } else {
            Database.integers.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a value
     * 
     * @param id    The current ID of the value
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeIntegerID(String id, String newID) {
        if (Database.integers.get(id) == null
                || Database.integers.get(newID) != null) {
            return false;
        } else {
            Database.integers.put(newID, Database.integers.get(id));
            Database.integers.remove(id);
            return true;
        }
    }

    /**
     * Remove the value corresponding to the ID given
     * 
     * @param id    The ID of the value to remove
     * @return
     * {@code true} if the value corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeInteger(String id) {
        if (Database.integers.get(id) == null) {
            return false;
        } else {
            Database.integers.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean integerExists(String id) {
        return Database.integers.get(id) == null ? false : true;
    }

    /**
     * Get the long that corresponds with the ID given
     * 
     * @param id    The ID of the value to obtain
     * @return
     * The long that matches the ID
     */
    public static long getLong(String id) {
        return Database.longs.get(id);
    }

    /**
     * Store a new value
     * 
     * @param id    The ID of the value
     * @param value The value to be stored
     * @return
     * The ID given
     */
    public static String setLong(String id, long value) {
        Database.longs.put(id, value);
        return id;
    }

    /**
     * Change a value
     * 
     * @param id        The ID of the value to change
     * @param newValue  The new value to change to
     * @return
     * {@code true} if the value with the ID given exists, or {@code false} if
     * the ID given doesn't
     */
    public static boolean changeLong(String id, long newValue) {
        if (Database.longs.get(id) == null) {
            return false;
        } else {
            Database.longs.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a value
     * 
     * @param id    The current ID of the value
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeLongID(String id, String newID) {
        if (Database.longs.get(id) == null
                || Database.longs.get(newID) != null) {
            return false;
        } else {
            Database.longs.put(newID, Database.longs.get(id));
            Database.longs.remove(id);
            return true;
        }
    }

    /**
     * Remove the value corresponding to the ID given
     * 
     * @param id    The ID of the value to remove
     * @return
     * {@code true} if the value corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeLong(String id) {
        if (Database.longs.get(id) == null) {
            return false;
        } else {
            Database.longs.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean longExists(String id) {
        return Database.longs.get(id) == null ? false : true;
    }

    /**
     * Get the float that corresponds with the ID given
     * 
     * @param id    The ID of the value to obtain
     * @return
     * The float that matches the ID
     */
    public static float getFloat(String id) {
        return Database.floats.get(id);
    }

    /**
     * Store a new value
     * 
     * @param id    The ID of the value
     * @param value The value to be stored
     * @return
     * The ID given
     */
    public static String setFloat(String id, float value) {
        Database.floats.put(id, value);
        return id;
    }

    /**
     * Change a value
     * 
     * @param id        The ID of the value to change
     * @param newValue  The new value to change to
     * @return
     * {@code true} if the value with the ID given exists, or {@code false} if
     * the ID given doesn't
     */
    public static boolean changeFloat(String id, float newValue) {
        if (Database.floats.get(id) == null) {
            return false;
        } else {
            Database.floats.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a value
     * 
     * @param id    The current ID of the value
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeFloatID(String id, String newID) {
        if (Database.floats.get(id) == null
                || Database.floats.get(newID) != null) {
            return false;
        } else {
            Database.floats.put(newID, Database.floats.get(id));
            Database.floats.remove(id);
            return true;
        }
    }

    /**
     * Remove the value corresponding to the ID given
     * 
     * @param id    The ID of the value to remove
     * @return
     * {@code true} if the value corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeFloat(String id) {
        if (Database.floats.get(id) == null) {
            return false;
        } else {
            Database.floats.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean floatExists(String id) {
        return Database.floats.get(id) == null ? false : true;
    }

    /**
     * Get the double that corresponds with the ID given
     * 
     * @param id    The ID of the value to obtain
     * @return
     * The double that matches the ID
     */
    public static double getDouble(String id) {
        return Database.doubles.get(id);
    }

    /**
     * Store a new value
     * 
     * @param id    The ID of the value
     * @param value The value to be stored
     * @return
     * The ID given
     */
    public static String setDouble(String id, double value) {
        Database.doubles.put(id, value);
        return id;
    }

    /**
     * Change a value
     * 
     * @param id        The ID of the value to change
     * @param newValue  The new value to change to
     * @return
     * {@code true} if the value with the ID given exists, or {@code false} if
     * the ID given doesn't
     */
    public static boolean changeDouble(String id, double newValue) {
        if (Database.doubles.get(id) == null) {
            return false;
        } else {
            Database.doubles.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a value
     * 
     * @param id    The current ID of the value
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeDoubleID(String id, String newID) {
        if (Database.doubles.get(id) == null
                || Database.doubles.get(newID) != null) {
            return false;
        } else {
            Database.doubles.put(newID, Database.doubles.get(id));
            Database.doubles.remove(id);
            return true;
        }
    }

    /**
     * Remove the value corresponding to the ID given
     * 
     * @param id    The ID of the value to remove
     * @return
     * {@code true} if the value corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeDouble(String id) {
        if (Database.doubles.get(id) == null) {
            return false;
        } else {
            Database.doubles.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean doubleExists(String id) {
        return Database.doubles.get(id) == null ? false : true;
    }

    /**
     * Get the boolean that corresponds with the ID given
     * 
     * @param id    The ID of the value to obtain
     * @return
     * The boolean that matches the ID
     */
    public static boolean getBoolean(String id) {
        return Database.booleans.get(id);
    }

    /**
     * Store a new value
     * 
     * @param id    The ID of the value
     * @param value The value to be stored
     * @return
     * The ID given
     */
    public static String setBoolean(String id, boolean value) {
        Database.booleans.put(id, value);
        return id;
    }

    /**
     * Change a value
     * 
     * @param id        The ID of the value to change
     * @param newValue  The new value to change to
     * @return
     * {@code true} if the value with the ID given exists, or {@code false} if
     * the ID given doesn't
     */
    public static boolean changeBoolean(String id, boolean newValue) {
        if (Database.booleans.get(id) == null) {
            return false;
        } else {
            Database.booleans.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a value
     * 
     * @param id    The current ID of the value
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeBooleanID(String id, String newID) {
        if (Database.booleans.get(id) == null
                || Database.booleans.get(newID) != null) {
            return false;
        } else {
            Database.booleans.put(newID, Database.booleans.get(id));
            Database.booleans.remove(id);
            return true;
        }
    }

    /**
     * Remove the value corresponding to the ID given
     * 
     * @param id    The ID of the value to remove
     * @return
     * {@code true} if the value corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeBoolean(String id) {
        if (Database.booleans.get(id) == null) {
            return false;
        } else {
            Database.booleans.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean booleanExists(String id) {
        return Database.booleans.get(id) == null ? false : true;
    }

    /**
     * Get the char that corresponds with the ID given
     * 
     * @param id    The ID of the value to obtain
     * @return
     * The char that matches the ID
     */
    public static char getCharacter(String id) {
        return Database.chars.get(id);
    }

    /**
     * Store a new value
     * 
     * @param id    The ID of the value
     * @param value The value to be stored
     * @return
     * The ID given
     */
    public static String setCharacter(String id, char value) {
        Database.chars.put(id, value);
        return id;
    }

    /**
     * Change a value
     * 
     * @param id        The ID of the value to change
     * @param newValue  The new value to change to
     * @return
     * {@code true} if the value with the ID given exists, or {@code false} if
     * the ID given doesn't
     */
    public static boolean changeCharacter(String id, char newValue) {
        if (Database.chars.get(id) == null) {
            return false;
        } else {
            Database.chars.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a value
     * 
     * @param id    The current ID of the value
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeCharacterID(String id, String newID) {
        if (Database.chars.get(id) == null
                || Database.chars.get(newID) != null) {
            return false;
        } else {
            Database.chars.put(newID, Database.chars.get(id));
            Database.chars.remove(id);
            return true;
        }
    }

    /**
     * Remove the value corresponding to the ID given
     * 
     * @param id    The ID of the value to remove
     * @return
     * {@code true} if the value corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeCharacter(String id) {
        if (Database.chars.get(id) == null) {
            return false;
        } else {
            Database.chars.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean charExists(String id) {
        return Database.chars.get(id) == null ? false : true;
    }

    /**
     * Get the String that corresponds with the ID given
     * 
     * @param id    The ID of the value to obtain
     * @return
     * The String that matches the ID
     */
    public static String getString(String id) {
        return Database.strings.get(id);
    }

    /**
     * Store a new value
     * 
     * @param id    The ID of the value
     * @param value The value to be stored
     * @return
     * The ID given
     */
    public static String setString(String id, String value) {
        Database.strings.put(id, value);
        return id;
    }

    /**
     * Change a value
     * 
     * @param id        The ID of the value to change
     * @param newValue  The new value to change to
     * @return
     * {@code true} if the value with the ID given exists, or {@code false} if
     * the ID given doesn't
     */
    public static boolean changeString(String id, String newValue) {
        if (Database.strings.get(id) == null) {
            return false;
        } else {
            Database.strings.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a value
     * 
     * @param id    The current ID of the value
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeStringID(String id, String newID) {
        if (Database.strings.get(id) == null
                || Database.strings.get(newID) != null) {
            return false;
        } else {
            Database.strings.put(newID, Database.strings.get(id));
            Database.strings.remove(id);
            return true;
        }
    }

    /**
     * Remove the value corresponding to the ID given
     * 
     * @param id    The ID of the value to remove
     * @return
     * {@code true} if the value corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeString(String id) {
        if (Database.strings.get(id) == null) {
            return false;
        } else {
            Database.strings.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean stringExists(String id) {
        return Database.strings.get(id) == null ? false : true;
    }

    /**
     * Get the Integer array that corresponds with the ID given
     * 
     * @param id    The ID of the array to obtain
     * @return
     * The Integer array that matches the ID
     */
    public static Integer[] getIntegerArray(String id) {
        return Database.integerArray.get(id);
    }

    /**
     * Store a new Integer array
     * 
     * Can only accept an array of type Integer instead of int because
     * ArrayList will only accept an array of Objects
     * 
     * @param id    The ID of the new array
     * @param value The array to be stored
     * @return
     * The ID of the new array
     */
    public static String setIntegerArray(String id, Integer[] value) {
        Database.integerArray.put(id, value);
        return id;
    }

    /**
     * Change an Integer array
     * 
     * @param id        The ID of the array to change
     * @param newValue  The new array to replace with
     * @return
     * {@code true} if an array with the ID given exists, or {@code false} if
     * it doesn't
     */
    public static boolean changeIntegerArray(String id, Integer[] newValue) {
        if (Database.integerArray.get(id) == null) {
            return false;
        } else {
            Database.integerArray.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of an Integer array
     * 
     * @param id    The current ID of the array
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeIntegerArrayID(String id, String newID) {
        if (Database.integerArray.get(id) == null
                || Database.integerArray.get(newID) != null) {
            return false;
        } else {
            Database.integerArray.put(newID, Database.integerArray.get(id));
            Database.integerArray.remove(id);
            return true;
        }
    }

    /**
     * Remove the array corresponding to the ID given
     * 
     * @param id    The ID of the array to remove
     * @return
     * {@code true} if the array corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeIntegerArray(String id) {
        if (Database.integerArray.get(id) == null) {
            return false;
        } else {
            Database.integerArray.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean integerArrayExists(String id) {
        return Database.integerArray.get(id) == null ? false : true;
    }

    /**
     * Get the Long array that corresponds with the ID given
     * 
     * @param id    The ID of the array to obtain
     * @return
     * The Long array that matches the ID
     */
    public static Long[] getLongArray(String id) {
        return Database.longArray.get(id);
    }

    /**
     * Store a new Long array
     * 
     * Can only accept an array of type Long instead of long because
     * ArrayList will only accept an array of Objects
     * 
     * @param id    The ID of the new array
     * @param value The array to be stored
     * @return
     * The ID of the new array
     */
    public static String setLongArray(String id, Long[] value) {
        Database.longArray.put(id, value);
        return id;
    }

    /**
     * Change a Long array
     * 
     * @param id        The ID of the array to change
     * @param newValue  The new array to replace with
     * @return
     * {@code true} if an array with the ID given exists, or {@code false} if
     * it doesn't
     */
    public static boolean changeLongArray(String id, Long[] newValue) {
        if (Database.longArray.get(id) == null) {
            return false;
        } else {
            Database.longArray.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a Long array
     * 
     * @param id    The current ID of the array
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeLongArrayID(String id, String newID) {
        if (Database.longArray.get(id) == null
                || Database.longArray.get(newID) != null) {
            return false;
        } else {
            Database.longArray.put(newID, Database.longArray.get(id));
            Database.longArray.remove(id);
            return true;
        }
    }

    /**
     * Remove the array corresponding to the ID given
     * 
     * @param id    The ID of the array to remove
     * @return
     * {@code true} if the array corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeLongArray(String id) {
        if (Database.longArray.get(id) == null) {
            return false;
        } else {
            Database.longArray.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean longArrayExists(String id) {
        return Database.longArray.get(id) == null ? false : true;
    }

    /**
     * Get the Float array that corresponds with the ID given
     * 
     * @param id    The ID of the array to obtain
     * @return
     * The Float array that matches the ID
     */
    public static Float[] getFloatArray(String id) {
        return Database.floatArray.get(id);
    }

    /**
     * Store a new Float array
     * 
     * Can only accept an array of type Float instead of float because
     * ArrayList will only accept an array of Objects
     * 
     * @param id    The ID of the new array
     * @param value The array to be stored
     * @return
     * The ID of the new array
     */
    public static String setFloatArray(String id, Float[] value) {
        Database.floatArray.put(id, value);
        return id;
    }

    /**
     * Change a Float array
     * 
     * @param id        The ID of the array to change
     * @param newValue  The new array to replace with
     * @return
     * {@code true} if an array with the ID given exists, or {@code false} if
     * it doesn't
     */
    public static boolean changeFloatArray(String id, Float[] newValue) {
        if (Database.floatArray.get(id) == null) {
            return false;
        } else {
            Database.floatArray.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a Float array
     * 
     * @param id    The current ID of the array
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeFloatArrayID(String id, String newID) {
        if (Database.floatArray.get(id) == null
                || Database.floatArray.get(newID) != null) {
            return false;
        } else {
            Database.floatArray.put(newID, Database.floatArray.get(id));
            Database.floatArray.remove(id);
            return true;
        }
    }

    /**
     * Remove the array corresponding to the ID given
     * 
     * @param id    The ID of the array to remove
     * @return
     * {@code true} if the array corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeFloatArray(String id) {
        if (Database.floatArray.get(id) == null) {
            return false;
        } else {
            Database.floatArray.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean floatArrayExists(String id) {
        return Database.floatArray.get(id) == null ? false : true;
    }

    /**
     * Get the Double array that corresponds with the ID given
     * 
     * @param id    The ID of the array to obtain
     * @return
     * The Double array that matches the ID
     */
    public static Double[] getDoubleArray(String id) {
        return Database.doubleArray.get(id);
    }

    /**
     * Store a new Double array
     * 
     * Can only accept an array of type Double instead of double because
     * ArrayList will only accept an array of Objects
     * 
     * @param id    The ID of the new array
     * @param value The array to be stored
     * @return
     * The ID of the new array
     */
    public static String setDoubleArray(String id, Double[] value) {
        Database.doubleArray.put(id, value);
        return id;
    }

    /**
     * Change a Double array
     * 
     * @param id        The ID of the array to change
     * @param newValue  The new array to replace with
     * @return
     * {@code true} if an array with the ID given exists, or {@code false} if
     * it doesn't
     */
    public static boolean changeDoubleArray(String id, Double[] newValue) {
        if (Database.doubleArray.get(id) == null) {
            return false;
        } else {
            Database.doubleArray.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a Double array
     * 
     * @param id    The current ID of the array
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeDoubleArrayID(String id, String newID) {
        if (Database.doubleArray.get(id) == null
                || Database.doubleArray.get(newID) != null) {
            return false;
        } else {
            Database.doubleArray.put(newID, Database.doubleArray.get(id));
            Database.doubleArray.remove(id);
            return true;
        }
    }

    /**
     * Remove the array corresponding to the ID given
     * 
     * @param id    The ID of the array to remove
     * @return
     * {@code true} if the array corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeDoubleArray(String id) {
        if (Database.doubleArray.get(id) == null) {
            return false;
        } else {
            Database.doubleArray.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean doubleArrayExists(String id) {
        return Database.doubleArray.get(id) == null ? false : true;
    }

    /**
     * Get the Boolean array that corresponds with the ID given
     * 
     * @param id    The ID of the array to obtain
     * @return
     * The Boolean array that matches the ID
     */
    public static Boolean[] getBooleanArray(String id) {
        return Database.booleanArray.get(id);
    }

    /**
     * Store a new Boolean array
     * 
     * Can only accept an array of type Boolean instead of boolean because
     * ArrayList will only accept an array of Objects
     * 
     * @param id    The ID of the new array
     * @param value The array to be stored
     * @return
     * The ID of the new array
     */
    public static String setBooleanArray(String id, Boolean[] value) {
        Database.booleanArray.put(id, value);
        return id;
    }

    /**
     * Change a Boolean array
     * 
     * @param id        The ID of the array to change
     * @param newValue  The new array to replace with
     * @return
     * {@code true} if an array with the ID given exists, or {@code false} if
     * it doesn't
     */
    public static boolean changeBooleanArray(String id, Boolean[] newValue) {
        if (Database.booleanArray.get(id) == null) {
            return false;
        } else {
            Database.booleanArray.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a Boolean array
     * 
     * @param id    The current ID of the array
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeBooleanArrayID(String id, String newID) {
        if (Database.booleanArray.get(id) == null
                || Database.booleanArray.get(newID) != null) {
            return false;
        } else {
            Database.booleanArray.put(newID, Database.booleanArray.get(id));
            Database.booleanArray.remove(id);
            return true;
        }
    }

    /**
     * Remove the array corresponding to the ID given
     * 
     * @param id    The ID of the array to remove
     * @return
     * {@code true} if the array corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeBooleanArray(String id) {
        if (Database.booleanArray.get(id) == null) {
            return false;
        } else {
            Database.booleanArray.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean booleanArrayExists(String id) {
        return Database.booleanArray.get(id) == null ? false : true;
    }

    /**
     * Get the Character array that corresponds with the ID given
     * 
     * @param id    The ID of the array to obtain
     * @return
     * The Character array that matches the ID
     */
    public static Character[] getCharacterArray(String id) {
        return Database.charArray.get(id);
    }

    /**
     * Store a new Character array
     * 
     * Can only accept an array of type Character instead of char because
     * ArrayList will only accept an array of Objects
     * 
     * @param id    The ID of the new array
     * @param value The array to be stored
     * @return
     * The ID of the new array
     */
    public static String setCharacterArray(String id, Character[] value) {
        Database.charArray.put(id, value);
        return id;
    }

    /**
     * Change a Character array
     * 
     * @param id        The ID of the array to change
     * @param newValue  The new array to replace with
     * @return
     * {@code true} if an array with the ID given exists, or {@code false} if
     * it doesn't
     */
    public static boolean changeCharacterArray(String id, Character[] newValue) {
        if (Database.charArray.get(id) == null) {
            return false;
        } else {
            Database.charArray.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a Character array
     * 
     * @param id    The current ID of the array
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeCharacterArrayID(String id, String newID) {
        if (Database.charArray.get(id) == null
                || Database.charArray.get(newID) != null) {
            return false;
        } else {
            Database.charArray.put(newID, Database.charArray.get(id));
            Database.charArray.remove(id);
            return true;
        }
    }

    /**
     * Remove the array corresponding to the ID given
     * 
     * @param id    The ID of the array to remove
     * @return
     * {@code true} if the array corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeCharacterArray(String id) {
        if (Database.charArray.get(id) == null) {
            return false;
        } else {
            Database.charArray.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean charArrayExists(String id) {
        return Database.charArray.get(id) == null ? false : true;
    }

    /**
     * Get the String array that corresponds with the ID given
     * 
     * @param id    The ID of the array to obtain
     * @return
     * The String array that matches the ID
     */
    public static String[] getStringArray(String id) {
        return Database.stringArray.get(id);
    }

    /**
     * Store a new String array
     * 
     * Can only accept an array of type String instead of String because
     * ArrayList will only accept an array of Objects
     * 
     * @param id    The ID of the new array
     * @param value The array to be stored
     * @return
     * The ID of the new array
     */
    public static String setStringArray(String id, String[] value) {
        Database.stringArray.put(id, value);
        return id;
    }

    /**
     * Change a String array
     * 
     * @param id        The ID of the array to change
     * @param newValue  The new array to replace with
     * @return
     * {@code true} if an array with the ID given exists, or {@code false} if
     * it doesn't
     */
    public static boolean changeStringArray(String id, String[] newValue) {
        if (Database.stringArray.get(id) == null) {
            return false;
        } else {
            Database.stringArray.put(id, newValue);
            return true;
        }
    }

    /**
     * Change the ID of a String array
     * 
     * @param id    The current ID of the array
     * @param newID The new ID to change to
     * @return
     * {@code false} if the ID given doesn't exist or the new ID already
     * exists, otherwise returns {@code true}
     */
    public static boolean changeStringArrayID(String id, String newID) {
        if (Database.stringArray.get(id) == null
                || Database.stringArray.get(newID) != null) {
            return false;
        } else {
            Database.stringArray.put(newID, Database.stringArray.get(id));
            Database.stringArray.remove(id);
            return true;
        }
    }

    /**
     * Remove the array corresponding to the ID given
     * 
     * @param id    The ID of the array to remove
     * @return
     * {@code true} if the array corresponding to the ID exists, or
     * {@code false} if it doesn't
     */
    public static boolean removeStringArray(String id) {
        if (Database.stringArray.get(id) == null) {
            return false;
        } else {
            Database.stringArray.remove(id);
            return true;
        }
    }

    /**
     * Check if the key-value pair exists
     * 
     * @param id    The ID of the value to check
     * @return
     * {@code true} if the exists, or {@code false} if it doesn't
     */
    public static boolean StringArrayExists(String id) {
        return Database.stringArray.get(id) == null ? false : true;
    }
}
