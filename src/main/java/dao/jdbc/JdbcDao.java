package dao.jdbc;

import dao.Dao;
import model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public T insert(T t) throws JdbcException {
        String query = getInsertQuery();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            setPsFields(statement,t);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            t.setId(generatedKeys.getInt(1));
            statement.close();
        } catch (SQLException e) {
            log.error("saving entity:{} was failed",t,e);
            throw new JdbcException();
        }
        return t;
    }

    @Override
    public List<T> getAll() {
        String query = getSelectAllQuery();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public T getById(int id) throws JdbcException {
        T model = null;
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectByIdQuery(id));
            ResultSet resultSet = statement.executeQuery();
            model = createEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            log.error("Could not find entity with id = {}  {}", id, e);
            throw new JdbcException();
        }
        return model;
    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(T t) throws JdbcException {
        deleteById(t.getId());
    }

    @Override
    public void deleteById(int id) throws JdbcException {

    }




    protected abstract String getDeleteQuery();
    protected abstract void setPsFields(PreparedStatement ps,T entity) throws JdbcException;
    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getSelectByIdQuery(int id);
    protected abstract T createEntityFromResultSet(ResultSet rs) throws SQLException, JdbcException;
    protected abstract String getSelectAllQuery();

}
