package zd.action;

import zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static zd.util.ConstantHolder.LOGIN_PAGE;

/**
 * Zhannur Diyas
 * 1/29/2017 | 2:13 PM
 */
public class ShowLoginPageAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return LOGIN_PAGE;
    }

}
