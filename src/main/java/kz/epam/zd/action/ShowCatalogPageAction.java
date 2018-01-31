package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.CATALOG_PAGE;

public class ShowCatalogPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            Integer pageNumber = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        req.getSession().setAttribute("pageCount", 5);
        req.getSession().setAttribute("bookCount", 20);
        req.getSession().setAttribute("currentPage", 1);
        return CATALOG_PAGE;
    }
}
