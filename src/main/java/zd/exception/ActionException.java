package zd.exception;

/**
 * Zhannur Diyas
 * 12/10/2016 | 12:33 AM
 */
public class ActionException extends Exception {
    public ActionException() {
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionException(Throwable cause) {
        super(cause);
    }
}
