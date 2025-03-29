public class Converter {
    // Float is Celsius, int is kelvin
    private static float C_TO_F_RATIO = 9.0f/5.0f;
    private static float F_TO_C_RATIO = 5.0f/9.0f;
    private static int offset = 0;

    // Getter and setter for offset
    static public void setOffset(int new_offset) {
        offset = new_offset;
    }
    static public int getOffset() {
        return offset;
    }

    // Converting single inputs
    public static int Temperature(float celsius) {
        return ((int) (celsius*C_TO_F_RATIO)+32)+offset;
    }

    public static float Temperature(int fahrenheit) {
        return ((float) (fahrenheit-32)*F_TO_C_RATIO)+offset;
    }

    // Converting multiple inputs
    public static int[] Temperature(float[] celsius) {
        int[] res = new int[celsius.length];

        // Iterate over each element, convert to fahrenheit and place in res array
        for (int i = 0; i<celsius.length; i++) res[i] = ((int) (celsius[i]*C_TO_F_RATIO)+32)+offset;

        return res;
    }

    public static float[] Temperature(int[] fahrenheit) {
        float[] res = new float[fahrenheit.length];

        for (int i = 0; i<fahrenheit.length; i++) res[i] = ((int) (fahrenheit[i]-32)*F_TO_C_RATIO)+offset;

        return res;
    }

}
