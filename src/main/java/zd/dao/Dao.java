package zd.dao;

import zd.dao.jdbc.JdbcException;

import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 9:55 PM
 *
 * Generic Dao interface
 *
 */
public interface Dao <T> {
    T insert(T t) throws JdbcException;
    List<T> getAll() throws JdbcException;
    T getById(int id) throws JdbcException;
    void update(T t) throws  JdbcException;
    void delete(T t) throws JdbcException;
    void deleteById(int id) throws JdbcException;
}
