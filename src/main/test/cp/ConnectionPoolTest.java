package cp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Zhannur Diyas
 * 11/26/2016 | 10:49 AM
 */
public class ConnectionPoolTest {

    private static void init() throws ConnectionPoolException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS books (\n" +
                    "  id int NOT NULL AUTO_INCREMENT,\n" +
                    "  isbn varchar(17),\n" +
                    "  language varchar(64),\n" +
                    "  title varchar(64),\n" +
                    "  description varchar(256),\n" +
                    "  price float,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ");");
            boolean resultSet = preparedStatement.execute();
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            exec.execute(new ConnectionPoolRunnable());
        }
        exec.shutdown();
    }
}