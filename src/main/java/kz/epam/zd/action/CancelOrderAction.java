package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Customer action to cancel an active order.
 */
public class CancelOrderAction implements Action {

    private static final String CANCELLED_ORDER_STATUS = "cancelled";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));

        Order order = new Order();
        order.setId(orderId);

        OrderService orderService = new OrderService();
        try {
            orderService.updateOrderStatus(order, orderId, CANCELLED_ORDER_STATUS);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        String referer = request.getHeader(REFERER);
        return REDIRECT_PREFIX + referer;
    }
}
