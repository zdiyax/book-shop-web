package dao;

import model.product.Book;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:20 PM
 */
public interface BookDao {
    public void insert(Book book) throws DaoException;

    public Book get(Book book) throws DaoException;

    public Book update(Book book) throws DaoException;

    public void delete(Book book) throws DaoException;
}
