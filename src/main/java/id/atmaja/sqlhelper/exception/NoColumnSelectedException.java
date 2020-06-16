package id.atmaja.sqlhelper.exception;

import id.atmaja.sqlhelper.constants.SQLError.*;

public class NoColumnSelectedException extends Exception {

    private final String errorCode;

    public NoColumnSelectedException() {
        super(Message.NO_COLUMN_SELECTED);
        errorCode = Code.NO_COLUMN_SELECTED;
    }
}
