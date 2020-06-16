package id.perdiatmaja.sqlhelper.exception;

import id.perdiatmaja.sqlhelper.constants.SQLError.*;

public class NoColumnSelectedException extends SQLHelperException {

    public NoColumnSelectedException() {
        super(Code.NO_COLUMN_SELECTED, Message.NO_COLUMN_SELECTED);
    }
}
