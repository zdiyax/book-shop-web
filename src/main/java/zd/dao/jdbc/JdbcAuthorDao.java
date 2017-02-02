package zd.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.dao.AuthorDao;
import zd.model.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static zd.util.ConstantHolder.INDEX_1;
import static zd.util.ConstantHolder.INDEX_2;

public class JdbcAuthorDao extends JdbcDao<Author> implements AuthorDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcAuthorDao.class);

    JdbcAuthorDao(Connection connection) {
        super(connection);
    }

    @Override
    Author createEntityFromRs(ResultSet rs, Author entity) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt(INDEX_1));
        author.setName(rs.getString(INDEX_2));
        return author;
    }

}
