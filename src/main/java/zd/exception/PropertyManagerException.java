package zd.exception;

/**
 * Zhannur Diyas
 * 12/15/2016 | 10:59 AM
 */
public class PropertyManagerException extends Exception {
    public PropertyManagerException(String message) {
        super(message);
    }

    public PropertyManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyManagerException(Throwable cause) {
        super(cause);
    }

    public PropertyManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
