package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Operator action to display Edit page action.
 */
public class ShowEditBookPageAction implements Action {

    private static final String EDIT_BOOK_PAGE = "edit-book";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return EDIT_BOOK_PAGE;
    }
}
