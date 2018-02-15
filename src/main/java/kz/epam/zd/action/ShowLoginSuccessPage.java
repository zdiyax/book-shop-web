package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.LOGIN_SUCCESS_PAGE;

/**
 * User action to display Login Success page.
 */
public class ShowLoginSuccessPage implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return LOGIN_SUCCESS_PAGE;
    }
}
