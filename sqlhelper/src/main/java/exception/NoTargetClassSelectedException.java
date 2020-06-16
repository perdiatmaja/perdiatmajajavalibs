package exception;

import constants.SQLError.*;

public class NoTargetClassSelectedException extends SQLHelperException {

    public NoTargetClassSelectedException() {
        super(Code.NO_TARGETED_CLASS_SELECTED, Message.NO_TARGETED_CLASS_SELECTED);
    }
}
