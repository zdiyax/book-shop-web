package zd.dao;

import zd.exception.JdbcDaoException;
import zd.model.Model;

import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 9:55 PM
 *
 * Generic Dao interface
 *
 */
public interface Dao <T extends Model> {
    T insert(T t) throws JdbcDaoException;
    List<T> getAll() throws JdbcDaoException;
    T getById(int id) throws JdbcDaoException;
    void update(T t) throws JdbcDaoException;
    void delete(T t) throws JdbcDaoException;
    void deleteById(int id) throws JdbcDaoException;
}
