package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.service.OrderService;
import kz.epam.zd.util.CookieHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Operator action to display Orders page.
 */
public class ShowOperatorOrdersPageAction implements Action {

    private static final String OPERATOR_ORDERS_PAGE = "operator-orders";
    private static final String ORDERS_OPERATOR_ERROR_MESSAGE = "orders.operator.error.message";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie localCookie = CookieHelper.findParameter(request, LOCALE);
        String locale = LOCALE_EN;

        if (localCookie != null) {
            locale = localCookie.getValue();
        }

        if (locale.equals(LOCALE_RU))
            request.getSession().setAttribute(STATUSES, ORDER_STATUSES_RU);
        else
            request.getSession().setAttribute(STATUSES, ORDER_STATUSES_EN);

        int pageNumber = DEFAULT_PAGE_NUMBER;
        try {
            pageNumber = Integer.parseInt(request.getParameter(PAGE));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(PAGE, pageNumber);
            return OPERATOR_ORDERS_PAGE;
        }
        request.getSession().setAttribute(PAGE, pageNumber);


        OrderService orderService = new OrderService();
        try {
            int orderAmount = orderService.getTotalOrderAmount();
            if (orderAmount % DEFAULT_PAGE_COUNT == ZERO) {
                request.getSession().setAttribute(PAGE_COUNT, orderAmount / DEFAULT_PAGE_COUNT);
            } else {
                request.getSession().setAttribute(PAGE_COUNT, orderAmount / DEFAULT_PAGE_COUNT + ONE);
            }
            List<Order> orders = orderService.getOrdersAllPaginated(pageNumber);
            if (orders.isEmpty()) {
                orders = orderService.getOrdersAllPaginated(DEFAULT_PAGE_NUMBER);
            }
            request.getSession().setAttribute(ORDERS, orders);
        } catch (ServiceException e) {
            request.setAttribute(ORDERS + FORM_ERRORS, ORDERS_OPERATOR_ERROR_MESSAGE);
        }
        return OPERATOR_ORDERS_PAGE;
    }
}
