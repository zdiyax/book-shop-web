package kz.epam.zd.exception;

public class UserExistsException extends ServiceException {

    private static final String USER_EXIST_ERROR_MSG = "register.userexists.error";

    public UserExistsException(NonUniqueFieldException e) {
        super(USER_EXIST_ERROR_MSG, e);
    }
}
