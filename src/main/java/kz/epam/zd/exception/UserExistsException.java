package kz.epam.zd.exception;

public class UserExistsException extends ServiceException {

    private static final String USER_EXIST_ERROR_MSG = "register.error.message.exist";

    public UserExistsException(NonUniqueFieldException e) {
        super(USER_EXIST_ERROR_MSG, e);
    }
}
