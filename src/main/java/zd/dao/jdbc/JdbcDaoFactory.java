package zd.dao.jdbc;

import zd.cp.PooledConnection;
import zd.dao.DaoFactory;

/**
 * Zhannur Diyas
 * 11/26/2016 | 1:01 AM
 */
public class JdbcDaoFactory extends DaoFactory {


    @Override
    public JdbcBookDao getJdbcBookDao(PooledConnection connection) {
        return new JdbcBookDao(connection);
    }

}

