package zd.exception;

/**
 * Zhannur Diyas
 * 12/1/2016 | 11:42 PM
 */
public class ValidatorException extends Exception {
    public ValidatorException() {
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
