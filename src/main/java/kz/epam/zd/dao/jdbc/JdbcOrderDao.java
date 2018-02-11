package kz.epam.zd.dao.jdbc;

import kz.epam.zd.dao.OrderDao;
import kz.epam.zd.model.Order;
import kz.epam.zd.model.OrderStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static kz.epam.zd.util.ConstantHolder.*;

public class JdbcOrderDao extends JdbcDao<Order> implements OrderDao {

    JdbcOrderDao(Connection connection) {
        super(connection);
    }

    @Override
    Order createEntityFromRs(ResultSet rs, Order entity) throws SQLException {
        Order order = new Order();
        order.setUserId(entity.getUserId());
        order.setId(rs.getInt(INDEX_1));
        order.setTotalPrice(rs.getInt(INDEX_2));
        order.setStatus(OrderStatus.valueOf(rs.getString(INDEX_3)));
        return order;
    }

}
