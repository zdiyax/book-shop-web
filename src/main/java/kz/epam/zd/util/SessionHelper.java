package kz.epam.zd.util;

import javax.servlet.http.HttpServletRequest;

public class SessionHelper {
    private SessionHelper() {
    }

    public static void saveParamToSession(HttpServletRequest request, String attrName, String value) {
        request.getSession().setAttribute(attrName, value);
    }
}
