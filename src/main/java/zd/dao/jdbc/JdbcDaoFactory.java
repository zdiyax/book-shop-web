package zd.dao.jdbc;

import zd.cp.PooledConnection;
import zd.dao.BookDao;
import zd.dao.DaoFactory;

public class JdbcDaoFactory extends DaoFactory {

    private static PooledConnection connection;

    @Override
    public BookDao getBookDao() {
        return new JdbcBookDao(connection);
    }
}

