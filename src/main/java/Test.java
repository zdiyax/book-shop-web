import zd.exception.PropertyManagerException;
import zd.util.PropertyManager;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Zhannur Diyas
 * 1/29/2017 | 8:05 PM
 */
public class Test {

    public static void main(String[] args) {
        PropertyManager propertyManager;
        try {
            propertyManager = new PropertyManager("db.properties");
            Driver driver = DriverManager.getDriver(propertyManager.getPropertyKey("url"));
        } catch (PropertyManagerException | SQLException e) {
            e.printStackTrace();
        }


    }
}
