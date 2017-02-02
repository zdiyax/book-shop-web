package zd.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.dao.BookDao;
import zd.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcBookDao extends JdbcDao<Book> implements BookDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcBookDao.class);

    JdbcBookDao(Connection connection) {
        super(connection);
    }

    @Override
    protected Book createEntityFromRs(ResultSet resultSet, Book entity) throws SQLException {
            Book book = new Book();
            book.setId(Integer.parseInt(resultSet.getString("id")));
            book.setIsbn(resultSet.getString("isbn"));
            book.setLanguage(resultSet.getString("language"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setDomain(resultSet.getString("domain"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setDescription(resultSet.getString("description"));
            book.setPrice(resultSet.getBigDecimal("price"));
            return book;
    }

}
