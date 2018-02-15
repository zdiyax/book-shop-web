package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.REGISTER_SUCCESS_PAGE;

/**
 * Global action to display Register success page.
 */
public class ShowRegisterSuccessPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return REGISTER_SUCCESS_PAGE;
    }
}
