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

/**
 * Global action to display Catalog page.
 */
//TODO: URGENT FORMAT
public class ShowCatalogPageAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowCatalogPageAction.class);

    private static final String BOOKS = "books";
    private static final String BOOKS_ERROR_MESSAGE = "books.error.message";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("page", pageNumber);
            return CATALOG_PAGE;
        }
        request.getSession().setAttribute("page", pageNumber);

        String search = request.getParameter("search_input");

        if (search != null) {
            BookService bookService = new BookService();
            //TODO: separate method for paging
            try {
                List<Book> books = bookService.getBooksByTitle(pageNumber, search);
                int bookAmount = books.size();
                if (bookAmount % 10 == 0) {
                    request.getSession().setAttribute("pageCount", bookAmount / 10);
                } else {
                    request.getSession().setAttribute("pageCount", bookAmount / 10 + 1);
                }
                if (books.isEmpty()) {
                    books = bookService.getBooksByTitle(1, search);
                }
                request.getSession().setAttribute("search", search);
                request.setAttribute(BOOKS, books);
            } catch (ServiceException e) {
                log.error("Error in catalog {}", e.getMessage());
                request.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
            }
            return CATALOG_PAGE;

        } else {
            BookService bookService = new BookService();
            //TODO: separate method for paging
            try {
                int bookAmount = bookService.getTotalBookAmount();
                //TODO: simplify, redundant db call
                if (bookAmount % 10 == 0) {
                    request.getSession().setAttribute("pageCount", bookAmount / 10);
                } else {
                    request.getSession().setAttribute("pageCount", bookAmount / 10 + 1);
                }
                List<Book> books = bookService.getBooksAll(pageNumber);
                if (books.isEmpty()) {
                    books = bookService.getBooksAll(1);
                }
                request.setAttribute(BOOKS, books);
            } catch (ServiceException e) {
                request.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
            }
            return CATALOG_PAGE;
        }
    }
}
