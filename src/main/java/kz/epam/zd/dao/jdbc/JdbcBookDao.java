package kz.epam.zd.dao.jdbc;

import kz.epam.zd.dao.BookDao;
import kz.epam.zd.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static kz.epam.zd.util.ConstantHolder.*;

public class JdbcBookDao extends JdbcDao<Book> implements BookDao {

    JdbcBookDao(Connection connection) {
        super(connection);
    }

    @Override
    protected Book createEntityFromRs(ResultSet resultSet, Book entity) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt(INDEX_1));
        book.setIsbn(resultSet.getString(INDEX_2));
        book.setTitle(resultSet.getString(INDEX_3));
        book.setAuthor(resultSet.getString(INDEX_4));
        book.setDescription(resultSet.getString(INDEX_5));
        book.setPrice(resultSet.getInt(INDEX_6));
        return book;
    }

}
