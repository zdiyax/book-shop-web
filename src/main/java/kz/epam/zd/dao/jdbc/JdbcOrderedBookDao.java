package kz.epam.zd.dao.jdbc;

import kz.epam.zd.dao.OrderedBookDao;
import kz.epam.zd.model.OrderedBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * JDBC DAO for OrderedBook entity.
 */
class JdbcOrderedBookDao extends JdbcDao<OrderedBook> implements OrderedBookDao {

    JdbcOrderedBookDao(Connection connection) {
        super(connection);
    }

    @Override
    OrderedBook createEntityFromRs(ResultSet rs, OrderedBook entity) throws SQLException {
        OrderedBook orderedBook = new OrderedBook();
        orderedBook.setId(rs.getInt(INDEX_1));
        orderedBook.setOrderId(rs.getInt(INDEX_2));
        orderedBook.setBookId(rs.getInt(INDEX_3));
        orderedBook.setQuantity(rs.getInt(INDEX_4));
        return orderedBook;
    }
}
