package kz.epam.zd.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class PasswordHelper {
    private static final String MD_5_ALGORITHM = "MD5";

    public static String hash(String password) throws PasswordHashAlgorithmException {
        MessageDigest md5;
        byte[] byteData;
        try{
            md5 = MessageDigest.getInstance(MD_5_ALGORITHM);
            md5.reset();
            md5.update(password.getBytes());
            byteData = md5.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordHashAlgorithmException("Requested password hashing algorithm is not available",  e);
        }
        StringBuilder sb = new StringBuilder();
        // Hash algorithm example from Mkyong
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    static public class PasswordHashAlgorithmException extends Exception {
        PasswordHashAlgorithmException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static boolean verifyPassword(String passwordToVerify, String passFromDB) throws PasswordHashAlgorithmException {
        return Objects.equals(hash(passwordToVerify), passFromDB);
    }
}
