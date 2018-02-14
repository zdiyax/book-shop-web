package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.*;

public class ShowOperatorOrdersPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("page", pageNumber);
            return "operator-orders";
        }
        req.getSession().setAttribute("page", pageNumber);


        OrderService orderService = new OrderService();
        try {
            int orderAmount = orderService.getTotalOrderAmount();
            if (orderAmount % 10 == 0) {
                req.getSession().setAttribute("pageCount", orderAmount / 10);
            } else {
                req.getSession().setAttribute("pageCount", orderAmount / 10 + 1);
            }
            List<Order> orders = orderService.getOrdersAllPaginated(pageNumber);
            if (orders.isEmpty()) {
                orders = orderService.getOrdersAllPaginated(1);
            }
            req.getSession().setAttribute(ORDERS, orders);
        } catch (ServiceException e) {
            req.setAttribute(ORDERS + FORM_ERRORS, "SOME MESSAGE");
        }
        return "operator-orders";
    }
}
