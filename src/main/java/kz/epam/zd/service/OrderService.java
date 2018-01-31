package kz.epam.zd.service;

import kz.epam.zd.action.ShowMyOrdersPageAction;
import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.dao.OrderDao;
import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderService extends AbstractService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public void makeOrder() {

    }

    public List<Order> getAllOrders() {
        return null;
    }

    public List<Order> getOrdersByUserId(Integer userId) throws ServiceException {
        parameters.add(userId);
        Order order = new Order();
        order.setUserId(userId);
        List<Order> orderList;
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            orderList = orderDao.getAllByParameters(order, parameters, "get.orders.by.userid");
        } catch (DaoException e) {
            log.debug("Error in OrderService occurred");
            throw new ServiceException(e);
        }
        return orderList;
    }
}
