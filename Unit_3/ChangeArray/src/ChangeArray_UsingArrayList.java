import java.util.List;
import java.util.ArrayList;

public class ChangeArray_UsingArrayList {
    public static void main(String[] args) {
        int input[] = {1, 2, 3, 6, 4};
        Integer output[] = pop(input);
        for (Integer item : output) {
            System.out.print(item + " ");
        }
    }
    // Appending characters
    public static Integer[] append(int inArray[], int item) {
        List<Integer> outArrayList = new ArrayList<Integer>();
        for (int items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Integer outArray[] = outArrayList.toArray(new Integer[0]);
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
    public static Character[] append(char inArray[], char item) {
        List<Character> outArrayList = new ArrayList<Character>();
        for (char items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.add(item);
        Character outArray[] = outArrayList.toArray(new Character[0]);
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
    public static Integer[] pop(int inArray[]) {
        List<Integer> outArrayList = new ArrayList<Integer>();
        for (int items : inArray) {
            outArrayList.add(items);
        }
        outArrayList.remove(outArrayList.size() - 1);
        Integer outArray[] = outArrayList.toArray(new Integer[0]);
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


    /*
    // Remove last item from array
    public static ArrayList<Integer> pop(ArrayList<Integer> inArrayList) {
        ArrayList<Integer> outArrayList = new ArrayList<Integer>(inArrayList);
        outArrayList.remove(outArrayList.size() - 1);
        return outArrayList;
    }
    public static ArrayList<Float> pop(ArrayList<Float> inArrayList) {
        ArrayList<Float> outArrayList = new ArrayList<Float>(inArrayList);
        outArrayList.remove(outArrayList.size() - 1);
        return outArrayList;
    }
    public static ArrayList<Character> pop(ArrayList<Character> inArrayList) {
        ArrayList<Character> outArrayList = new ArrayList<Character>(inArrayList);
        outArrayList.remove(outArrayList.size() - 1);
        return outArrayList;
    }
    public static ArrayList<Boolean> pop(ArrayList<Boolean> inArrayList) {
        ArrayList<Boolean> outArrayList = new ArrayList<Boolean>(inArrayList);
        outArrayList.remove(outArrayList.size() - 1);
        return outArrayList;
    }
    public static ArrayList<String> pop(ArrayList<String> inArrayList) {
        ArrayList<String> outArrayList = new ArrayList<String>(inArrayList);
        outArrayList.remove(outArrayList.size() - 1);
        return outArrayList;
    }
    // Insert item into array
    public static ArrayList<Integer> insert(ArrayList<Integer> inArrayList, int item, int index) {
        ArrayList<Integer> outArrayList = new ArrayList<Integer>(inArrayList);
        outArrayList.add(index, item);
        return outArrayList;
    }
    public static ArrayList<Float> insert(ArrayList<Float> inArrayList, float item, int index) {
        ArrayList<Float> outArrayList = new ArrayList<Float>(inArrayList);
        outArrayList.add(index, item);
        return outArrayList;
    }
    public static ArrayList<Character> insert(ArrayList<Character> inArrayList, char item, int index) {
        ArrayList<Character> outArrayList = new ArrayList<Character>(inArrayList);
        outArrayList.add(index, item);
        return outArrayList;
    }
    public static ArrayList<Boolean> insert(ArrayList<Boolean> inArrayList, boolean item, int index) {
        ArrayList<Boolean> outArrayList = new ArrayList<Boolean>(inArrayList);
        outArrayList.add(index, item);
        return outArrayList;
    }
    public static ArrayList<String> insert(ArrayList<String> inArrayList, String item, int index) {
        ArrayList<String> outArrayList = new ArrayList<String>(inArrayList);
        outArrayList.add(index, item);
        return outArrayList;
    }
    */
}
