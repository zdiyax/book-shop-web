package zd.exception;

/**
 * Zhannur Diyas
 * 1/29/2017 | 2:57 PM
 */
public class UserNotFoundException extends ServiceException {

    public UserNotFoundException(JdbcDaoException e) {
        super(e);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Exception e) {
        super(e);
    }
}
