package kz.epam.zd.dao;

import kz.epam.zd.dao.jdbc.JdbcDaoFactory;
import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.JdbcDaoException;

/**
 * DAO Factory pattern realization
 */
public abstract class DaoFactory implements AutoCloseable {

    public static JdbcDaoFactory createJdbcDaoFactory() throws DaoException {
        return new JdbcDaoFactory();
    }

    public abstract BookDao getBookDao();

    public abstract UserDao getUserDao();

    public abstract OrderDao getOrderDao();

    public abstract BookOrderedDao getBookOrderedDao();

    @Override
    public void close() throws JdbcDaoException {

    }

    public abstract void beginTransaction() throws JdbcDaoException;

    public abstract void rollback() throws JdbcDaoException;

    public abstract void commit() throws JdbcDaoException;


}
