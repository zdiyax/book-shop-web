package kz.epam.zd.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LocaleChangerException extends Exception {

    public LocaleChangerException(ServiceException e) {

        super(e);

    }
}
