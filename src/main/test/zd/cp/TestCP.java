package zd.cp;

import zd.dao.DaoFactory;
import zd.dao.UserDao;
import zd.dao.jdbc.JdbcDaoFactory;
import zd.model.user.User;

/**
 * Zhannur Diyas
 * 2/1/2017 | 3:12 AM
 */
public class TestCP {
    public static void main(String[] args) throws Exception {
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.configure();

        DaoFactory jdbcDaoFactory= DaoFactory.createJdbcDaoFactory();
        JdbcDaoFactory.setPool(connectionPool);
        UserDao userDao = jdbcDaoFactory.getUserDao();

    }
}
