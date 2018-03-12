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
 * Operator action to delete order from displaying by deactivation.
 */
public class DeleteOrderAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(DeleteOrderAction.class);
    private static final String ORDERS_DELETE_ERROR_MESSAGE = "orders.delete.error.message";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(ID));

        Order order = new Order();
        order.setId(orderId);

        OrderService orderService = new OrderService();
        try {
            orderService.deleteOrder(order);
        } catch (ServiceException e) {
            request.setAttribute(ORDERS + FORM_ERRORS, ORDERS_DELETE_ERROR_MESSAGE);
            log.error("DeleteOrderAction failed {}", e.getMessage());
        }
        log.debug("Order deactivated. Order id = {}", orderId);
        String referer = request.getHeader(REFERER);
        return REDIRECT_PREFIX + referer;
    }
}
