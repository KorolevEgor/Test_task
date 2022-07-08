public class StringReverse {
    public static String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    public static String reverse2(String s) {
        byte[] bytes = s.getBytes();
        for (int i = 0; i < bytes.length / 2; i++) {
            swapBytes(bytes, i, bytes.length - i - 1);
        }
        return new String(bytes);
    }

    private static void swapBytes(byte[] bytes, int index1, int index2) {
        byte tmp = bytes[index1];
        bytes[index1] = bytes[index2];
        bytes[index2] = tmp;
    }
    
}
