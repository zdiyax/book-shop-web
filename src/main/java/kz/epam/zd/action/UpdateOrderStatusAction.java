package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.OrderStatus;
import kz.epam.zd.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Updates Order entity's order status in the database.
 */
public class UpdateOrderStatusAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(UpdateOrderStatusAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String parameter = request.getParameter(ID);
        Integer orderId = Integer.parseInt(parameter);
        String orderStatus = request.getParameter(parameter);

        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.valueOf(orderStatus));


        OrderService orderService = new OrderService();
        try {
            orderService.updateOrderStatus(order, orderId, orderStatus);
        } catch (ServiceException e) {
            log.error("Error in UpdateOrderStatusAction.class occurred : {}", e.getMessage());
        }
        log.debug("Order status updated. Order id = {}", orderId);

        String referer = request.getHeader(REFERER);
        return REDIRECT_PREFIX + referer;
    }
}
