package kz.epam.zd.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility class to manage site cookies.
 */
public class CookieHelper {

    private CookieHelper() {
    }

    public static Cookie findParameter(HttpServletRequest req, String parameter) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (parameter.equals(cookie.getName())) return cookie;
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse resp, String name, String value) {
        resp.addCookie(new Cookie(name, value));
    }
}
