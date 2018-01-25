package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static kz.epam.zd.util.ConstantHolder.CATALOG_PAGE;

public class ShowCatalogPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return CATALOG_PAGE;
    }
}
