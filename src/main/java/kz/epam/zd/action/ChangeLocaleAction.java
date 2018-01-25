package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.LocaleChangerException;
import kz.epam.zd.util.CookieHelper;
import kz.epam.zd.util.LocaleUpdater;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

public class ChangeLocaleAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {

        String userLocale = req.getParameter(LOCALE);
        try {
            LocaleUpdater.changeUserLocale(req, userLocale);
        } catch (LocaleChangerException e) {
            throw new ActionException(e);
        }
        CookieHelper.setCookie(res, LOCALE, userLocale);
        String referrer = req.getHeader(REFERRER);

        return REDIRECT_PREFIX + referrer;
    }
}

