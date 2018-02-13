package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowOrderSuccessPageAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        return "order-success";
    }
}
