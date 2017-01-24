package zd.exception;

public class JdbcDaoException extends DaoException {

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
