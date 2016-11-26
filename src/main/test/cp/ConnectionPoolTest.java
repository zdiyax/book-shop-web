package cp;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Zhannur Diyas
 * 11/26/2016 | 10:49 AM
 */
public class ConnectionPoolTest {

    private static void init() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            Connection connection = connectionPool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new ConnectionPoolRunnable());
        }
    }
}