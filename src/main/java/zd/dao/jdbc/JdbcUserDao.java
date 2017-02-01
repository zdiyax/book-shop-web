package zd.dao.jdbc;

import zd.dao.UserDao;
import zd.model.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static zd.util.ConstantHolder.INDEX_1;

public class JdbcUserDao extends JdbcDao<User> implements UserDao {

    JdbcUserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected User createEntityFromRs(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(INDEX_1));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

}
