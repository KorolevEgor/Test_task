import org.junit.jupiter.api.Assertions;

public class StringReverseTest {

    // корректная работа на пустой строке
    @org.junit.jupiter.api.Test
    void reverseEmptyString() {
        String expected = "";
        String actual = StringReverse.reverse("");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на строке из одного символа
    @org.junit.jupiter.api.Test
    void reverseOneSymbol() {
        String expected = "*";
        String actual = StringReverse.reverse("*");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на строке из двух символов
    @org.junit.jupiter.api.Test
    void reverseTwoSymbol() {
        String expected = "12";
        String actual = StringReverse.reverse("21");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на произвольной строке
    @org.junit.jupiter.api.Test
    void reverseSuccess() {
        String expected = "0123456789";
        String actual = StringReverse.reverse("9876543210");
        Assertions.assertEquals(expected, actual);
    }


    /////////////////////////////
    // reverse 2 method
    /////////////////////////////

    // корректная работа на пустой строке
    @org.junit.jupiter.api.Test
    void reverse2EmptyString() {
        String expected = "";
        String actual = StringReverse.reverse2("");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на строке из одного символа
    @org.junit.jupiter.api.Test
    void reverse2OneSymbol() {
        String expected = "*";
        String actual = StringReverse.reverse2("*");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на строке из двух символов
    @org.junit.jupiter.api.Test
    void reverse2TwoSymbol() {
        String expected = "12";
        String actual = StringReverse.reverse2("21");
        Assertions.assertEquals(expected, actual);
    }

    // корректная работа на произвольной строке
    @org.junit.jupiter.api.Test
    void reverse2Success() {
        String expected = "0123456789";
        String actual = StringReverse.reverse2("9876543210");
        Assertions.assertEquals(expected, actual);
    }
}
