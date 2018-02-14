package kz.epam.zd.service;

import kz.epam.zd.dao.BookOrderedDetailsDao;
import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.BookOrderedDetails;

import java.util.List;

public class BookOrderedDetailsService extends AbstractService {

    public List<BookOrderedDetails> getDetailsByOrderId(BookOrderedDetails bookOrderedDetails, int orderId) throws ServiceException {
        List<BookOrderedDetails> bookList;
        parameters.add(orderId);
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            BookOrderedDetailsDao bookDao = daoFactory.getBookOrderedDetailsDao();
            bookList = bookDao.getAllByParameters(bookOrderedDetails, parameters, "get.details.by.orderid");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bookList;
    }
}
