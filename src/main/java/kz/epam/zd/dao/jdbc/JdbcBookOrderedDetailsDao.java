package kz.epam.zd.dao.jdbc;

import kz.epam.zd.dao.BookOrderedDetailsDao;
import kz.epam.zd.model.BookOrderedDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * JDBC DAO for BookOrderedDetails entity.
 */
class JdbcBookOrderedDetailsDao extends JdbcDao<BookOrderedDetails> implements BookOrderedDetailsDao {
    JdbcBookOrderedDetailsDao(Connection connection) {
        super(connection);
    }

    @Override
    BookOrderedDetails createEntityFromRs(ResultSet rs, BookOrderedDetails entity) throws SQLException {
        BookOrderedDetails bookOrderedDetails = new BookOrderedDetails();
        bookOrderedDetails.setId(rs.getInt(INDEX_1));
        bookOrderedDetails.setBookTitle(rs.getString(INDEX_2));
        bookOrderedDetails.setBookAuthor(rs.getString(INDEX_3));
        bookOrderedDetails.setQuantity(rs.getInt(INDEX_4));
        bookOrderedDetails.setOrderPrice(rs.getInt(INDEX_5));
        return bookOrderedDetails;
    }
}
