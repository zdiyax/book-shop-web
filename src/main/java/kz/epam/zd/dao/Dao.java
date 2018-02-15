package kz.epam.zd.dao;

import kz.epam.zd.exception.DaoException;
import kz.epam.zd.model.Entity;

import java.util.List;

/**
 * Generic interface for DAO implementation
 * @param <T> all Entity subclasses
 */
public interface Dao <T extends Entity> {
    T insert(T entity, List<Object> parameters, String key) throws DaoException;
    T update (T entity, List<Object> parameters, String key) throws DaoException;
    List<T> getAllByParameters(T entity, List<Object> parameters, String key) throws DaoException;
    T getByParameters(T entity, List<Object> parameters, String key) throws DaoException;
    void delete(T entity, List<Object> parameters, String key) throws DaoException;
}
