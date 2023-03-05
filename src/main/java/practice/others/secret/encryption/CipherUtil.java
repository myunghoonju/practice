package practice.others.secret.encryption;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CipherUtil {

    private static final String KEY = "unicrypt";//8byte
    private static final String AESKEY = "unicryptunicryptunicryptunicrypt";//32byte
    private static final String CHARSET_NAME = "UTF-8";
    public static final Charset CHARSET_UTF_8 = Charset.forName(CHARSET_NAME);
    private static String CIPHER = "DES/CBC/PKCS5Padding";
    private static String SECRETKEY_INSTANCE = "DES";
    private static byte[] ivBytes = {0, 0, 0, 0, 0, 0, 0, 0};
    private static byte[] aesIvBytes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static String aesEncrypt(String str) throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        byte[] keyBytes = AESKEY.getBytes(CHARSET_UTF_8);
        byte[] content = str.getBytes(CHARSET_UTF_8);

        SecretKeySpec secKey = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(aesIvBytes);
        System.out.println("aesEncrypt algorithm " + secKey.getAlgorithm());
        //NoSuchPaddingException, NoSuchAlgorithmException,
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //InvalidAlgorithmParameterException, InvalidKeyException
        c.init(Cipher.ENCRYPT_MODE, secKey, iv);
        //IllegalBlockSizeException, BadPaddingException
        byte[] bytes = c.doFinal(content);

        return Hex.encodeHexString(bytes, false);

    }

    public static String aesDecrypt(String str) throws UnsupportedEncodingException,
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            IllegalBlockSizeException,
            BadPaddingException {
        //UnsupportedEncodingException
        byte[] key = AESKEY.getBytes(CHARSET_NAME);
        byte[] value = DatatypeConverter.parseHexBinary(str);
        SecretKeySpec secKey = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(aesIvBytes);

        //NoSuchPaddingException, NoSuchAlgorithmException,
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //InvalidKeyException, InvalidAlgorithmParameterException
        c.init(Cipher.DECRYPT_MODE, secKey, iv);
        // IllegalBlockSizeException, BadPaddingException
        byte[] bytes = c.doFinal(value);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String desEncrypt(String str) throws Exception {

        byte[] keyBytes = KEY.getBytes(CHARSET_UTF_8);
        byte[] content = str.getBytes(CHARSET_UTF_8);

        DESKeySpec keySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRETKEY_INSTANCE);
        SecretKey key = keyFactory.generateSecret(keySpec);

        System.out.println("desEncrypt algorithm " + key.getAlgorithm());

        Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivBytes));
        byte[] result = cipher.doFinal(content);
        return String.valueOf(Hex.encodeHex(result, false));
    }

    public static String desDecrypt(String str) throws Exception {
        byte[] key = KEY.getBytes(CHARSET_NAME);
        byte[] value = DatatypeConverter.parseHexBinary(str);

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, SECRETKEY_INSTANCE);
        Cipher decryptCipher = Cipher.getInstance(CIPHER);
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));

        return new String(decryptCipher.doFinal(value), CHARSET_UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String tEst = "tEst";
        String desEncrypt = desEncrypt(tEst);
        String aesEncrypt = aesEncrypt(tEst);

        try {
            log.info("result {}", desDecrypt(aesEncrypt));
        } catch (Exception e) {
            log.info("result {}", aesDecrypt(aesEncrypt));

        }
    }
}
