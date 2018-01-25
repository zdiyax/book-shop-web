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
 * Custom connection pool implementation using Singleton DP
 */
public class ConnectionPool {
    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private static final String PROPERTY_FILE_NAME = "db.properties";
    private static final String JDBC_DRIVERNAME_PROPERTY = "jdbc.driver";
    private static final String JDBC_URL_PROPERTY = "jdbc.url";
    private static final String JDBC_USERNAME_PROPERTY = "jdbc.username";
    private static final String JDBC_PASSWORD_PROPERTY = "jdbc.password";
    private static final String JDBC_POOLSIZE_PROPERTY = "jdbc.poolsize";
    private static ConnectionPool instance;
    private static BlockingQueue<Connection> connections;
    private String driverName;
    private String url;
    private String username;
    private String password;
    private int poolSize;

    public static ConnectionPool getInstance() {
        return InstanceHolder.instance;
    }

    static BlockingQueue<Connection> getConnections() {
        return connections;
    }

    /**
     * Method fills the connection pool with connections using DriverManager
     */
    private void fill() throws ConnectionPoolException {
        connections = new ArrayBlockingQueue<Connection>(poolSize);
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
     * Method loads properties from property file using PropertyManager
     *
     * @throws ConnectionPoolException - if properties failed to load in
     */
    public void configure() throws ConnectionPoolException {
        try {
            PropertyManager propertyManager = new PropertyManager("db.properties");
            Properties properties = propertyManager.getProperties();
            log.debug("Properties value: {}", properties);

            this.driverName = properties.getProperty(JDBC_DRIVERNAME_PROPERTY);
            try {
                Class.forName(driverName);
            } catch (ClassNotFoundException e) {
                throw new ConnectionPoolException(e);
            }
            this.url = properties.getProperty(JDBC_URL_PROPERTY);
            this.username = properties.getProperty(JDBC_USERNAME_PROPERTY);
            this.password = properties.getProperty(JDBC_PASSWORD_PROPERTY);
            this.poolSize = Integer.parseInt(properties.getProperty(JDBC_POOLSIZE_PROPERTY));
            if (poolSize < 5 || poolSize > 10) {
                log.error("Invalid pool size in the property file, should be between 5 and 10");
                throw new ConnectionPoolException();
            }
            log.info("Properties set");
        } catch (PropertyManagerException e) {
            throw new ConnectionPoolException(e);
        }
        fill();

    }

    /**
     * Method for retrieving connection from ConnectionPool
     *
     * @return - PooledConnection from ConnectionPool
     * @throws ConnectionPoolException - if
     */
    public Connection getConnection() throws ConnectionPoolException, InterruptedException {
        Connection connection = null;
        try {
            if (connections.peek() == null) {
                connections = new ArrayBlockingQueue<Connection>(poolSize * 2);
                connection = DriverManager.getConnection(url, username, password);
                for (int i = 0; i < poolSize * 2; i++) {
                    connections.offer(connection);
                }
            } else {
                connection = connections.take();
            }
            log.debug("Connection received: {}", connection);
        } catch (InterruptedException e) {
            log.debug("Connection error occurred. Thread interrupted.");
            throw new ConnectionPoolException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            throw new ConnectionPoolException("CONNECTION == NULL");
        }
        return connection;
    }

    /**
     * Method for closing the ConnectionPool
     *
     * @throws ConnectionPoolException - if CP cannot be closed at the moment
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
                throw new ConnectionPoolException();
            }
        }
        connections.clear();
    }

    public void returnConnection(Connection connection) throws ConnectionPoolException {
        try {
            if (connection.isValid(1))
            connections.offer(connection);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }


    private static class InstanceHolder {
        final static ConnectionPool instance = new ConnectionPool();
    }
}
