package kz.epam.zd.dao;

import kz.epam.zd.dao.jdbc.JdbcDaoFactory;
import kz.epam.zd.exception.DaoException;

/**
 * DAO Factory pattern realization
 */
public abstract class DaoFactory {

    public static JdbcDaoFactory createJdbcDaoFactory() throws DaoException {
        return new JdbcDaoFactory();
    }

    public abstract BookDao getBookDao();

    public abstract UserDao getUserDao();

    public abstract OrderDao getOrderDao();


}
