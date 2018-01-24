package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Zhannur Diyas
 * 2/1/2017 | 2:57 AM
 */
public class ShowLoginSuccessPage implements Action {

    private static final String LOGIN_SUCCESS_PAGE = "login-success";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return LOGIN_SUCCESS_PAGE;
    }
}
