package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.user.Locale;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.UserService;
import kz.epam.zd.util.CookieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Global action to update user locale.
 */
public class ChangeLocaleAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ChangeLocaleAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String locale = request.getParameter(LOCALE);

        if (request.getSession().getAttribute(USER) != null) {
            final User user = (User) request.getSession().getAttribute(USER);
            user.setLocale(new Locale(locale));
            UserService service = new UserService();
            try {
                service.updateUserLocale(user);
            } catch (ServiceException e) {
                log.debug("Locale was not changed: {}", e);
                throw new ActionException(e);
            }
        }
        request.getSession().setAttribute(LOCALE, locale);
        log.debug("Locale changed successfully to {}", locale);

        CookieHelper.setCookie(response, LOCALE, locale);
        String referrer = request.getHeader(REFERER);
        return REDIRECT_PREFIX + referrer;
    }
}

