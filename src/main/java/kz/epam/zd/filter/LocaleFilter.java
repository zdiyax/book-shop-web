package kz.epam.zd.filter;

import kz.epam.zd.util.CookieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LocaleFilter.class);
    private static final String DEFAULT_LOCALE = "en";
    private static final String LOCALE = "locale";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String locale = null;
        Cookie localCookie = CookieHelper.findParameter(req, LOCALE);
        if (localCookie != null) locale = localCookie.getValue();

        if (locale == null) {
            log.debug("Locale not found in cookies, try to find in session.");
            locale = (String) session.getAttribute(LOCALE);
            CookieHelper.setCookie(resp, LOCALE, locale);
        }

        if (locale == null) {
            locale = DEFAULT_LOCALE;
            CookieHelper.setCookie(resp, LOCALE, locale);
            log.debug("Locale not found in session, set default locale to \"{}\"", locale);
        }

        Locale currentLocale = new Locale(locale);
        Config.set(session, Config.FMT_LOCALE, currentLocale);
        session.setAttribute(LOCALE, locale);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
