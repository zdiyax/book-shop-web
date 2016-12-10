package zd.cp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Zhannur Diyas
 * 11/25/2016 | 10:03 PM
 */

public class ConnectionPool {
    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private static final String PROPERTY_FILE_NAME = "db.properties";

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
        try {
            PooledConnection connection = (PooledConnection) DriverManager.getConnection(url, username, password);
            for (int i = 0; i < poolSize; i++) {
                connections.add(connection);
            }
        } catch (SQLException e) {
            log.error("Couldn't load connections in connection pool " + e);
        }
    }

    private void loadProperties() throws ConnectionPoolException {
        Properties properties = new Properties();
        InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
        try {
            properties.load(inputStream);
            log.debug("Properties successfully loaded");
        } catch (IOException e) {
            log.error("Failed to load the property file" + e.getMessage());
            e.printStackTrace();
        }
        log.debug("Properties value: {}", properties);
        this.driverName = properties.getProperty("jdbc.driver");
        this.url = properties.getProperty("jdbc.url");
        this.username = properties.getProperty("jdbc.username");
        this.password = properties.getProperty("jdbc.password");
        //TODO: invalid pool size exception handling
        this.poolSize = Integer.parseInt(properties.getProperty("jdbc.poolsize"));
        if (poolSize < 5 || poolSize > 10) {
            log.error("Invalid pool size, should be between 5 and 10");
            throw new ConnectionPoolException();
        }
        log.info("Properties set");
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            if (connections.peek() == null) {
                connections = new ArrayBlockingQueue<PooledConnection>(poolSize*2);
                connection = DriverManager.getConnection(url, username, password);
                for (int i = 0; i < poolSize*2; i++) {
                    connections.add((PooledConnection) connection);
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
        final static ConnectionPool instance = new ConnectionPool();
    }
}
