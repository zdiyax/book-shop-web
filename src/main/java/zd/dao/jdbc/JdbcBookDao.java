package zd.dao.jdbc;

import zd.dao.BookDao;
import zd.exception.JdbcDaoException;
import zd.model.ModelException;
import zd.model.product.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Zhannur Diyas
 * 11/26/2016 | 10:33 AM
 */
public class JdbcBookDao extends JdbcDao<Book> implements BookDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcBookDao.class);

    private Connection connection;

    public JdbcBookDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getDeleteQuery() {
        return null;
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

    @Override
    protected String getSelectByIdQuery(int id) {
        return "select * from books where (id = " + id + ");";
    }

    @Override
    protected Book createEntityFromResultSet(ResultSet resultSet) throws SQLException, JdbcDaoException {

        try {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setIsbn(resultSet.getString("isbn"));
            log.debug("id equals to {}", book.getId());
            book.setLanguage(resultSet.getString("language"));
            book.setTitle(resultSet.getString("title"));
            book.setPrice(Integer.parseInt(resultSet.getString("author")));
            book.setDescription(resultSet.getString("description"));
            book.setAuthor("adsd");
            book.setPublisher("sadsd");
            System.out.println(book);
            return book;
        } catch (SQLException e) {
            log.error("Failed to create an entity from result set");
            throw new JdbcDaoException();
        } catch (ModelException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getSelectAllQuery() {
        return null;
    }
}
