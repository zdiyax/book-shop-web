package dao.jdbc;

import dao.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Zhannur Diyas
 * 11/27/2016 | 1:16 PM
 */
public class JdbcDao<T> implements Dao<T> {
    @Override
    public T insert(T t) {
        String query;

        if (entity.getId() == null) {
            query = getInsertQuery();
        }else
            query = getUpdateQuery();

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            setPsFields(ps,entity);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt(1));
            ps.close();
        } catch (SQLException e) {
            log.error("saving entity:{} was failed",entity,e);
            throw new JdbcException(e);
        }
        return entity;
    }

    @Override
    public List<T> get() {
        return null;
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(int id) {

    }
}
