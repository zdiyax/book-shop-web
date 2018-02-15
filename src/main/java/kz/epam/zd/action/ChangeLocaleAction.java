package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.LocaleChangerException;
import kz.epam.zd.util.CookieHelper;
import kz.epam.zd.util.LocaleUpdater;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Global action to update user locale.
 */
public class ChangeLocaleAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String userLocale = request.getParameter(LOCALE);
        try {
            LocaleUpdater.changeUserLocale(request, userLocale);
        } catch (LocaleChangerException e) {
            throw new ActionException(e);
        }
        CookieHelper.setCookie(response, LOCALE, userLocale);
        String referrer = request.getHeader(REFERER);
        return REDIRECT_PREFIX + referrer;
    }

}

