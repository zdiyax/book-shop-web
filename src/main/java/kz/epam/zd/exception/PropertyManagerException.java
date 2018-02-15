package kz.epam.zd.exception;

/**
 * Exception wrapper for PropertyManager exceptions.
 */
public class PropertyManagerException extends Exception {
    public PropertyManagerException(String message) {
        super(message);
    }

    public PropertyManagerException(Throwable cause) {
        super(cause);
    }

}
