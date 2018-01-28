package kz.epam.zd.dao.jdbc;

import kz.epam.zd.cp.ConnectionPool;
import kz.epam.zd.dao.BookDao;
import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.dao.OrderDao;
import kz.epam.zd.dao.UserDao;
import kz.epam.zd.exception.ConnectionPoolException;
import kz.epam.zd.exception.JdbcDaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {
    private static final Logger log = LoggerFactory.getLogger(JdbcDaoFactory.class);
    private static ConnectionPool pool = ConnectionPool.getInstance();
    private Connection connection;

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

    @Override
    public void close() throws JdbcDaoException {
        try {
            if (connection.isClosed() || !connection.isValid(1))
                log.debug("Connection is closed or invalid and won't be added to the pool");
            else {
                pool.returnConnection(connection);
                log.debug("Connection returned to the pool successfully");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new JdbcDaoException(e);
        }
    }
}

