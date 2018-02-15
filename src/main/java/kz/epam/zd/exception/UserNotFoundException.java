package kz.epam.zd.exception;

/**
 * Specific ServiceException is thrown when there is no user with given username.
 */
public class UserNotFoundException extends ServiceException {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "user.not.found.error.message";

    public UserNotFoundException() {
        super(USER_NOT_FOUND_ERROR_MESSAGE);
    }
}
