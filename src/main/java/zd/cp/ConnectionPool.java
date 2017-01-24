package zd.cp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.exception.ConnectionPoolException;
import zd.exception.PropertyManagerException;
import zd.util.PropertyManager;

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
    private static ConnectionPool instance;
    private static BlockingQueue<PooledConnection> connections;
    private String driverName;
    private String url;
    private String username;
    private String password;
    private int poolSize;

    public ConnectionPool() {
        try {
            loadProperties();
        } catch (ConnectionPoolException e) {
            log.error(" ", e);
        }
        connections = new ArrayBlockingQueue<PooledConnection>(poolSize);
        fill();
    }

    /**
     * Method fills the connection pool with connections using DriverManager and property file
     */
    private void fill() {
        try {
            PooledConnection connection = (PooledConnection) DriverManager.getConnection(url, username, password);
            for (int i = 0; i < poolSize; i++) {
                connections.add(connection);
            }
        } catch (SQLException e) {
            log.error("Couldn't load connections in connection pool " + e);
        }
    }

    /**
     * Method loads properties from property file using PropertyManager
     * @throws ConnectionPoolException - if properties failed to load in
     */
    private void loadProperties() throws ConnectionPoolException {
        Properties properties = new Properties();
        try {
            PropertyManager propertyManager = new PropertyManager("db.properties");
            properties = propertyManager.getProperties();
        } catch (PropertyManagerException e) {
            throw new ConnectionPoolException(e);
        }
        log.debug("Properties value: {}", properties);
        this.driverName = properties.getProperty(JDBC_DRIVERNAME_PROPERTY);
        this.url = properties.getProperty(JDBC_URL_PROPERTY);
        this.username = properties.getProperty(JDBC_USERNAME_PROPERTY);
        this.password = properties.getProperty(JDBC_PASSWORD_PROPERTY);
        this.poolSize = Integer.parseInt(properties.getProperty("jdbc.poolsize"));
        if (poolSize < 5 || poolSize > 10) {
            log.error("Invalid pool size in the property file, should be between 5 and 10");
            throw new ConnectionPoolException();
        }
        log.info("Properties set");
    }

    /**
     * Method for retrieving connection from ConnectionPool
     * @return - PooledConnection from ConnectionPool
     * @throws ConnectionPoolException - if
     */
    public Connection getConnection() throws ConnectionPoolException {
        PooledConnection connection = null;
        try {
            if (connections.peek() == null) {
                connections = new ArrayBlockingQueue<PooledConnection>(poolSize*2);
                connection = (PooledConnection) DriverManager.getConnection(url, username, password);
                for (int i = 0; i < poolSize*2; i++) {
                    connections.add(connection);
                }
            }
            connection = connections.take();
        } catch (InterruptedException e) {
            log.debug("Connection error occurred. Thread interrupted.");
            throw new ConnectionPoolException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ConnectionPool getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * Method for closing the ConnectionPool
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

    static BlockingQueue<PooledConnection> getConnections() {
        return connections;
    }

    void returnConnection(PooledConnection connection) {
        connections.add(connection);
    }
    private static class InstanceHolder {
        /*
        TODO: Explain why this way of creating a singleton is better than just "private ConnectionPool cp;"
         */
        final static ConnectionPool instance = new ConnectionPool();
    }
}
