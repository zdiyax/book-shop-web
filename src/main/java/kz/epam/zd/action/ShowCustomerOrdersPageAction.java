package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Customer action to display Customer Orders page.
 */
public class ShowCustomerOrdersPageAction implements Action {

    private static final String CUSTOMER_ORDERS = "customer-orders";

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

        return CUSTOMER_ORDERS;
    }
}
