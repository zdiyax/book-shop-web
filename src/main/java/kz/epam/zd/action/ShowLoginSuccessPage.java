package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User action to display Login Success page.
 */
public class ShowLoginSuccessPage implements Action {

    private static final String LOGIN_SUCCESS_PAGE = "login-success";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return LOGIN_SUCCESS_PAGE;
    }
}
