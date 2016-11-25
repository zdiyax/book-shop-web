package dao;

import model.product.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 9:57 PM
 */
public class BookDao implements Dao<Book> {
    private static final Logger log = LoggerFactory.getLogger(BookDao.class);

    @Override
    public Book insert(Book book) {
        return null;
    }

    @Override
    public List<Book> get() {
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(int id) {

    }
}
