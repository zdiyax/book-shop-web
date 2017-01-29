package zd.service;

import zd.dao.BookDao;
import zd.dao.DaoFactory;
import zd.exception.DaoException;
import zd.exception.ServiceException;
import zd.model.Book;

import java.util.List;

/**
 * Zhannur Diyas
 * 1/15/2017 | 6:50 AM
 */
public class BookService extends AbstractService {
    private static final String GET_BOOKS_ALL = "get.all.books";
    private static final String GET_BOOKS_BY_DOMAIN = "get.books.by.domain";

    public List<Book> getAllBooks() throws ServiceException {
        return getBooksByQuery(GET_BOOKS_ALL);
    }

    public List<Book> getBooksByDomain() throws ServiceException {
        return getBooksByQuery(GET_BOOKS_BY_DOMAIN);
    }

    private List<Book> getBooksByQuery(String query) throws ServiceException {
        List<Book> bookList = null;
        try {
            DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory();
            BookDao bookDao = daoFactory.getBookDao();
            bookList = bookDao.getAllByQuery(query);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bookList;
    }

    private void addBookToCart() throws ServiceException {

    }
}
