package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.LOGIN_PAGE;

/**
 * Anonymous action to display Login page.
 */
public class ShowLoginPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return LOGIN_PAGE;
    }
}
