package kz.epam.zd.dao.jdbc;

import kz.epam.zd.cp.ConnectionPool;
import kz.epam.zd.dao.*;
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
    public BookOrderedDao getBookOrderedDao() {
        return new JdbcBookOrderedDao(connection);
    }

    @Override
    public void close() throws JdbcDaoException {
        try {
            if (connection.isClosed() || !connection.isValid(1))
                log.debug("Connection is closed or invalid and won't be added to the pool");
            else {
                pool.returnConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new JdbcDaoException(e);
        }
    }

    @Override
    public void beginTransaction() throws JdbcDaoException {
        try {
            if ((!connection.isClosed()) && (connection.getAutoCommit())) {
                connection.setAutoCommit(false);
                log.debug("Transaction opened");
            }
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
    }

    @Override
    public void rollback() throws JdbcDaoException {
        try {
            if ((!connection.isClosed()) && (!connection.getAutoCommit())) {
                connection.rollback();
                connection.setAutoCommit(true);
                log.debug("Transaction rollback");
            }
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
    }

    @Override
    public void commit() throws JdbcDaoException {
        try {
            if ((!connection.isClosed()) && (!connection.getAutoCommit())) {
                connection.commit();
                log.debug("Transaction commit.");
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
    }
}

