package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Global action to display Catalog page.
 */
public class ShowCatalogPageAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(ShowCatalogPageAction.class);
    private static final String BOOKS = "books";
    private static final String BOOKS_ERROR_MESSAGE = "books.error.message";
    private static final String CATALOG_PAGE = "catalog";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int pageNumber = DEFAULT_PAGE_NUMBER;
        try {
            pageNumber = Integer.parseInt(request.getParameter(PAGE));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(PAGE, pageNumber);
            return CATALOG_PAGE;
        }
        request.getSession().setAttribute(PAGE, pageNumber);

        String search = request.getParameter(SEARCH_INPUT);

        if (search != null) {
            return search(request, search);

        } else {
            BookService bookService = new BookService();
            try {
                int bookAmount = bookService.getTotalBookAmount();
                if (bookAmount % DEFAULT_PAGE_COUNT == ZERO) {
                    request.getSession().setAttribute(PAGE_COUNT, bookAmount / DEFAULT_PAGE_COUNT);
                } else {
                    request.getSession().setAttribute(PAGE_COUNT, bookAmount / DEFAULT_PAGE_COUNT + ONE);
                }
                List<Book> books = bookService.getBooksAll(pageNumber);
                if (books.isEmpty()) {
                    books = bookService.getBooksAll(DEFAULT_PAGE_NUMBER);
                }
                request.setAttribute(BOOKS, books);
            } catch (ServiceException e) {
                request.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
            }
            return CATALOG_PAGE;
        }
    }

    /**
     * Searches for books by book title if search parameter is present.
     * @param request http request
     * @param title - title to be searched for
     * @return jsp page
     */
    private String search(HttpServletRequest request, String title) {
        BookService bookService = new BookService();
        try {
            List<Book> books = bookService.getBooksByTitle(title);
            request.getSession().setAttribute(PAGE_COUNT,  ONE);
            request.getSession().setAttribute(SEARCH, title);
            request.setAttribute(BOOKS, books);
        } catch (ServiceException e) {
            log.error("Error in catalog {}", e.getMessage());
            request.setAttribute(BOOKS + FORM_ERRORS, BOOKS_ERROR_MESSAGE);
        }
        return CATALOG_PAGE;
    }
}
