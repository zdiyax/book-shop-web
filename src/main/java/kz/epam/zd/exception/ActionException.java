package kz.epam.zd.exception;

/**
 * Exception wrapper for Action layer.
 */
public class ActionException extends Exception {
    public ActionException(Exception e) {
        super(e);
    }
}
