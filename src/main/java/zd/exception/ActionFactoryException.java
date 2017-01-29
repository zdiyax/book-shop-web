package zd.exception;

/**
 * Zhannur Diyas
 * 1/29/2017 | 2:20 PM
 */
public class ActionFactoryException extends Exception {

    public ActionFactoryException() {
    }

    public ActionFactoryException(String message) {
        super(message);
    }

    public ActionFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionFactoryException(Throwable cause) {
        super(cause);
    }
}
