package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.CATALOG_PAGE;
import static kz.epam.zd.util.ConstantHolder.FORM_ERRORS;

public class ShowCatalogPageAction implements Action {
    private static final String BOOKS = "books";
    private static final String BOOKS_ERROR_MESSAGE = "books.error.message";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("page", pageNumber);
            return CATALOG_PAGE;
        }
        req.getSession().setAttribute("page", pageNumber);

        BookService bookService = new BookService();
        try {
            int bookAmount = bookService.getTotalBookAmount();
            if (bookAmount % 10 == 0) {
                req.getSession().setAttribute("pageCount", bookAmount / 10);
            } else {
                req.getSession().setAttribute("pageCount", bookAmount / 10 + 1);
            }
            List<Book> books = bookService.getBooksAll(pageNumber);
            if (books.isEmpty()) {
                books = bookService.getBooksAll(1);
            }
            req.setAttribute(BOOKS, books);
        } catch (ServiceException e) {
            req.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
        }
        return CATALOG_PAGE;
    }
}
