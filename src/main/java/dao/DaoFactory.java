package dao;

import dao.jdbc.JdbcDaoFactory;

/**
 * Zhannur Diyas
 * 11/26/2016 | 12:56 AM
 */
public abstract class DaoFactory {

    public static DaoFactory getFactory() {
        return new JdbcDaoFactory();
    }

}
