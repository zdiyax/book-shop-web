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
 * Operator action to edit Book entry in the database.
 */
public class EditBookAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(DeleteOrderAction.class);
    private static final String BOOKS_EDIT_ERROR_MESSAGE = "books.edit.error.message";
    private static final String REDIRECT_EDIT_BOOK_PAGE = "redirect:/do/?action=show-edit-book-page";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Book book = (Book) request.getSession().getAttribute(BOOK);
        book.setTitle(request.getParameter(TITLE));
        book.setAuthor(request.getParameter(AUTHOR));
        book.setPrice(Integer.parseInt(request.getParameter(PRICE)));
        book.setIsbn(request.getParameter(ISBN));
        book.setDescription(request.getParameter(DESCRIPTION));
        book.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));

        BookService bookService = new BookService();
        try {
            bookService.updateBook(book);
            request.getSession().setAttribute(BOOK, book);
        } catch (ServiceException e) {
            log.error("EditBookAction failed {}", e.getMessage());
            request.setAttribute(BOOKS + FORM_ERRORS, BOOKS_EDIT_ERROR_MESSAGE);
            return REDIRECT_EDIT_BOOK_PAGE;
        }
        log.debug("Book successfully edited. Book id = {}", book.getId());

        return REDIRECT_PREFIX + BOOK_DETAILS_BY_ID + book.getId();
    }
}
