package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.util.ValidatorHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.REDIRECT_PREFIX;

public class RefreshCartAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        HashMap books = (HashMap) req.getSession().getAttribute("cart");

        if (ValidatorHelper.checkCartForm(req, books))
            return REDIRECT_PREFIX + "/do/?action=show-cart-page";

        for (int i = 0; i < books.size(); i++) {
            for (Object o : books.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                books.replace(pair.getKey(), pair.getValue(), Integer.valueOf(req.getParameter("quantity" + i)));
            }
        }
        req.getSession().setAttribute("cart", books);
        return REDIRECT_PREFIX + "/do/?action=show-cart-page";
    }
}
