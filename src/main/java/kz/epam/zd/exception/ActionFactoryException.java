package kz.epam.zd.exception;

/**
 * Exception wrapper for ActionFactory exceptions.
 */
public class ActionFactoryException extends Exception {
    public ActionFactoryException(Exception e) {
        super(e);
    }
}
