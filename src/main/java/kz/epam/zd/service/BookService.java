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

    private static final String GET_BOOKS_AMOUNT = "get.books.amount";
    private static final String GET_BOOKS_ALL_ACTIVE = "get.books.all";
    private static final String GET_BOOKS_BY_TITLE = "get.books.by.title";
    private static final String GET_BOOK_BY_ID = "get.book.by.id";

    private static final int BOOKS_PER_PAGE = 10;

    private List<Book> getBooksByQuery(Book book, String query) throws ServiceException {
        List<Book> bookList;
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            BookDao bookDao = daoFactory.getBookDao();
            bookList = bookDao.getAllByParameters(book, parameters, query);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bookList;
    }

    public List<Book> getBooksAll(int pageNumber) throws ServiceException {
        int offset = --pageNumber * BOOKS_PER_PAGE;
        parameters.add(offset);
        return getBooksByQuery(new Book(), GET_BOOKS_ALL_ACTIVE);
    }

    public int getTotalBookAmount() throws ServiceException {
        List<Book> books = getBooksByQuery(new Book(), GET_BOOKS_AMOUNT);
        return books.size();
    }

    public List<Book> getBooksByTitle(int pageNumber, String title) throws ServiceException {
        parameters.add(title);
        int offset = --pageNumber * BOOKS_PER_PAGE;
        parameters.add(offset);
        return getBooksByQuery(new Book(), GET_BOOKS_BY_TITLE);
    }

    public Book getBookById(int id) throws ServiceException {
        parameters.add(id);
        return getBooksByQuery(new Book(), GET_BOOK_BY_ID).get(0);
    }
}
