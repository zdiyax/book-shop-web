package zd.dao.jdbc;

import zd.dao.AuthorDao;
import zd.exception.JdbcDaoException;
import zd.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcAuthorDao extends JdbcDao<Author> implements AuthorDao {
    JdbcAuthorDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Author> getAllByQuery(String query) throws JdbcDaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setPsFields(PreparedStatement statement, Author author) throws JdbcDaoException {

    }

    @Override
    protected Author createEntityFromRs(ResultSet rs) throws SQLException, JdbcDaoException {
        return null;
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
        return null;
    }

    @Override
    protected String getSelectAllQuery() {
        return null;
    }
}
