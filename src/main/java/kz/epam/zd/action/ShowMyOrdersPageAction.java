package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.exception.ValidatorException;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.OrderService;
import kz.epam.zd.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Customer action to display My Orders page
 */
public class ShowMyOrdersPageAction implements Action {

    private static final String MY_ORDERS = "my-orders";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final User user = (User) request.getSession().getAttribute(USER);
        final int userId = user.getId();
        OrderService orderService = new OrderService();
        try {
            final List<Order> orderList = orderService.getOrdersByUserId(userId);
            request.setAttribute(ORDERS, orderList);
        } catch (ServiceException e) {
            request.setAttribute(ORDERS + FORM_ERRORS, e.getMessage());
        }

        return MY_ORDERS;
    }
}
