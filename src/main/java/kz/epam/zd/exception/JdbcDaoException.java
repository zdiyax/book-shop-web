package kz.epam.zd.exception;

/**
 * Exception wrapper for JDBC DAO layer.
 */
public class JdbcDaoException extends DaoException {

    public JdbcDaoException() {
    }


    public JdbcDaoException(Throwable cause) {
        super(cause);
    }
}
