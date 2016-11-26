package cp;

import java.sql.Connection;

/**
 * Zhannur Diyas
 * 11/26/2016 | 10:49 AM
 */
public class ConnectionPoolTest {

    static Connection connection;

    public static void init() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connection = connectionPool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        init();
    }
}