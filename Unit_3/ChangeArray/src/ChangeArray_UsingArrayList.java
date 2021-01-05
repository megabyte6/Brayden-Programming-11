import java.util.List;
import java.util.ArrayList;

public class ChangeArray_UsingArrayList {
    // Append item to array
    public static Byte[] append(byte inArray[], byte item) {
        List<Byte> outArrayList = new ArrayList<Byte>();
        for (byte items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Byte outArray[] = outArrayList.toArray(new Byte[0]);
        return outArray;
    }
    public static Short[] append(short inArray[], short item) {
        List<Short> outArrayList = new ArrayList<Short>();
        for (short items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Short outArray[] = outArrayList.toArray(new Short[0]);
        return outArray;
    }
    public static Integer[] append(int inArray[], int item) {
        List<Integer> outArrayList = new ArrayList<Integer>();
        for (int items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Integer outArray[] = outArrayList.toArray(new Integer[0]);
        return outArray;
    }
    public static Long[] append(long inArray[], long item) {
        List<Long> outArrayList = new ArrayList<Long>();
        for (long items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Long outArray[] = outArrayList.toArray(new Long[0]);
        return outArray;
    }
    public static Float[] append(float inArray[], float item) {
        List<Float> outArrayList = new ArrayList<Float>();
        for (float items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Float outArray[] = outArrayList.toArray(new Float[0]);
        return outArray;
    }
    public static Double[] append(double inArray[], double item) {
        List<Double> outArrayList = new ArrayList<Double>();
        for (double items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Double outArray[] = outArrayList.toArray(new Double[0]);
        return outArray;
    }
    public static Boolean[] append(boolean inArray[], boolean item) {
        List<Boolean> outArrayList = new ArrayList<Boolean>();
        for (boolean items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Boolean outArray[] = outArrayList.toArray(new Boolean[0]);
        return outArray;
    }
    public static Character[] append(char inArray[], char item) {
        List<Character> outArrayList = new ArrayList<Character>();
        for (char items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Character outArray[] = outArrayList.toArray(new Character[0]);
        return outArray;
    }
    public static String[] append(String inArray[], String item) {
        List<String> outArrayList = new ArrayList<String>();
        for (String items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        String outArray[] = outArrayList.toArray(new String[0]);
        return outArray;
    }
    // Remove last item from array
    public static Byte[] pop(byte inArray[]) {
        List<Byte> outArrayList = new ArrayList<Byte>();
        for (byte items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Byte outArray[] = outArrayList.toArray(new Byte[0]);
        return outArray;
    }
    public static Short[] pop(short inArray[]) {
        List<Short> outArrayList = new ArrayList<Short>();
        for (short items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Short outArray[] = outArrayList.toArray(new Short[0]);
        return outArray;
    }
    public static Integer[] pop(int inArray[]) {
        List<Integer> outArrayList = new ArrayList<Integer>();
        for (int items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Integer outArray[] = outArrayList.toArray(new Integer[0]);
        return outArray;
    }
    public static Long[] pop(long inArray[]) {
        List<Long> outArrayList = new ArrayList<Long>();
        for (long items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Long outArray[] = outArrayList.toArray(new Long[0]);
        return outArray;
    }
    public static Float[] pop(float inArray[]) {
        List<Float> outArrayList = new ArrayList<Float>();
        for (float items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Float outArray[] = outArrayList.toArray(new Float[0]);
        return outArray;
    }
    public static Double[] pop(double inArray[]) {
        List<Double> outArrayList = new ArrayList<Double>();
        for (double items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Double outArray[] = outArrayList.toArray(new Double[0]);
        return outArray;
    }
    public static Boolean[] pop(boolean inArray[]) {
        List<Boolean> outArrayList = new ArrayList<Boolean>();
        for (boolean items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Boolean outArray[] = outArrayList.toArray(new Boolean[0]);
        return outArray;
    }
    public static Character[] pop(char inArray[]) {
        List<Character> outArrayList = new ArrayList<Character>();
        for (char items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Character outArray[] = outArrayList.toArray(new Character[0]);
        return outArray;
    }
    public static String[] pop(String inArray[]) {
        List<String> outArrayList = new ArrayList<String>();
        for (String items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        String outArray[] = outArrayList.toArray(new String[0]);
        return outArray;
    }
    // Insert item into array
    public static Byte[] insert(byte inArray[], byte item, int index) {
        List<Byte> outArrayList = new ArrayList<Byte>();
        for (byte items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        Byte outArray[] = outArrayList.toArray(new Byte[0]);
        return outArray;
    }
    public static Short[] insert(short inArray[], short item, int index) {
        List<Short> outArrayList = new ArrayList<Short>();
        for (short items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        Short outArray[] = outArrayList.toArray(new Short[0]);
        return outArray;
    }
    public static Integer[] insert(int inArray[], int item, int index) {
        List<Integer> outArrayList = new ArrayList<Integer>();
        for (int items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        Integer outArray[] = outArrayList.toArray(new Integer[0]);
        return outArray;
    }
    public static Long[] insert(long inArray[], long item, int index) {
        List<Long> outArrayList = new ArrayList<Long>();
        for (long items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        Long outArray[] = outArrayList.toArray(new Long[0]);
        return outArray;
    }
    public static Float[] insert(float inArray[], float item, int index) {
        List<Float> outArrayList = new ArrayList<Float>();
        for (float items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        Float outArray[] = outArrayList.toArray(new Float[0]);
        return outArray;
    }
    public static Double[] insert(double inArray[], double item, int index) {
        List<Double> outArrayList = new ArrayList<Double>();
        for (double items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        Double outArray[] = outArrayList.toArray(new Double[0]);
        return outArray;
    }
    public static Boolean[] insert(boolean inArray[], boolean item, int index) {
        List<Boolean> outArrayList = new ArrayList<Boolean>();
        for (boolean items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        Boolean outArray[] = outArrayList.toArray(new Boolean[0]);
        return outArray;
    }
    public static Character[] insert(char inArray[], char item, int index) {
        List<Character> outArrayList = new ArrayList<Character>();
        for (char items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        Character outArray[] = outArrayList.toArray(new Character[0]);
        return outArray;
    }
    public static String[] insert(String inArray[], String item, int index) {
        List<String> outArrayList = new ArrayList<String>();
        for (String items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(index, item);
        String outArray[] = outArrayList.toArray(new String[0]);
        return outArray;
    }
}
