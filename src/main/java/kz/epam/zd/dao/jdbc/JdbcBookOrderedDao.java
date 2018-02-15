package kz.epam.zd.dao.jdbc;

import kz.epam.zd.dao.BookOrderedDao;
import kz.epam.zd.model.BookOrdered;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * JDBC DAO for BookOrdered entity.
 */
class JdbcBookOrderedDao extends JdbcDao<BookOrdered> implements BookOrderedDao {

    JdbcBookOrderedDao(Connection connection) {
        super(connection);
    }

    @Override
    BookOrdered createEntityFromRs(ResultSet rs, BookOrdered entity) throws SQLException {
        BookOrdered bookOrdered = new BookOrdered();
        bookOrdered.setId(rs.getInt(INDEX_1));
        bookOrdered.setOrderId(rs.getInt(INDEX_2));
        bookOrdered.setBookId(rs.getInt(INDEX_3));
        bookOrdered.setQuantity(rs.getInt(INDEX_4));
        return bookOrdered;
    }
}
