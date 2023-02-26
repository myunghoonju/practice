package practice.others.secret;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.util.Base64;

public class TokenGenerator {

    public static final String SHA_512 = "SHA-512";

    private static final String TIMESTAMP = String.valueOf(System.currentTimeMillis());
    private static final String NONCE = "foodtech";
    private static final String ACCESSKEY = "f9ecb7d7-f5e5-40e1-bc9b-6b5e4ed6cfe0";

    public static void main(String[] args) {
        String token = getToken();
        System.out.println("token:: " + token);
        byte[] decode = Base64.getDecoder().decode(token);
        String s = new String(decode);
        System.out.println(s);
    }

    public static String encrypt() {
        String input = TIMESTAMP + NONCE + ACCESSKEY;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_512);
            md.update(input.getBytes());

            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException | ProviderException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getToken() {
        String sign = encrypt();
        String authData = TIMESTAMP + "$$" + NONCE + "$$" + sign;
        return Base64.getEncoder().encodeToString(authData.getBytes());
    }
}
