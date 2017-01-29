package zd.dao;

import zd.dao.jdbc.JdbcDaoFactory;

/**
 * DAO Factory pattern realization
 */
public abstract class DaoFactory {

    public static JdbcDaoFactory createJdbcDaoFactory(){
        return new JdbcDaoFactory();
    }

    public abstract BookDao getBookDao();

    public abstract UserDao getUserDao();

    public abstract OrderDao getOrderDao();

}
