package zd.action;

import zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static zd.util.ConstantHolder.CATALOG_PAGE;

/**
 * Zhannur Diyas
 * 1/29/2017 | 3:13 PM
 */
public class ShowCatalogPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return CATALOG_PAGE;
    }
}
