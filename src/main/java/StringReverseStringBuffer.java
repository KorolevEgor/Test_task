public class StringReverseStringBuffer implements StringReverse {
    @Override
    public String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }
}
