package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Operator action to display Add Book page.
 */
public class ShowAddBookPageAction implements Action {

    private static final String ADD_BOOK_PAGE = "add-book";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ADD_BOOK_PAGE;
    }
}
