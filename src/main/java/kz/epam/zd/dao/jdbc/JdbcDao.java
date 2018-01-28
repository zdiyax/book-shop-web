package kz.epam.zd.dao.jdbc;

import kz.epam.zd.exception.JdbcDaoException;
import kz.epam.zd.exception.NonUniqueFieldException;
import kz.epam.zd.exception.PropertyManagerException;
import kz.epam.zd.model.Model;
import kz.epam.zd.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kz.epam.zd.dao.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic JDBC DAO abstract class for all Model subclasses
 * JDBC implementation
 *
 * @param <T> - for all Model subclasses
 */
public abstract class JdbcDao<T extends Model> implements Dao<T> {

    private static final Logger log = LoggerFactory.getLogger(JdbcDao.class);
    private static final String QUERY_PROPERTY_FILE = "sql.properties";
    private static final String UNIQUE_VIOLATION_CODE = "23505";
    private String query;
    private Connection connection;

    JdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T insert(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        query = getSqlQuery(key);
        if (entity.getId() == null) {
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                setParametersToPs(parameters, ps);
                ps.execute();
                setId(entity, ps);
            } catch (SQLException e) {
                // PostgreSQL SQLState code for unique violation
                if (UNIQUE_VIOLATION_CODE.equals(e.getSQLState())) throw new NonUniqueFieldException(e);
                throw new JdbcDaoException(e);
            }
        }

        return entity;
    }

    @Override
    public T update(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        query = getSqlQuery(key);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setParametersToPs(parameters, ps);
            int result = ps.executeUpdate();
            //if ResultSet return 0 (no fields were updated) throw exception about it
            if (result == 0) {
                log.error("Couldn't update the entity: {}", entity.getClass().getSimpleName());
                throw new JdbcDaoException();
            }
            else
                log.debug("Entity : {} updated successfully", entity.getClass().getSimpleName());
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
        return entity;
    }

    @Override
    public List<T> getAllByParameters(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        query = getSqlQuery(key);
        List<T> entities = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setParametersToPs(parameters, ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                T newEntity = createEntityFromRs(rs, entity);
                entities.add(newEntity);
            }
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }

        return entities;
    }

    @Override
    public T getByParameters(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        query = getSqlQuery(key);
        T newEntity = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setParametersToPs(parameters, ps);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) newEntity = createEntityFromRs(rs, entity);
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
        return newEntity;
    }

    @Override
    public void delete(T entity, List<Object> parameters, String key) throws JdbcDaoException {
        query = getSqlQuery(key);
        try (PreparedStatement ps = connection.prepareStatement(query)){
            setParametersToPs(parameters, ps);
            ps.execute();
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
    }


    private String getSqlQuery(String key) throws JdbcDaoException {
        String sqlQuery;
        try {
            PropertyManager pm = new PropertyManager(QUERY_PROPERTY_FILE);
            sqlQuery = pm.getPropertyKey(key);
        } catch (PropertyManagerException e) {
            throw new JdbcDaoException(e);
        }
        return sqlQuery;
    }

    private void setParametersToPs(List<Object> parameters, PreparedStatement ps) throws SQLException {
        int count = 1;
        for (Object parameter : parameters) {
            ps.setObject(count, parameter);
            count++;
        }
        parameters.clear();
    }

    private void setId(T entity, PreparedStatement ps) throws SQLException {
        ResultSet generatedId = ps.getGeneratedKeys();
        generatedId.next();
        int id = generatedId.getInt(1);
        entity.setId(id);
        if (entity.getId() != null)
            log.debug("Entity inserted: {} | entity.id = ", entity.getClass().getSimpleName(), entity.getId());
    }

    abstract T createEntityFromRs(ResultSet rs, T entity) throws SQLException;
}
