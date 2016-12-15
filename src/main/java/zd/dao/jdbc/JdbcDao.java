package zd.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.cp.PooledConnection;
import zd.dao.Dao;
import zd.exception.JdbcDaoException;
import zd.exception.PropertyManagerException;
import zd.model.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Zhannur Diyas
 * 11/27/2016 | 1:16 PM
 */
public abstract class JdbcDao<T extends Model> implements Dao<T> {

    private static final Logger log = LoggerFactory.getLogger(JdbcDao.class);
    private static final String QUERIES = "sql.properties";

    private PooledConnection connection;

    public JdbcDao(PooledConnection connection) {
        this.connection = connection;
    }


    @Override
    public T insert(T t) throws JdbcDaoException {
        String query = getInsertQuery();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            setPsFields(statement,t);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            t.setId(resultSet.getInt(1));
            statement.close();
        } catch (SQLException e) {
            log.error(" ");
            throw new JdbcDaoException();
        }
        return t;
    }

    @Override
    public List<T> getAll() {
        String query = getSelectAllQuery();
        PreparedStatement statement;
        List<T> ts = new ArrayList<T>();
        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                /// ???
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public T getById(int id) throws JdbcDaoException {
        T model = null;
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectByIdQuery(id));
            ResultSet resultSet = statement.executeQuery();
            model = createEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            log.error("");
            throw new JdbcDaoException();
        }
        return model;
    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(T t) throws JdbcDaoException {
        deleteById(t.getId());
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {

    }




    protected abstract T createEntityFromResultSet(ResultSet rs) throws SQLException, JdbcDaoException;
    protected abstract

    protected abstract String getInsertQuery() throws PropertyManagerException;
    protected abstract String getDeleteQuery() throws PropertyManagerException;
    protected abstract String getUpdateQuery() throws PropertyManagerException;

}
