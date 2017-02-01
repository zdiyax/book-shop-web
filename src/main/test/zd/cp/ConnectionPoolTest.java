package zd.cp;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import zd.dao.DaoFactory;
import zd.dao.UserDao;
import zd.dao.jdbc.JdbcDaoFactory;

/**
 * Zhannur Diyas
 * 2/1/2017 | 3:05 AM
 */
public class ConnectionPoolTest extends TestCase {
    @Before
    public void before() throws Exception {
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.configure();
    }
    @Test
    public void testCP() throws Exception {
        DaoFactory daoFactory = new JdbcDaoFactory();
        UserDao userDao= daoFactory.getUserDao();
    }
}