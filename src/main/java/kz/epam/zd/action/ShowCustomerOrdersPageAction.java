package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.OrderStatus;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.OrderService;
import kz.epam.zd.util.CookieHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderService.getOrdersByUserId(userId);
        } catch (ServiceException e) {
            request.setAttribute(ORDERS + FORM_ERRORS, e.getMessage());
        }

        Cookie localCookie = CookieHelper.findParameter(request, LOCALE);
        String locale = LOCALE_EN;

        if (localCookie != null) {
            locale = localCookie.getValue();
        }

        if (locale.equals(LOCALE_RU))
            for (Order order : orderList) {
                //noinspection SuspiciousMethodCalls
                String status = order.getStatus().name();
                order.setStatus(OrderStatus.valueOf(ORDER_STATUSES.get(status)));
            }
        request.setAttribute(ORDERS, orderList);
        request.getSession().setAttribute(STATUSES, ORDER_STATUSES_EN);

        return CUSTOMER_ORDERS;
    }
}
