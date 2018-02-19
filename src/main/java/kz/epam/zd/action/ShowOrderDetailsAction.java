package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.OrderedBookDetails;
import kz.epam.zd.service.OrderedBookDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.DETAILS;
import static kz.epam.zd.util.ConstantHolder.ID;

/**
 * User action to display Order Details page.
 */
public class ShowOrderDetailsAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowOrderDetailsAction.class);
    private static final String ORDER_DETAILS_PAGE = "order-details";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(ID));
        OrderedBookDetails orderedBookDetails = new OrderedBookDetails();

        Order order = new Order();
        order.setId(orderId);

        OrderedBookDetailsService orderedBookDetailsService = new OrderedBookDetailsService();
        List<OrderedBookDetails> orderedBookDetailsResult;
        try {
            orderedBookDetailsResult = orderedBookDetailsService.getDetailsByOrderId(orderedBookDetails, orderId);
            request.getSession().setAttribute(DETAILS, orderedBookDetailsResult);
        } catch (ServiceException e) {
            log.error("ShowOrderDetailsAction failed: {}", e.getMessage());
        }

        return ORDER_DETAILS_PAGE;
    }
}
