package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.ABOUT_PAGE;

public class ShowAboutPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        return ABOUT_PAGE;
    }
}
