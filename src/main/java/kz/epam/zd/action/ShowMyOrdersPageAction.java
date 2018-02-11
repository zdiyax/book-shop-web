package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.exception.ValidatorException;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.OrderService;
import kz.epam.zd.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.*;

public class ShowMyOrdersPageAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ShowMyOrdersPageAction.class);
    private static final String MY_ORDERS = "my-orders";
    private static final String PERSONAL_INFO = "personalinfo";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            FormValidator validator = new FormValidator();
            Map<String, List<String>> fieldErrors = validator.validate(PERSONAL_INFO, request);
            if (validator.hasFieldsErrors(request, fieldErrors))
                return REDIRECT_PREFIX + "/do/?action=show-my-orders-page";
        } catch (ValidatorException e) {
            throw new ActionException(e);
        }

        final User user = (User) request.getSession().getAttribute(USER);
        log.debug("Personal info form is valid");
        final int userId = user.getId();
        OrderService orderService = new OrderService();
        try {
            final List<Order> orderList = orderService.getOrdersByUserId(userId);
            request.setAttribute(ORDERS, orderList);
        } catch (ServiceException e) {
            request.setAttribute(ORDERS + FORM_ERRORS, e.getMessage());
        }
        return MY_ORDERS;
    }
}
