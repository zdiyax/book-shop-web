package zd.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.dao.Dao;
import zd.exception.DaoException;
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
 * @param <T> - for all Model subclasses
 */
public abstract class JdbcDao<T extends Model> implements Dao<T> {

    private static final Logger log = LoggerFactory.getLogger(JdbcDao.class);
    private static final String QUERIES = "sql.properties";

    private Connection connection;

    JdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T insert(T t) throws JdbcDaoException {
        String query = getInsertQuery();
        PreparedStatement statement;
        if (t.getId() == null) {
            try {
                statement = connection.prepareStatement(query);
                setPsFields(statement, t);
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                t.setId(resultSet.getInt(1));
            } catch (SQLException e) {
                throw new JdbcDaoException(e);
            }
        }
        return t;
    }

    protected abstract void setPsFields(PreparedStatement statement, T t) throws JdbcDaoException;

    @Override
    public T getById(int id) throws JdbcDaoException {
        T model = null;
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectByIdQuery(id));
            ResultSet resultSet = statement.executeQuery();
            model = createEntityFromRs(resultSet);
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
        return model;
    }

    @Override
    public void delete(T t) throws JdbcDaoException {
        deleteById(t.getId());
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {
        String query = getSelectByIdQuery(id);
        T model = null;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                model = createEntityFromRs(rs);
            }
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
    }

    //TODO: REALIZOVAT'
    @Override
    public List<T> getAllByQuery(String query) throws DaoException {
        return null;
    }

    protected abstract T createEntityFromRs(ResultSet rs) throws SQLException, JdbcDaoException;

    protected abstract String getInsertQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getSelectByIdQuery(int id);

    protected abstract String getSelectAllQuery();

}
