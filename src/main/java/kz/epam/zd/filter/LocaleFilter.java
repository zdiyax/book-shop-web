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

/**
 * Manages the locale property on the pages.
 */
public class LocaleFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LocaleFilter.class);
    private static final String DEFAULT_LOCALE = "en";
    private static final String LOCALE = "locale";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        String locale = null;
        Cookie localCookie = CookieHelper.findParameter(request, LOCALE);
        if (localCookie != null) locale = localCookie.getValue();

        if (locale == null) {
            log.debug("Locale not found in cookies");
            locale = (String) session.getAttribute(LOCALE);
            CookieHelper.setCookie(response, LOCALE, locale);
        }
        if (locale == null) {
            locale = DEFAULT_LOCALE;
            CookieHelper.setCookie(response, LOCALE, locale);
            log.debug("Locale not found in session, setting locale to default =  \"{}\"", locale);
        }
        Locale currentLocale = new Locale(locale);
        Config.set(session, Config.FMT_LOCALE, currentLocale);
        session.setAttribute(LOCALE, locale);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
