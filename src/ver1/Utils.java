package ver1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Utils {
    public static byte[] sha1(byte[] bytes) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bytes);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sha512(byte[] salt, byte[] bytes) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        md.update(salt);
        byte[] hash = md.digest(bytes);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < hash.length; i++) {
            sb.append(String.format("%02x", hash[i]));
        }
        return sb.toString();
    }
}
