package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.LOGIN_PAGE;

public class ShowLoginPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        return LOGIN_PAGE;
    }
}
