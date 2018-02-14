package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.FORM_ERRORS;
import static kz.epam.zd.util.ConstantHolder.REDIRECT_PREFIX;

public class AddBookAction implements Action {

    private static final String BOOKS = "books";
    private static final String BOOK = "book";
    private static final String BOOKS_ERROR_MESSAGE = "books.error.message";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ActionException {
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setPrice(Integer.parseInt(req.getParameter("price")));
        book.setIsbn(req.getParameter("isbn"));
        book.setDescription(req.getParameter("description"));
        book.setQuantity(Integer.parseInt(req.getParameter("quantity")));

        BookService bookService = new BookService();
        Book resultBook = new Book();
        try {
            resultBook = bookService.insertBook(book);
            req.getSession().setAttribute(BOOK, book);
        } catch (ServiceException e) {
            req.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
            return REDIRECT_PREFIX + "/do/?action=show-detailed-book-info&id=" + resultBook.getId();
        }


        return REDIRECT_PREFIX + "/do/?action=show-detailed-book-info&id=" + resultBook.getId();
    }
}
