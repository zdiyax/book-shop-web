package zd.exception;

/**
 * Zhannur Diyas
 * 11/27/2016 | 1:17 PM
 */
public class JdbcDaoException extends Exception {

    public JdbcDaoException() {
    }

    public JdbcDaoException(String message) {
        super(message);
    }

    public JdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcDaoException(Throwable cause) {
        super(cause);
    }
}
