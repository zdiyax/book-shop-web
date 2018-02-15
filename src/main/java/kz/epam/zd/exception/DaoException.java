package kz.epam.zd.exception;

/**
 * Exception wrapper for DAO layer.
 */
public class DaoException extends Exception {

    DaoException() {
    }

    DaoException(Throwable cause) {
        super(cause);
    }

}
