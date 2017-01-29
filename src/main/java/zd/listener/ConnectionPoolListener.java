package zd.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.cp.ConnectionPool;
import zd.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(ConnectionPoolListener.class);
    private ConnectionPool pool;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        pool = new ConnectionPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            pool.close();
        } catch (ConnectionPoolException e) {
            log.error("Couldn't close connection pool", e);
        }
    }
}
