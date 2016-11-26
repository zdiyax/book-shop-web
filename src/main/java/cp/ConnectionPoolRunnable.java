package cp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Zhannur Diyas
 * 11/23/2016 | 9:40 PM
 */
public class ConnectionPoolRunnable implements Runnable {
    @Override
    public void run() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books (isbn, language, title, description, price) VALUES (isbn,  language, title, description, 1000);");
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
