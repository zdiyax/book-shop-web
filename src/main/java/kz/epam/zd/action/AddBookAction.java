package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Operator action to add Book entry to the database.
 */
public class AddBookAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ChangeLocaleAction.class);
    private static final String BOOKS_ADD_BOOK_ERROR_MESSAGE = "books.add.book.error.message";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Book book = new Book();
        book.setTitle(request.getParameter(TITLE));
        book.setAuthor(request.getParameter(AUTHOR));
        book.setPrice(Integer.parseInt(request.getParameter(PRICE)));
        book.setIsbn(request.getParameter(ISBN));
        book.setDescription(request.getParameter(DESCRIPTION));
        book.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));

        BookService bookService = new BookService();
        Book resultBook = new Book();
        try {
            resultBook = bookService.insertBook(book);
            request.getSession().setAttribute(BOOK, book);
        } catch (ServiceException e) {
            log.error("AddBookAction failed: {}", e.getMessage());
            request.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ADD_BOOK_ERROR_MESSAGE);
            return REDIRECT_PREFIX + BOOK_DETAILS_BY_ID + resultBook.getId();
        }
        log.debug("Book added to DB. Book id = {}", resultBook.getId());

        return REDIRECT_PREFIX + BOOK_DETAILS_BY_ID + resultBook.getId();
    }
}
