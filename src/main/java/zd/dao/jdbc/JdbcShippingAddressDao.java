package zd.dao.jdbc;

import zd.dao.ShippingAddressDao;
import zd.model.user.ShippingAddress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Zhannur Diyas
 * 2/1/2017 | 7:10 AM
 */
public class JdbcShippingAddressDao extends JdbcDao<ShippingAddress> implements ShippingAddressDao {

    JdbcShippingAddressDao(Connection connection) {
        super(connection);
    }

    @Override
    ShippingAddress createEntityFromRs(ResultSet rs) throws SQLException {
        return null;
    }
}
