package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Operator action to edit Book entry in the database
 */
public class EditBookAction implements Action {

    private static final String BOOK_EDIT_ERROR_MESSAGE = "book.edit.error.message";
    private static final String REDIRECT_EDIT_BOOK_PAGE = "redirect:/do/?action=show-edit-book-page";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Book book = (Book) request.getSession().getAttribute(BOOK);
        book.setTitle(request.getParameter(TITLE));
        book.setAuthor(request.getParameter(AUTHOR));
        book.setPrice(Integer.parseInt(request.getParameter(PRICE)));
        book.setIsbn(request.getParameter(ISBN));
        book.setDescription(request.getParameter(DESCR));
        book.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));

        BookService bookService = new BookService();
        try {
            bookService.updateBook(book);
            request.getSession().setAttribute(BOOK, book);
        } catch (ServiceException e) {
            request.setAttribute(BOOKS + FORM_ERRORS, BOOK_EDIT_ERROR_MESSAGE);
            return REDIRECT_EDIT_BOOK_PAGE;
        }
        return REDIRECT_PREFIX + BOOK_DETAILS_BY_ID + book.getId();

    }
}
