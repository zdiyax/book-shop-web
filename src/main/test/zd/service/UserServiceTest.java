package zd.service;

import junit.framework.TestCase;
import zd.cp.ConnectionPool;
import zd.exception.ConnectionPoolException;
import zd.exception.ServiceException;
import zd.model.user.User;

/**
 * Zhannur Diyas
 * 2/3/2017 | 1:13 AM
 */
public class UserServiceTest extends TestCase {

    public void test() throws ConnectionPoolException {
        User user = new User();
        user.setUsername("username");
        user.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
        UserService userService = new UserService();
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.configure();
        try {
            userService.login(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}