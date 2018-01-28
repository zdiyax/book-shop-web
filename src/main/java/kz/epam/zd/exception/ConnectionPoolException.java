package kz.epam.zd.exception;

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
