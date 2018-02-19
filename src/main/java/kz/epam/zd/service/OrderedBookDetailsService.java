package kz.epam.zd.service;

import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.dao.OrderedBookDetailsDao;
import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.OrderedBookDetails;

import java.util.List;

public class OrderedBookDetailsService extends AbstractService {

    public List<OrderedBookDetails> getDetailsByOrderId(OrderedBookDetails orderedBookDetails, int orderId) throws ServiceException {
        List<OrderedBookDetails> bookList;
        parameters.add(orderId);
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            OrderedBookDetailsDao bookDao = daoFactory.getOrderedBookDetailsDao();
            bookList = bookDao.getAllByParameters(orderedBookDetails, parameters, "get.details.by.order.id");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bookList;
    }
}
