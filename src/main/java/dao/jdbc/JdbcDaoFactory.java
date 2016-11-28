package dao.jdbc;

import dao.DaoFactory;

import java.sql.Connection;

/**
 * Zhannur Diyas
 * 11/26/2016 | 1:01 AM
 */
public class JdbcDaoFactory extends DaoFactory {


    @Override
    public JdbcBookDao getJdbcBookDao(Connection connection) {
        return new JdbcBookDao(connection);
    }

}

