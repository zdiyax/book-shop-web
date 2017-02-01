package zd.dao.jdbc;

import zd.dao.UserInfoDao;
import zd.model.user.UserInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Zhannur Diyas
 * 2/1/2017 | 7:11 AM
 */
public class JdbcUserInfoDao extends JdbcDao<UserInfo> implements UserInfoDao {

    JdbcUserInfoDao(Connection connection) {
        super(connection);
    }

    @Override
    UserInfo createEntityFromRs(ResultSet rs) throws SQLException {
        return null;
    }
}
