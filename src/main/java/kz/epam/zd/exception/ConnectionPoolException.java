package kz.epam.zd.exception;

/**
 * Exception wrapper for ConnectionPool exceptions.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException() {
    }
}
