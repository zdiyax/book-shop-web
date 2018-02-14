package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.CATALOG_PAGE;
import static kz.epam.zd.util.ConstantHolder.FORM_ERRORS;

public class ShowCatalogPageAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ShowCatalogPageAction.class);

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

        String search = req.getParameter("search_input");

        if (search != null) {
            BookService bookService = new BookService();
            //TODO: separate method for paging
            try {
                List<Book> books = bookService.getBooksByTitle(pageNumber, search);
                int bookAmount = books.size();
                if (bookAmount % 10 == 0) {
                    req.getSession().setAttribute("pageCount", bookAmount / 10);
                } else {
                    req.getSession().setAttribute("pageCount", bookAmount / 10 + 1);
                }
                if (books.isEmpty()) {
                    books = bookService.getBooksByTitle(1, search);
                }
                req.getSession().setAttribute("search", search);
                req.setAttribute(BOOKS, books);
            } catch (ServiceException e) {
                log.error("Error in catalog {}", e.getMessage());
                req.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
            }
            return CATALOG_PAGE;

        } else {
            BookService bookService = new BookService();
            //TODO: separate method for paging
            try {
                int bookAmount = bookService.getTotalBookAmount();
                //TODO: simplify, redundant db call
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
}
