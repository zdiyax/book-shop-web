package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Operator action to display Orders page
 */
public class ShowOperatorOrdersPageAction implements Action {

    private static final String OPERATOR_ORDERS_PAGE = "operator-orders";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
            if (orderAmount % 10 == 0) {
                request.getSession().setAttribute(PAGE_COUNT, orderAmount / 10);
            } else {
                request.getSession().setAttribute(PAGE_COUNT, orderAmount / 10 + 1);
            }
            List<Order> orders = orderService.getOrdersAllPaginated(pageNumber);
            if (orders.isEmpty()) {
                orders = orderService.getOrdersAllPaginated(DEFAULT_PAGE_NUMBER);
            }
            request.getSession().setAttribute(ORDERS, orders);
        } catch (ServiceException e) {
            request.setAttribute(ORDERS + FORM_ERRORS, "SOME MESSAGE");
        }
        return OPERATOR_ORDERS_PAGE;
    }
}
