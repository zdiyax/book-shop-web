package kz.epam.zd.dao;

import kz.epam.zd.exception.DaoException;
import kz.epam.zd.model.Model;

import java.util.List;

/**
 * Generic interface for DAO design pattern
 * @param <T> - for all Model subclasses
 */
public interface Dao <T extends Model> {
    T insert(T entity, List<Object> parameters, String key) throws DaoException;
    void update (T entity, List<Object> parameters, String key) throws DaoException;
    List<T> getAllByParameters(T entity, List<Object> parameters, String key) throws DaoException;
    T getByParameters(T entity, List<Object> parameters, String key) throws DaoException;
    void delete(T entity, List<Object> parameters, String key) throws DaoException;
}
