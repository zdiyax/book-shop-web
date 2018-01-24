package kz.epam.zd.exception;

public class ServiceException extends Exception {
    public ServiceException(JdbcDaoException e) {
    }

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Exception e) {
    }
}
