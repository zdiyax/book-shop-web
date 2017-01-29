package zd.action;

import zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static zd.util.ConstantHolder.ABOUT_PAGE;

/**
 * Zhannur Diyas
 * 1/29/2017 | 3:08 PM
 */
public class ShowAboutPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return ABOUT_PAGE;
    }
}
