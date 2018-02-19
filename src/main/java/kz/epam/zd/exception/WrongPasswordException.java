package kz.epam.zd.exception;

/**
 * Specific ServiceException is thrown when user provides wrong password credentials.
 */
public class WrongPasswordException extends ServiceException {

    private static final String USER_WRONG_PASSWORD_ERROR_MESSAGE = "user.wrong.password.error.message";

    public WrongPasswordException() {
        super(USER_WRONG_PASSWORD_ERROR_MESSAGE);
    }
}
