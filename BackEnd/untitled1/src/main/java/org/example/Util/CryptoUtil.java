package org.example.Util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class CryptoUtil {

    private static SecretKeySpec getKey(String secret, String salt) throws Exception {
        byte[] key = (secret + salt).getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(Arrays.copyOf(key, 16), "AES");
    }

    public static String encrypt(String strToEncrypt, String secret, String salt) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, getKey(secret, salt));
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    public static String decrypt(String strToDecrypt, String secret, String salt) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, getKey(secret, salt));
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
}
