package zd.dao.jdbc;

import zd.cp.PooledConnection;
import zd.dao.LanguageDao;
import zd.exception.JdbcDaoException;
import zd.model.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcLanguageDao extends JdbcDao<Language> implements LanguageDao {

    JdbcLanguageDao(PooledConnection connection) {
        super(connection);
    }

    @Override
    protected void setPsFields(PreparedStatement statement, Language language) throws JdbcDaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Language createEntityFromRs(ResultSet rs) throws SQLException, JdbcDaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getInsertQuery() {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getUpdateQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getSelectByIdQuery(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getSelectAllQuery() {
        throw new UnsupportedOperationException();
    }

    protected String getSelectByNameQuery(String name) {
        return "SELECT * FROM language WHERE name = " + name + ";";
    }

    @Override
    public List<Language> getAllByParam(String query) throws JdbcDaoException {
        return null;
    }
}
