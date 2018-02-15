package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.BookOrderedDetails;
import kz.epam.zd.model.Order;
import kz.epam.zd.service.BookOrderedDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.DETAILS;
import static kz.epam.zd.util.ConstantHolder.ID;

/**
 * User action to display Order Details page.
 */
public class ShowOrderDetailsAction implements Action {

    private static final String ORDER_DETAILS_PAGE = "order-details";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int orderId = Integer.parseInt(request.getParameter(ID));
        BookOrderedDetails bookOrderedDetails = new BookOrderedDetails();

        Order order = new Order();
        order.setId(orderId);

        BookOrderedDetailsService bookOrderedDetailsService = new BookOrderedDetailsService();
        List<BookOrderedDetails> bookOrderedDetailsResult;
        try {
            bookOrderedDetailsResult = bookOrderedDetailsService.getDetailsByOrderId(bookOrderedDetails, orderId);
            request.getSession().setAttribute(DETAILS, bookOrderedDetailsResult);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        return ORDER_DETAILS_PAGE;
    }
}
