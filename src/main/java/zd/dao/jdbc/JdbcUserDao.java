package zd.dao.jdbc;

import zd.dao.UserDao;
import zd.exception.JdbcDaoException;
import zd.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcUserDao extends JdbcDao<User> implements UserDao {

    JdbcUserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected void setPsFields(PreparedStatement statement, User user) throws JdbcDaoException {

    }

    @Override
    protected User createEntityFromRs(ResultSet resultSet) throws SQLException, JdbcDaoException {
        try {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new JdbcDaoException(e);
        }
    }

    @Override
    protected String getInsertQuery() {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected String getSelectByIdQuery(int id) {
        return "SELECT * FROM newschema.\"user\" WHERE (\"userid\" = " + id + ");";
    }

    @Override
    protected String getSelectAllQuery() {
        return null;
    }

    @Override
    public List<User> getAllByQuery(String query) throws JdbcDaoException {
        return null;
    }
}
