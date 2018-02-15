package kz.epam.zd.dao.jdbc;

import kz.epam.zd.dao.Dao;
import kz.epam.zd.exception.JdbcDaoException;
import kz.epam.zd.exception.NonUniqueFieldException;
import kz.epam.zd.exception.PropertyManagerException;
import kz.epam.zd.model.Entity;
import kz.epam.zd.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.INDEX_1;

/**
 * Generic JDBC DAO abstract class for all Entity subclasses
 * JDBC implementation
 *
 * @param <T> all Entity.class subclasses
 */
public abstract class JdbcDao<T extends Entity> implements Dao<T> {

    private static final Logger log = LoggerFactory.getLogger(JdbcDao.class);
    private static final String QUERY_PROPERTY_FILE = "sql.properties";
    private static final String UNIQUE_VIOLATION_CODE = "23505";
    private String query;
    private Connection connection;

    JdbcDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts entity to database.
     *
     * @param entity     entity to be inserted
     * @param parameters list of parameters for prepared statement
     * @param queryKey   query key to search for in property file
     * @return inserted entity with id field set up
     * @throws JdbcDaoException if an entry with same unique fields is already present in database
     * or if there is a syntax error in query
     */
    @Override
    public T insert(T entity, List<Object> parameters, String queryKey) throws JdbcDaoException {
        query = getSqlQuery(queryKey);
        if (entity.getId() == null) {
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                setParametersToPs(parameters, ps);
                ps.execute();
                setEntityId(entity, ps);
            } catch (SQLException e) {
                // PostgreSQL SQLState code for unique violation
                if (UNIQUE_VIOLATION_CODE.equals(e.getSQLState())) throw new NonUniqueFieldException(e);
                throw new JdbcDaoException(e);
            }
        }
        return entity;
    }

    /**
     * Updates an existing entity in database.
     *
     * @param entity     entity to be updated
     * @param parameters list of parameters for prepared statement
     * @param queryKey   query key to search for in property file
     * @return updated entity
     * @throws JdbcDaoException if no field of entity was updated or if there is a syntax error in query
     */
    @Override
    public T update(T entity, List<Object> parameters, String queryKey) throws JdbcDaoException {
        query = getSqlQuery(queryKey);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setParametersToPs(parameters, ps);
            int result = ps.executeUpdate();
            //if ResultSet returns 0 (no fields were updated) throw notification about it
            if (result == 0) {
                log.error("Couldn't update the entity: {}", entity.getClass().getSimpleName());
                throw new JdbcDaoException();
            } else
                log.debug("Entity : {} updated successfully", entity.getClass().getSimpleName());
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
        return entity;
    }

    /**
     * Gets all the entities that matches given parameters.
     *
     * @param entity     entity to be searched for
     * @param parameters list of parameters for prepared statement
     * @param queryKey   query key to search for in property file
     * @return list of found entities
     * @throws JdbcDaoException if there is a syntax error in query or if database access error occurs
     */
    @Override
    public List<T> getAllByParameters(T entity, List<Object> parameters, String queryKey) throws JdbcDaoException {
        query = getSqlQuery(queryKey);
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

    /**
     * Gets a single entity that matches given parameters.
     *
     * @param entity entity to be searched for
     * @param parameters list of parameters for prepared statement
     * @param queryKey query key to search for in property file
     * @return found entity
     * @throws JdbcDaoException if there is a syntax error in query or if database access error occurs
     */
    @Override
    public T getByParameters(T entity, List<Object> parameters, String queryKey) throws JdbcDaoException {
        query = getSqlQuery(queryKey);
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

    /**
     * Deletes an entry from the database.
     *
     * @param entity entity to be deleted
     * @param parameters list of parameters for prepared statement
     * @param queryKey query key to search for in property file
     * @throws JdbcDaoException if there is a syntax error in query or if database access error occurs
     */
    @Override
    public void delete(T entity, List<Object> parameters, String queryKey) throws JdbcDaoException {
        query = getSqlQuery(queryKey);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setParametersToPs(parameters, ps);
            ps.execute();
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
    }

    /**
     * Looks for the corresponding sql query by the query key in property file.
     *
     * @param queryKey query key to search for in property file
     * @return sql query from the property file
     * @throws JdbcDaoException if there is a syntax error in query or if database access error occurs
     */
    private String getSqlQuery(String queryKey) throws JdbcDaoException {
        String sqlQuery;
        try {
            PropertyManager pm = new PropertyManager(QUERY_PROPERTY_FILE);
            sqlQuery = pm.getPropertyKey(queryKey);
        } catch (PropertyManagerException e) {
            throw new JdbcDaoException(e);
        }
        return sqlQuery;
    }

    /**
     * Sets parameters to PreparedStatement.
     * @param parameters list of parameters
     * @param ps PreparedStatement instance
     * @throws SQLException if parameters cannot be set to PreparedStatement
     */
    private void setParametersToPs(List<Object> parameters, PreparedStatement ps) throws SQLException {
        int count = 1;
        for (Object parameter : parameters) {
            ps.setObject(count, parameter);
            count++;
        }
        parameters.clear();
    }

    /**
     * Sets id field generated by the database to the entity.
     * @param entity entity
     * @param ps PreparedStatement instance
     * @throws SQLException if database access problem occurs
     */
    private void setEntityId(T entity, PreparedStatement ps) throws SQLException {
        ResultSet generatedId = ps.getGeneratedKeys();
        generatedId.next();
        int id = generatedId.getInt(INDEX_1);
        entity.setId(id);
        if (entity.getId() != null)
            log.debug("Entity inserted: {} | entity.id: {}", entity.getClass().getSimpleName(), id);
    }

    /**
     * Creates an entity from result set.
     * @param rs ResultSet
     * @param entity entity
     * @return entity with fields set up
     * @throws SQLException if problem with ResultSet occurs
     */
    abstract T createEntityFromRs(ResultSet rs, T entity) throws SQLException;
}
