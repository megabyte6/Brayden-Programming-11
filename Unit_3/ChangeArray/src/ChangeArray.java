public class ChangeArray {
    // Append item to array
    public static byte[] append(byte inArray[], byte item) {
        byte outArray[] = new byte[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    public static short[] append(short inArray[], short item) {
        short outArray[] = new short[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    public static int[] append(int inArray[], int item) {
        int outArray[] = new int[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    public static long[] append(long inArray[], long item) {
        long outArray[] = new long[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    public static float[] append(float inArray[], float item) {
        float outArray[] = new float[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    public static double[] append(double inArray[], double item) {
        double outArray[] = new double[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    public static boolean[] append(boolean inArray[], boolean item) {
        boolean outArray[] = new boolean[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    public static char[] append(char inArray[], char item) {
        char outArray[] = new char[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    public static String[] append(String inArray[], String item) {
        String outArray[] = new String[inArray.length + 1];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[i];
        }
        outArray[outArray.length - 1] = item;
        return outArray;
    }
    // Remove last item from array
    public static byte[] pop(byte inArray[]) {
        byte outArray[] = new byte[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    public static short[] pop(short inArray[]) {
        short outArray[] = new short[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    public static int[] pop(int inArray[]) {
        int outArray[] = new int[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    public static long[] pop(long inArray[]) {
        long outArray[] = new long[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    public static float[] pop(float inArray[]) {
        float outArray[] = new float[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    public static double[] pop(double inArray[]) {
        double outArray[] = new double[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    public static boolean[] pop(boolean inArray[]) {
        boolean outArray[] = new boolean[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    public static char[] pop(char inArray[]) {
        char outArray[] = new char[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    public static String[] pop(String inArray[]) {
        String outArray[] = new String[inArray.length - 1];
        for (int i = 0; i < inArray.length - 1; i++) {
            outArray[i] = inArray[i];
        }
        return outArray;
    }
    // Insert item into array
    public static byte[] insert(byte inArray[], byte item, int index) {
        byte outArray[] = new byte[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
    public static short[] insert(short inArray[], short item, int index) {
        short outArray[] = new short[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
    public static int[] insert(int inArray[], int item, int index) {
        int outArray[] = new int[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
    public static long[] insert(long inArray[], long item, int index) {
        long outArray[] = new long[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
    public static float[] insert(float inArray[], float item, int index) {
        float outArray[] = new float[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
    public static double[] insert(double inArray[], double item, int index) {
        double outArray[] = new double[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
    public static boolean[] insert(boolean inArray[], boolean item, int index) {
        boolean outArray[] = new boolean[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
    public static char[] insert(char inArray[], char item, int index) {
        char outArray[] = new char[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
    public static String[] insert(String inArray[], String item, int index) {
        String outArray[] = new String[inArray.length + 1];
        boolean insertComplete = false;
        for (int i = 0; i < outArray.length; i++) {
            if (i == index) {
                outArray[i] = item;
                insertComplete = true;
            } else {
                if (insertComplete) {
                    outArray[i] = inArray[i - 1];
                } else {
                    outArray[i] = inArray[i];
                }
            }
        }
        return outArray;
    }
}