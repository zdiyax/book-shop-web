package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Operator action to display Add Book page
 */
public class ShowAddBookPageAction implements Action {

    private static final String ADD_BOOK = "add-book";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ADD_BOOK;
    }
}
