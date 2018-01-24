package kz.epam.zd.dao.jdbc;

import kz.epam.zd.model.user.User;
import kz.epam.zd.util.ConstantHolder;
import kz.epam.zd.dao.UserDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao extends JdbcDao<User> implements UserDao {

    JdbcUserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected User createEntityFromRs(ResultSet resultSet, User entity) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ConstantHolder.INDEX_1));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

}
