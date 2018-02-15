package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Customer action to display Order Success page.
 */
public class ShowOrderSuccessPageAction implements Action {

    private static final String ORDER_SUCCESS_PAGE = "order-success";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ORDER_SUCCESS_PAGE;
    }
}
