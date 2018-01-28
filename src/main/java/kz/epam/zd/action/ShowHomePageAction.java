package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.HOME_PAGE;

public class ShowHomePageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        return HOME_PAGE;
    }
}
