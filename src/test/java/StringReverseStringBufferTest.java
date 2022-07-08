import org.junit.jupiter.api.Assertions;

public class StringReverseStringBufferTest {

    StringReverse stringReverse = new StringReverseStringBuffer();

    // корректная работа на пустой строке
    @org.junit.jupiter.api.Test
    void reverseEmptyString() {
        String expected = "";
        String actual = stringReverse.reverse("");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на строке из одного символа
    @org.junit.jupiter.api.Test
    void reverseOneSymbol() {
        String expected = "*";
        String actual = stringReverse.reverse("*");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на строке из двух символов
    @org.junit.jupiter.api.Test
    void reverseTwoSymbol() {
        String expected = "12";
        String actual = stringReverse.reverse("21");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на произвольной строке
    @org.junit.jupiter.api.Test
    void reverseSuccess() {
        String expected = "0123456789";
        String actual = stringReverse.reverse("9876543210");
        Assertions.assertEquals(expected, actual);
    }

}
