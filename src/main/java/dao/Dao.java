package dao;

import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 9:55 PM
 *
 * Generic Dao interface
 *
 */
public interface Dao <T> {
    T insert(T t);
    List<T> get();
    T getById(int id);
    void update(T t);
    void delete(int id);
}
