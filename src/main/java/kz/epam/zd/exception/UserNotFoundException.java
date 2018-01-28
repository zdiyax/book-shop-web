package kz.epam.zd.exception;

public class UserNotFoundException extends ServiceException {
    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "user.notfound.error";

    public UserNotFoundException() {
     super(USER_NOT_FOUND_ERROR_MESSAGE);
    }
}
