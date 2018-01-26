package kz.epam.zd.dao.jdbc;

import kz.epam.zd.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kz.epam.zd.dao.BookDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcBookDao extends JdbcDao<Book> implements BookDao {

    JdbcBookDao(Connection connection) {
        super(connection);
    }

    @Override
    protected Book createEntityFromRs(ResultSet resultSet, Book entity) throws SQLException {
            Book book = new Book();
            book.setId(Integer.parseInt(resultSet.getString("id")));
            book.setIsbn(resultSet.getString("isbn"));
            book.setTitle(resultSet.getString("title"));
            book.setDescription(resultSet.getString("description"));
            book.setPrice(resultSet.getBigDecimal("price"));
            return book;
    }

}
