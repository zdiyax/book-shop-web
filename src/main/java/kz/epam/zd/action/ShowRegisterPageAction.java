package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Zhannur Diyas
 * 1/29/2017 | 11:05 PM
 */
public class ShowRegisterPageAction implements Action {

    private static final String REGISTER_PAGE = "register";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return REGISTER_PAGE;
    }
}
