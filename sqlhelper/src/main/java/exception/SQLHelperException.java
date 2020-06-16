package exception;

public class SQLHelperException extends Exception {

    private final String code;

    public SQLHelperException(String code, String message) {
        super(message);
        this.code = code;
    }
}
