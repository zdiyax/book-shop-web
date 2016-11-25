package model;

/**
 * Zhannur Diyas
 * 11/25/2016 | 9:17 PM
 */

public class ModelException extends Exception {

    public ModelException(String message) {
        super(message);
    }

    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelException(Throwable cause) {
        super(cause);
    }
}
