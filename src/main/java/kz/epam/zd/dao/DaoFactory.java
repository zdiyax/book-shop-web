package kz.epam.zd.dao;

import kz.epam.zd.dao.jdbc.JdbcDaoFactory;
import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.JdbcDaoException;

/**
 * Factory pattern implementation for creating DAOs
 */
public abstract class DaoFactory implements AutoCloseable {

    public static JdbcDaoFactory createJdbcDaoFactory() throws DaoException {
        return new JdbcDaoFactory();
    }

    public abstract BookDao getBookDao();

    public abstract UserDao getUserDao();

    public abstract OrderDao getOrderDao();

    public abstract OrderedBookDao getOrderedBookDao();

    public abstract OrderedBookDetailsDao getOrderedBookDetailsDao();

    @Override
    public void close() throws JdbcDaoException {

    }

}
