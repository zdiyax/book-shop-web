package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.BookOrderedDetails;
import kz.epam.zd.model.Order;
import kz.epam.zd.service.BookOrderedDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.REDIRECT_PREFIX;
import static kz.epam.zd.util.ConstantHolder.REFERER;

public class ShowOrderDetailsAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        int orderId = Integer.parseInt(req.getParameter("id"));
        BookOrderedDetails bookOrderedDetails = new BookOrderedDetails();


        Order order = new Order();
        order.setId(orderId);

        BookOrderedDetailsService bookOrderedDetailsService = new BookOrderedDetailsService();
        List<BookOrderedDetails> bookOrderedDetails1 = new ArrayList<>();
        try {
            bookOrderedDetails1 = bookOrderedDetailsService.getDetailsByOrderId(bookOrderedDetails, orderId);
            req.getSession().setAttribute("details", bookOrderedDetails1);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        return "order-details";
    }
}
