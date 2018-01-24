package kz.epam.zd.dao.jdbc;

import kz.epam.zd.model.Order;
import kz.epam.zd.dao.OrderDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcOrderDao extends JdbcDao<Order> implements OrderDao {
    JdbcOrderDao(Connection connection) {
        super(connection);
    }

    @Override
    Order createEntityFromRs(ResultSet rs, Order entity) throws SQLException {
        return null;
    }

}
