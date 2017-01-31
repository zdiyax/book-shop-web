package zd.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.cp.ConnectionPool;
import zd.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(ConnectionPoolListener.class);
    private static ConnectionPool pool;

    static {
        log.debug("Static initializer called");
    }
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        pool = new ConnectionPool();

        try {
            pool.fill();
        } catch (ConnectionPoolException e) {
            try {
                pool.close();
            } catch (ConnectionPoolException e1) {
                log.error("Listener failed to start: " + e1);
            }
            log.error("Initializing a CP failed, error in fill() method." + e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        try {
            DriverManager.deregisterDriver(drivers.nextElement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pool.close();
        } catch (ConnectionPoolException e) {
            log.error("Couldn't close connection pool", e);
        }
    }

    public static ConnectionPool getPool() {
        return pool;
    }
}
