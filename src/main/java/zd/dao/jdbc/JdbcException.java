package zd.dao.jdbc;

/**
 * Zhannur Diyas
 * 11/27/2016 | 1:17 PM
 */
public class JdbcException extends Exception {

    public JdbcException() {
    }

    public JdbcException(String message) {
        super(message);
    }

    public JdbcException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcException(Throwable cause) {
        super(cause);
    }
}
