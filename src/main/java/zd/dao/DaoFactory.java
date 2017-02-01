package zd.dao;

import zd.dao.jdbc.JdbcDaoFactory;
import zd.exception.DaoException;

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

    public abstract AuthorDao getAuthorDao();

    public abstract LanguageDao getLanguageDao();

    public abstract ShippingAddressDao getShippingAddressDao();

    public abstract UserInfoDao getUserInfoDao();

}
