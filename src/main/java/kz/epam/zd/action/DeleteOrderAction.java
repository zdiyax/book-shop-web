package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.REDIRECT_PREFIX;
import static kz.epam.zd.util.ConstantHolder.REFERER;

public class DeleteOrderAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        String parameter = req.getParameter("id");
        Integer orderId = Integer.parseInt(parameter);

        Order order = new Order();
        order.setId(orderId);

        OrderService orderService = new OrderService();
        try {
            orderService.deleteOrder(order, orderId);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        String referer = req.getHeader(REFERER);
        return REDIRECT_PREFIX + referer;
    }
}
