package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.REGISTER_PAGE;

public class ShowRegisterPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        return REGISTER_PAGE;
    }
}
