package kz.epam.zd.service;

import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.dao.BookDao;
import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.model.Book;

import java.util.List;

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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bookList;
    }

    private void addBookToCart() throws ServiceException {

    }

    public void addBook() throws ServiceException {

    }
}
