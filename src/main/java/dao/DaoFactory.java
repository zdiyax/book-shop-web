package dao;

/**
 * Zhannur Diyas
 * 11/26/2016 | 12:56 AM
 */
public class DaoFactory {
    public static DaoFactory getFactory() {
        return JdbcDaoFactory;
    }
}
