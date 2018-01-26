package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static kz.epam.zd.util.ConstantHolder.REGISTER_SUCCESS_PAGE;

public class ShowRegisterSuccessPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return REGISTER_SUCCESS_PAGE;
    }
}
