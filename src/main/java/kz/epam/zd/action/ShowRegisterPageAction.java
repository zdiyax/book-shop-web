package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Anonymous action to display Register page.
 */
public class ShowRegisterPageAction implements Action {

    private static final String REGISTER_PAGE = "register";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return REGISTER_PAGE;
    }
}
