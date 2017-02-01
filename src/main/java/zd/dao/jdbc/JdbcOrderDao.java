package zd.dao.jdbc;

import zd.dao.OrderDao;
import zd.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcOrderDao extends JdbcDao<Order> implements OrderDao {
    JdbcOrderDao(Connection connection) {
        super(connection);
    }

    @Override
    Order createEntityFromRs(ResultSet rs) throws SQLException {
        return null;
    }

}
