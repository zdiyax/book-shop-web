package kz.epam.zd.dao.jdbc;

import kz.epam.zd.dao.OrderedBookDetailsDao;
import kz.epam.zd.model.OrderedBookDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * JDBC DAO for OrderedBookDetails entity.
 */
class JdbcOrderedBookDetailsDao extends JdbcDao<OrderedBookDetails> implements OrderedBookDetailsDao {
    JdbcOrderedBookDetailsDao(Connection connection) {
        super(connection);
    }

    @Override
    OrderedBookDetails createEntityFromRs(ResultSet rs, OrderedBookDetails entity) throws SQLException {
        OrderedBookDetails orderedBookDetails = new OrderedBookDetails();
        orderedBookDetails.setId(rs.getInt(INDEX_1));
        orderedBookDetails.setBookTitle(rs.getString(INDEX_2));
        orderedBookDetails.setBookAuthor(rs.getString(INDEX_3));
        orderedBookDetails.setQuantity(rs.getInt(INDEX_4));
        orderedBookDetails.setOrderPrice(rs.getInt(INDEX_5));
        return orderedBookDetails;
    }
}
