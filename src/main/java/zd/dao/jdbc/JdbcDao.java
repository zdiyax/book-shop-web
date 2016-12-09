package zd.dao.jdbc;

import zd.dao.Dao;
import zd.exception.JdbcDaoException;
import zd.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
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

    private Connection connection;

    public JdbcDao(Connection connection) {
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
            log.error("saving entity:{} was failed",t,e);
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
            log.error("Could not find entity with id = {}  {}", id, e);
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




    protected abstract String getDeleteQuery();
    protected abstract void setPsFields(PreparedStatement ps,T entity) throws JdbcDaoException;
    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getSelectByIdQuery(int id);
    protected abstract T createEntityFromResultSet(ResultSet rs) throws SQLException, JdbcDaoException;
    protected abstract String getSelectAllQuery();

}
