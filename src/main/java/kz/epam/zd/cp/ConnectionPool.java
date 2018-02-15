package kz.epam.zd.cp;

import kz.epam.zd.exception.ConnectionPoolException;
import kz.epam.zd.exception.PropertyManagerException;
import kz.epam.zd.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Custom connection pool implementation. Creates and stores database connections.
 * Provides connections to use to DAO layer and gets them offered back.
 */
public class ConnectionPool {

    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private static final String PROPERTY_FILE_NAME = "db.properties";
    private static final String JDBC_DRIVER_PROPERTY = "jdbc.driver";
    private static final String JDBC_URL_PROPERTY = "jdbc.url";
    private static final String JDBC_USERNAME_PROPERTY = "jdbc.username";
    private static final String JDBC_PASSWORD_PROPERTY = "jdbc.password";
    private static final String JDBC_POOL_SIZE_PROPERTY = "jdbc.poolsize";
    private static final int TIMEOUT = 1;
    private static final int POOL_SIZE_MULTIPLIER = 2;

    private BlockingQueue<Connection> connections;
    private String url;
    private String username;
    private String password;
    private int poolSize;

    /**
     * Loads properties from property file using PropertyManager.
     * Executes {@code fill()} method after.
     *
     * @throws ConnectionPoolException - if properties failed to load in
     */
    public void configure() throws ConnectionPoolException {
        try {
            PropertyManager propertyManager = new PropertyManager(PROPERTY_FILE_NAME);
            Properties properties = propertyManager.getProperties();
            log.debug("Properties value: {}", properties);

            String driverName = properties.getProperty(JDBC_DRIVER_PROPERTY);
            try {
                Class.forName(driverName);
            } catch (ClassNotFoundException e) {
                throw new ConnectionPoolException(e);
            }
            url = properties.getProperty(JDBC_URL_PROPERTY);
            username = properties.getProperty(JDBC_USERNAME_PROPERTY);
            password = properties.getProperty(JDBC_PASSWORD_PROPERTY);
            poolSize = Integer.parseInt(properties.getProperty(JDBC_POOL_SIZE_PROPERTY));
            log.info("Properties set");
        } catch (PropertyManagerException e) {
            throw new ConnectionPoolException(e);
        }
        fill();
    }

    /**
     * Fills the connection pool with new connections using DriverManager.
     */
    private void fill() throws ConnectionPoolException {
        connections = new ArrayBlockingQueue<>(poolSize);
        try {
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                if (connection != null) {
                    connections.offer(connection);
                }
            }
        } catch (SQLException e) {
            log.error("Couldn't load connections in connection pool |" + e);
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Obtains connection from ConnectionPool.
     *
     * @return connection from ConnectionPool
     * @throws ConnectionPoolException if pool is empty
     */
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            if (connections.peek() == null) {
                connections = new ArrayBlockingQueue<>(poolSize * POOL_SIZE_MULTIPLIER);
                connection = DriverManager.getConnection(url, username, password);
                for (int i = 0; i < poolSize * 2; i++) {
                    connections.offer(connection);
                }
            } else {
                connection = connections.take();
            }
        } catch (InterruptedException | SQLException e) {
            log.debug("Connection error occurred. Thread interrupted.");
            throw new ConnectionPoolException();
        }
        if (connection == null) {
            throw new ConnectionPoolException("Connection is null.");
        }
        return connection;
    }

    /**
     * Returns connection back to the ConnectionPool.
     *
     * @param connection connection to be returned
     * @throws ConnectionPoolException if connection cannot be returned at the moment
     */
    public void returnConnection(Connection connection) throws ConnectionPoolException {
        try {
            if (connection.isValid(TIMEOUT))
                connections.offer(connection);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Closes the ConnectionPool.
     *
     * @throws ConnectionPoolException if ConnectionPool cannot be closed at the moment
     */
    public synchronized void close() throws ConnectionPoolException {
        for (Connection con : connections) {
            try {
                if (con.isClosed()) {
                    con.close();
                    log.debug("Connection to the pool closed");
                }
            } catch (SQLException e) {
                log.error("Can't close connection to the pool");
                throw new ConnectionPoolException(e);
            }
        }
        connections.clear();
    }

}
