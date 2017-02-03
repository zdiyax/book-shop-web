package zd.util;

import zd.exception.PropertyManagerException;

/**
 * Zhannur Diyas
 * 2/3/2017 | 1:34 AM
 */
public class PropertyManagerTest {

    public static void test() throws PropertyManagerException {
        PropertyManager pm = new PropertyManager("sql.properties");
        System.out.println(pm.getPropertyKey("get.user.by.username"));
    }

    public static void main(String[] args) throws PropertyManagerException {
        test();
    }
}