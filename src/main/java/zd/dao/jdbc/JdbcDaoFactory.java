package zd.dao.jdbc;

import zd.cp.ConnectionPool;
import zd.dao.*;
import zd.exception.JdbcDaoException;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {

    private Connection connection;
    private static ConnectionPool pool = ConnectionPool.getInstance();


    public JdbcDaoFactory() throws JdbcDaoException {

        try {
            this.connection = pool.getConnection();
        } catch (Exception e) {
            throw new JdbcDaoException(e);
        }

    }

    public static void setPool(ConnectionPool pool) {
        JdbcDaoFactory.pool = pool;
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

