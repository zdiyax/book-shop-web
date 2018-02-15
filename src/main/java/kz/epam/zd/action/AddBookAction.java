package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Operator action to add Book entry to the database
 */
public class AddBookAction implements Action {

    private static final String BOOKS_ERROR_MESSAGE = "books.error.message";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Book book = new Book();
        book.setTitle(request.getParameter(TITLE));
        book.setAuthor(request.getParameter(AUTHOR));
        book.setPrice(Integer.parseInt(request.getParameter(PRICE)));
        book.setIsbn(request.getParameter(ISBN));
        book.setDescription(request.getParameter(DESCR));
        book.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));

        BookService bookService = new BookService();
        Book resultBook = new Book();
        try {
            resultBook = bookService.insertBook(book);
            request.getSession().setAttribute(BOOK, book);
        } catch (ServiceException e) {
            request.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
            return REDIRECT_PREFIX + BOOK_DETAILS_BY_ID + resultBook.getId();
        }

        return REDIRECT_PREFIX + BOOK_DETAILS_BY_ID + resultBook.getId();
    }
}
