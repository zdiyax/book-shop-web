package zd.dao;

import zd.exception.DaoException;
import zd.exception.JdbcDaoException;
import zd.model.Model;

import java.util.List;

/**
 * Generic interface for DAO design pattern
 * @param <T> - for all Model subclasses
 */
public interface Dao <T extends Model> {
    T insert(T t) throws DaoException;
    T getById(int id) throws JdbcDaoException;
    List<T> getAllByParam(String query) throws DaoException;
    void delete(T t) throws DaoException;
    void deleteById(int id) throws DaoException;
}
