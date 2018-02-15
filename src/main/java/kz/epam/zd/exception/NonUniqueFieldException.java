package kz.epam.zd.exception;

/**
 * Specific JdbcDaoException is thrown when database unique fields
 * rule is violated.
 */
public class NonUniqueFieldException extends JdbcDaoException {
    public NonUniqueFieldException(Throwable cause) {
        super(cause);
    }
}
