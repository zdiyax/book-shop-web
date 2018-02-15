package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Customer action to cancel an active order.
 */
public class CancelOrderAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ChangeLocaleAction.class);
    private static final String CANCELLED_ORDER_STATUS = "cancelled";
    private static final String ORDERS_CANCEL_ERROR_MESSAGE = "orders.cancel.error.message";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));

        Order order = new Order();
        order.setId(orderId);

        OrderService orderService = new OrderService();
        try {
            orderService.updateOrderStatus(order, orderId, CANCELLED_ORDER_STATUS);
        } catch (ServiceException e) {
            request.setAttribute(ORDERS + FORM_ERRORS, ORDERS_CANCEL_ERROR_MESSAGE);
            log.debug("CancelOrderAction failed", e);
        }
        log.debug("Order cancelled. Order id = {}", orderId);

        String referer = request.getHeader(REFERER);
        return REDIRECT_PREFIX + referer;
    }
}
