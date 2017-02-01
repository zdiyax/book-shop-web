package zd.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.dao.Dao;
import zd.exception.JdbcDaoException;
import zd.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Generic JDBC DAO abstract class for all Model subclasses
 * JDBC implementation
 *
 * @param <T> - for all Model subclasses
 */
public abstract class JdbcDao<T extends Model> implements Dao<T> {

    private static final Logger log = LoggerFactory.getLogger(JdbcDao.class);
    private static final String QUERY_PROPERTY_FILE = "query.properties";
    private String query;
    private Connection connection;

    JdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T insert(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        return null;
    }

    @Override
    public T update(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        return null;
    }

    @Override
    public List<T> getAllByParameters(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        return null;
    }

    @Override
    public T getByParameters(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        return null;
    }

    @Override
    public void delete(T entity, String key) throws JdbcDaoException {

    }

    private void setParametersToPs(List<Object> parameters, PreparedStatement ps) throws SQLException {
        int count = 1;
        for (Object parameter : parameters) {
            ps.setObject(count, parameter);
            count++;
        }
        parameters.clear();
    }

    void setId(T entity, PreparedStatement ps) throws SQLException {
        ResultSet generatedId = ps.getGeneratedKeys();
        generatedId.next();
        int id = generatedId.getInt(1);
        entity.setId(id);
    }

    abstract T createEntityFromRs(ResultSet rs) throws SQLException;
}
