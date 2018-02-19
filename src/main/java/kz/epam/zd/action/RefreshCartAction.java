package kz.epam.zd.action;

import kz.epam.zd.util.ValidatorHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Customer action to update book quantities in the shopping cart.
 */
public class RefreshCartAction implements Action {

    private static final String CART_QUANTITY_ERROR_MESSAGE = "cart.quantity.error.message";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HashMap books = (HashMap) request.getSession().getAttribute(CART);

        if (ValidatorHelper.checkCartForm(request, books)) {
            request.setAttribute(CART + FORM_ERRORS, CART_QUANTITY_ERROR_MESSAGE);
            return REDIRECT_CART_PAGE;
        }
        //updates book quantity
        for (int i = 0; i < books.size(); i++) {
            for (Object o : books.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                //noinspection unchecked
                books.replace(pair.getKey(), pair.getValue(), Integer.valueOf(request.getParameter(QUANTITY + i)));
            }
        }
        request.getSession().setAttribute(CART, books);
        return REDIRECT_CART_PAGE;
    }
}
