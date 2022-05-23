package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class PasswordUtils {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int KEY_LENGTH = 512;
    public static final int length = 50;
    public static final int ITERATIONS = 1000;
    private static final SecureRandom RAND = new SecureRandom();

    public static String generateSalt() {
        byte[] salt = new byte[length];
        RAND.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String generateHashedPassword(String password, String salt) {
        char[] rawPassword = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(rawPassword, bytes, ITERATIONS, KEY_LENGTH);
        Arrays.fill(rawPassword, Character.MIN_VALUE);
        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(securePassword);
        } catch (Exception ex) {
            return "";
        } finally {
            spec.clearPassword();
        }
    }

    public static boolean checkPassword(String inputPassword, String hashedPassword, String salt) {
       String encryptedPassword = generateHashedPassword(inputPassword, salt);
        return encryptedPassword.equals(hashedPassword);
    }

}
