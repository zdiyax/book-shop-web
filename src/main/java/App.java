import cp.ConnectionPool;
import cp.ConnectionPoolException;
import cp.ConnectionPoolRunnable;
import dao.jdbc.JdbcBookDao;
import dao.jdbc.JdbcDaoFactory;
import dao.jdbc.JdbcException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Zhannur Diyas
 * 11/27/2016 | 7:16 PM
 */
public class App {
    public static void main(String[] args) {
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

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            exec.execute(new ConnectionPoolRunnable());
        }
        exec.shutdown();
        JdbcDaoFactory factory = new JdbcDaoFactory();
        JdbcBookDao dao = null;
        try {
            dao = factory.getJdbcBookDao(connectionPool.getConnection());
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try {
            dao.getById(1);
        } catch (JdbcException e) {
            e.printStackTrace();
        }

    }
}
