package kz.epam.zd.dao.jdbc;

import kz.epam.zd.dao.UserDao;
import kz.epam.zd.model.user.Locale;
import kz.epam.zd.model.user.RoleType;
import kz.epam.zd.model.user.User;
import kz.epam.zd.model.user.UserRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static kz.epam.zd.util.ConstantHolder.*;

public class JdbcUserDao extends JdbcDao<User> implements UserDao {

    JdbcUserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected User createEntityFromRs(ResultSet resultSet, User entity) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(INDEX_1));
        user.setUsername(resultSet.getString(INDEX_2));
        user.setPassword(resultSet.getString(INDEX_3));
        user.setUserRole(new UserRole(RoleType.valueOf(resultSet.getString(INDEX_4))));
        user.setLocale(new Locale(resultSet.getString(INDEX_5)));
        user.setFullName(resultSet.getString(INDEX_6));
        user.setEmail(resultSet.getString(INDEX_7));
        user.setAddress(resultSet.getString(INDEX_8));
        user.setTelephoneNumber(resultSet.getString(INDEX_9));
        return user;
    }

}
