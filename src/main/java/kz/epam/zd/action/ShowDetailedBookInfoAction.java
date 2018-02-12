package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.FORM_ERRORS;

public class ShowDetailedBookInfoAction implements Action {
    private static final String BOOKS = "books";
    private static final String BOOK = "book";
    private static final String BOOKS_ERROR_MESSAGE = "books.error.message";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        int id = Integer.parseInt(req.getParameter("id"));

        BookService bookService = new BookService();
        try {
            Book book = bookService.getBookById(id);
            req.getSession().setAttribute(BOOK, book);
        } catch (ServiceException e) {
            req.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
            return "book-details";
        }
        return "book-details";
    }
}
