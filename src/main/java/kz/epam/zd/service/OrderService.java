package kz.epam.zd.service;

import kz.epam.zd.dao.BookOrderedDao;
import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.dao.OrderDao;
import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.model.BookOrdered;
import kz.epam.zd.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService extends AbstractService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private static final String MAKE_ORDER_KEY = "make.order";
    private static final String INSERT_BOOK_ORDERED = "insert.bookordered";

    private static final int ORDERS_PER_PAGE = 10;
    private static final String UPDATE_ORDER_STATUS = "update.order.status";
    private static final String DELETE_ORDER = "delete.order";


    public void makeOrder(Order order, int userId, HashMap books) throws ServiceException {
        int totalCost = calculateTotalCost(books);

        parameters.add(userId);
        parameters.add(order.getStatus().toString());
        parameters.add(totalCost);
        Order resultOrder;
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            resultOrder = orderDao.insert(order, parameters, MAKE_ORDER_KEY);
        } catch (DaoException e) {
            log.debug("Error in OrderService occurred: " + e.getMessage());
            throw new ServiceException(e);
        }


        for (Object o : books.entrySet()) {
            parameters.clear();
            parameters.add(resultOrder.getId());
            Map.Entry pair = (Map.Entry) o;
            Book book1 = (Book) pair.getKey();
            int quantity = (int) pair.getValue();
            parameters.add(book1.getId());
            parameters.add(quantity);
            BookOrdered bookOrdered = new BookOrdered();
            bookOrdered.setOrderId(resultOrder.getId());
            bookOrdered.setBookId(book1.getId());
            bookOrdered.setQuantity(quantity);

            try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
                BookOrderedDao bookOrderedDao = daoFactory.getBookOrderedDao();
                bookOrderedDao.insert(bookOrdered, parameters, INSERT_BOOK_ORDERED);
            } catch (DaoException e) {
                log.debug("Error in OrderService occurred: " + e.getMessage());
                throw new ServiceException(e);
            }
        }

    }

    private int calculateTotalCost(HashMap books) {
        int totalCost = 0;
        for (Object o : books.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            Book book1 = (Book) pair.getKey();
            totalCost = totalCost + book1.getPrice() * (int) pair.getValue();
        }
        return totalCost;
    }

    public List<Order> getOrdersByUserId(Integer userId) throws ServiceException {
        parameters.add(userId);
        Order order = new Order();
        order.setUserId(userId);
        return getOrdersByQuery(order, "get.orders.by.userid");
    }

    private List<Order> getOrdersByQuery(Order order, String query) throws ServiceException {
        List<Order> orderList;
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            orderList = orderDao.getAllByParameters(order, parameters, query);
        } catch (DaoException e) {
            log.debug("Error in OrderService occurred: {}", e.getMessage());
            throw new ServiceException();
        }
        return orderList;
    }

    public int getTotalOrderAmount() throws ServiceException {
        List<Order> orderList = getOrdersByQuery(new Order(), "get.orders.all");
        return orderList.size();
    }

    public List<Order> getOrdersAllPaginated(int pageNumber) throws ServiceException {
        int offset = --pageNumber * ORDERS_PER_PAGE;
        parameters.add(offset);
        return getOrdersByQuery(new Order(), "get.orders.all.paginated");
    }

    public void updateOrderStatus(Order order, Integer orderId, String orderStatus) throws ServiceException {
        parameters.add(orderStatus);
        parameters.add(orderId);
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            orderDao.update(order, parameters, UPDATE_ORDER_STATUS);
        } catch (DaoException e) {
            log.debug("Error in OrderService occurred: " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    public void deleteOrder(Order order, Integer orderId) throws ServiceException {
        parameters.add(orderId);
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            orderDao.delete(order, parameters, DELETE_ORDER);
        } catch (DaoException e) {
            log.debug("Error in OrderService occurred: " + e.getMessage());
            throw new ServiceException(e);
        }
    }
}
