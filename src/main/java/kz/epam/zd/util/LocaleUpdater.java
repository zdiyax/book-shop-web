package kz.epam.zd.util;

import kz.epam.zd.exception.LocaleChangerException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.user.Locale;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static kz.epam.zd.util.ConstantHolder.LOCALE;
import static kz.epam.zd.util.ConstantHolder.USER;

public class LocaleUpdater {

    private LocaleUpdater() {
    }

    public static void changeUserLocale(HttpServletRequest req, String locale) throws LocaleChangerException {

        if (req.getSession().getAttribute(USER) != null) {
            final User user = (User) req.getSession().getAttribute(USER);
            user.setLocale(new Locale(locale));
            UserService service = new UserService();
            try {
                service.updateUserLocale(user);
            } catch (ServiceException e) {
                throw new LocaleChangerException(e);
            }
        }

        req.getSession().setAttribute(LOCALE, locale);

    }
}