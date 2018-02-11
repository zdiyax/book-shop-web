package kz.epam.zd.service;

import kz.epam.zd.dao.BookDao;
import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookService extends AbstractService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private static final String GET_BOOKS_PAGINATED = "get.books.paginated";
    private static final String GET_BOOKS_AMOUNT = "get.books.amount";


    private static final int BOOKS_PER_PAGE = 10;

    private List<Book> getBooksByQuery(Book book, String query) throws ServiceException {
        log.debug("Entering getBooksByQuery() method");
        List<Book> bookList;
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            BookDao bookDao = daoFactory.getBookDao();
            bookList = bookDao.getAllByParameters(book, parameters, query);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bookList;
    }

    public List<Book> getBooksPaginated(int pageNumber) throws ServiceException {
        int offset = --pageNumber * BOOKS_PER_PAGE;
        parameters.add(offset);
        return getBooksByQuery(new Book(), GET_BOOKS_PAGINATED);
    }

    public int getTotalBookAmount() throws ServiceException {
        List<Book> books = getBooksByQuery(new Book(), GET_BOOKS_AMOUNT);
        return books.size();
    }
}
