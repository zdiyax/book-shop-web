package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.REGISTER_PAGE;

/**
 * Anonymous action to display Register page
 */
public class ShowRegisterPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return REGISTER_PAGE;
    }
}
