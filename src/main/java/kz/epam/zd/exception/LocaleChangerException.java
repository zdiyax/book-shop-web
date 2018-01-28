package kz.epam.zd.exception;

public class LocaleChangerException extends Exception {
    public LocaleChangerException(ServiceException e) {
        super(e);
    }
}
