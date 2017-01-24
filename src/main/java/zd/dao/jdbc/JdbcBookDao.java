package zd.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.cp.PooledConnection;
import zd.dao.BookDao;
import zd.exception.JdbcDaoException;
import zd.model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcBookDao extends JdbcDao<Book> implements BookDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcBookDao.class);

    private PooledConnection connection;

    JdbcBookDao(PooledConnection connection) {
        super(connection);
    }

    @Override
    protected Book createEntityFromRs(ResultSet resultSet) throws SQLException, JdbcDaoException {

        try {
            Book book = new Book();
            log.debug("Book is starting to assemble: {}", book);
            book.setIsbn(resultSet.getString("isbn"));
            book.setLanguage(resultSet.getString("language"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setDomain(resultSet.getString("domain"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setDescription(resultSet.getString("description"));
            book.setPrice(resultSet.getBigDecimal("price"));
            return book;
        } catch (SQLException e) {
            log.error("Failed to create an entity from result set");
            throw new JdbcDaoException();
        }
    }

    @Override
    protected void setPsFields(PreparedStatement ps, Book entity) throws JdbcDaoException {

    }

    @Override
    protected String getInsertQuery() {
        return null;
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    /*
    TODO: Change SQL Dialect from MySQL to PostgreSQL
     */
    @Override
    protected String getSelectByIdQuery(int id) {
        return "select * from books where (id = " + id + ");";
    }

    @Override
    protected String getSelectAllQuery() {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

}
