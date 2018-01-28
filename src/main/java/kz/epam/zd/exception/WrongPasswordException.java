package kz.epam.zd.exception;

public class WrongPasswordException extends ServiceException {
    private static final String WRONG_PASSWORD = "user.wrongpassword";

    public WrongPasswordException() {
        super(WRONG_PASSWORD);
    }
}
