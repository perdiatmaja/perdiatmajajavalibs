package exception;

import constants.SQLError.*;

public class NoColumnSelectedException extends Exception {

    private final String errorCode;

    public NoColumnSelectedException() {
        super(Message.NO_COLUMN_SELECTED);
        errorCode = Code.NO_COLUMN_SELECTED;
    }
}
