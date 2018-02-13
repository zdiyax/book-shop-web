package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.OrderStatus;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.OrderService;
import kz.epam.zd.util.ValidatorHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.*;

public class OrderBooksAction implements Action {
    private static final String CART = "cart";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        HashMap books = (HashMap) req.getSession().getAttribute("cart");
        if (books.isEmpty()) {
            req.getSession().setAttribute("cartFormErrors", "empty.cart.message");
            return REDIRECT_PREFIX + "/do/?action=show-cart-page";
        }

        if (ValidatorHelper.checkCartForm(req, books))
            return REDIRECT_PREFIX + "/do/?action=show-cart-page";

        final User user = (User) req.getSession().getAttribute(USER);
        final int userId = user.getId();

        for (int i = 0; i < books.size(); i++) {
            for (Object o : books.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                books.replace(pair.getKey(), pair.getValue(), Integer.valueOf(req.getParameter("quantity" + i)));
            }
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.valueOf("waiting"));


        OrderService orderService = new OrderService();
        try {
            orderService.makeOrder(order, userId, books);
        } catch (ServiceException e) {
            req.setAttribute(ORDERS + FORM_ERRORS, e.getMessage());
        }

        req.getSession().setAttribute("cart", new HashMap<Book, Integer>());
        return REDIRECT_PREFIX + "/do/?action=show-order-success-page";
    }

}
