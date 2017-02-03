package zd.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Zhannur Diyas
 * 2/3/2017 | 12:18 AM
 */
public class PasswordHelperTest {
    private static final String EXPECTING_VALUE = "e10adc3949ba59abbe56e057f20f883e";
    private static final String VALUE_TO_HASH = "123456";

    @Test
    public void test() throws Exception {
        System.out.println(PasswordHelper.hash("password"));
        Assert.assertEquals(PasswordHelper.hash(VALUE_TO_HASH), EXPECTING_VALUE);
    }

}