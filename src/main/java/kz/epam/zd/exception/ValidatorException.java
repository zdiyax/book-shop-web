package kz.epam.zd.exception;

/**
 * Exception wrapper for Validator exceptions.
 */
public class ValidatorException extends Exception {

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
