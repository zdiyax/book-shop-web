package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Global action to display Book details page by id.
 */
public class ShowBookDetailsAction implements Action {

    private static final String BOOK_DETAILS_PAGE = "book-details";
    private static final String BOOKS_ERROR_MESSAGE = "books.error.message";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));

        BookService bookService = new BookService();
        try {
            Book book = bookService.getBookById(id);
            request.getSession().setAttribute(BOOK, book);
        } catch (ServiceException e) {
            request.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
            return BOOK_DETAILS_PAGE;
        }
        return BOOK_DETAILS_PAGE;
    }
}
