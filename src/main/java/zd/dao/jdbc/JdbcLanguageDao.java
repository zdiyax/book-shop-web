package zd.dao.jdbc;

import zd.dao.LanguageDao;
import zd.model.Language;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static zd.util.ConstantHolder.INDEX_1;
import static zd.util.ConstantHolder.INDEX_2;

public class JdbcLanguageDao extends JdbcDao<Language> implements LanguageDao {

    JdbcLanguageDao(Connection connection) {
        super(connection);
    }

    @Override
    Language createEntityFromRs(ResultSet rs, Language entity) throws SQLException {
        Language language = new Language();
        language.setId(rs.getInt(INDEX_1));
        language.setName(rs.getString(INDEX_2));
        return language;
    }

}
