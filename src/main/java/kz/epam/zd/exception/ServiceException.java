package kz.epam.zd.exception;


/**
 * Exception wrapper for Service layer.
 */
public class ServiceException extends Exception {
    private String message;

    public ServiceException(String message, Exception e) {
        super(e);
        this.message = message;

    }

    public ServiceException(Exception e) {
        this.message = e.getMessage();
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
