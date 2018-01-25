package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static kz.epam.zd.util.ConstantHolder.PROFILE_PAGE;

public class ShowProfilePageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return PROFILE_PAGE;
    }
}
