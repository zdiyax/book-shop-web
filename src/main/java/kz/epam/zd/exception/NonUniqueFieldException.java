package kz.epam.zd.exception;

/**
 * Zhannur Diyas
 * 2/1/2017 | 4:39 PM
 */
public class NonUniqueFieldException extends JdbcDaoException {
    public NonUniqueFieldException() {
    }

    public NonUniqueFieldException(String message) {
        super(message);
    }

    public NonUniqueFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonUniqueFieldException(Throwable cause) {
        super(cause);
    }
}
