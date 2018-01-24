package kz.epam.zd.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
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

        String locale = DEFAULT_LOCALE;
        resp.addCookie(new Cookie(LOCALE, locale));
        Locale.setDefault(Locale.forLanguageTag(locale));

        Locale currentLocale = new Locale(locale);
        Config.set(session, Config.FMT_LOCALE, currentLocale);
        session.setAttribute(LOCALE, locale);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
