package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Anonymous action to display Login page.
 */
public class ShowLoginPageAction implements Action {

    private static final String LOGIN_PAGE = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return LOGIN_PAGE;
    }
}
