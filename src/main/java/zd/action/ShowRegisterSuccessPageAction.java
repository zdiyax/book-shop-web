package zd.action;

import zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Zhannur Diyas
 * 2/1/2017 | 1:50 AM
 */
public class ShowRegisterSuccessPageAction implements Action {

    private static final String REGISTER_SUCCESS_PAGE = "register-success";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return REGISTER_SUCCESS_PAGE;
    }
}
