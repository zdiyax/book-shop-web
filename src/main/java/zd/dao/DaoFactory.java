package zd.dao;

import zd.cp.PooledConnection;
import zd.dao.jdbc.JdbcDaoFactory;

/**
 * Zhannur Diyas
 * 11/26/2016 | 12:56 AM
 */
public abstract class DaoFactory {

    public static JdbcDaoFactory createFactory(){
        return new JdbcDaoFactory();
    }

    public abstract BookDao getJdbcBookDao(PooledConnection connection);

}
