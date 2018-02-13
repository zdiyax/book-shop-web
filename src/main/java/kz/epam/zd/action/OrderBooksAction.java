package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.OrderStatus;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static kz.epam.zd.util.ConstantHolder.*;

public class OrderBooksAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
//        Functionality here
        HashMap books = (HashMap) req.getSession().getAttribute("cart");
        final User user = (User) req.getSession().getAttribute(USER);
        final int userId = user.getId();

        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.valueOf("waiting"));


        OrderService orderService = new OrderService();
        try {
            orderService.makeOrder(order, userId, books);
        } catch (ServiceException e) {
            req.setAttribute(ORDERS + ERROR, e.getMessage());
        }

        req.getSession().setAttribute("cart", new HashMap<Book, Integer>());
        return REDIRECT_PREFIX + "/do/?action=show-order-success-page";
    }
}
