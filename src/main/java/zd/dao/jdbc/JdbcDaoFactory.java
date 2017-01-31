package zd.dao.jdbc;

import zd.cp.ConnectionPool;
import zd.dao.BookDao;
import zd.dao.DaoFactory;
import zd.dao.OrderDao;
import zd.dao.UserDao;
import zd.exception.ConnectionPoolException;
import zd.exception.JdbcDaoException;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {

    private static Connection connection;
    private static ConnectionPool pool;


    public JdbcDaoFactory() throws JdbcDaoException {
        try {
            this.connection = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new JdbcDaoException(e);
        }

    }

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

