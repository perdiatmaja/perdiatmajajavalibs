package id.perdiatmaja.sqlhelper.utils;

public class SQLHelperTextUtil {

    private static final String EMPTY_STRING = "";

    public static boolean isEmpty(String text) {
        return text == null || EMPTY_STRING.equals(text);
    }
}
