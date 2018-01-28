package kz.epam.zd.exception;

public class NonUniqueFieldException extends JdbcDaoException {
    public NonUniqueFieldException(Throwable cause) {
        super(cause);
    }
}
