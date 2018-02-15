package kz.epam.zd.exception;

/**
 * Specific ServiceException is thrown as a wrapper of NonUniqueFieldException
 */
public class UserExistsException extends ServiceException {

    private static final String USER_EXISTS_ERROR_MESSAGE = "user.exists.error.message";

    public UserExistsException(NonUniqueFieldException e) {
        super(USER_EXISTS_ERROR_MESSAGE, e);
    }
}
