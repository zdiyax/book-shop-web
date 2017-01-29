package zd.dao.jdbc;

import zd.cp.PooledConnection;
import zd.dao.OrderDao;
import zd.exception.JdbcDaoException;
import zd.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcOrderDao extends JdbcDao<Order> implements OrderDao {
    JdbcOrderDao(PooledConnection connection) {
        super(connection);
    }

    @Override
    public List<Order> getAllByQuery(String query) throws JdbcDaoException {
        return null;
    }

    @Override
    protected void setPsFields(PreparedStatement statement, Order order) throws JdbcDaoException {

    }

    @Override
    protected Order createEntityFromRs(ResultSet rs) throws SQLException, JdbcDaoException {
        return null;
    }

    @Override
    protected String getInsertQuery() {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected String getSelectByIdQuery(int id) {
        return null;
    }

    @Override
    protected String getSelectAllQuery() {
        return null;
    }
}
