package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Global action to display Register success page.
 */
public class ShowRegisterSuccessPageAction implements Action {

    private static final String REGISTER_SUCCESS_PAGE = "register-success";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return REGISTER_SUCCESS_PAGE;
    }
}
