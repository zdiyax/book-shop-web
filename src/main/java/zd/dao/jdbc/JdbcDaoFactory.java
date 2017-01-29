package zd.dao.jdbc;

import zd.cp.PooledConnection;
import zd.dao.BookDao;
import zd.dao.DaoFactory;
import zd.dao.OrderDao;
import zd.dao.UserDao;

public class JdbcDaoFactory extends DaoFactory {

    private static PooledConnection connection;

    @Override
    public BookDao getBookDao() {
        return new JdbcBookDao(connection);
    }

    @Override
    public UserDao getUserDao() {
        return new JdbcUserDao(connection);
    }

    @Override
    public OrderDao getOrderDao() {
        return new JdbcOrderDao(connection);
    }
}

