package com.tian.spring.common.cryption;


import org.jboss.netty.handler.codec.base64.Base64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author tianbeiping
 * @Title: CryptionUtil
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午5:00
 */
public class EnCryptionUtil {


    private static final String DIGEST_MD5 = "MD5";
    private static final String DIGEST_SHA = "SHA";
    private static final String DIGEST_SHA_256 = "SHA-256";
    private static final String DIGEST_SHA_521 = "SHA-521";

    public static String getBase64MD5(String msg) {
        return getBase64(msg, DIGEST_MD5);
    }

    public static String getHexMD5(String msg) {
        return getHex(msg, DIGEST_MD5);
    }

    public static String getBase64SHA(String msg) {
        return getBase64(msg, DIGEST_SHA);
    }

    public static String getHexSHA(String msg) {
        return getHex(msg, DIGEST_SHA);
    }

    public static String getBase64SHA256(String msg) {
        return getBase64(msg, DIGEST_SHA_256);
    }

    public static String getHexSHA256(String msg) {
        return getHex(msg, DIGEST_SHA_256);
    }


    private static String getHex(String msg, String code) {
        try {
            MessageDigest digest = MessageDigest.getInstance(code);
            return toHex(digest.digest(msg.getBytes()));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getBase64(String msg, String code) {
        try {
            MessageDigest digest = MessageDigest.getInstance(code);
            return toBase64(digest.digest(msg.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 对称加密算法主要分为:DES , 3DES(3重DES) , AES(想要替代DES)  , PBE(基于口令的对称算法)
     */

    private static String enbas(String key, String msg) {

        return null;
    }

    private static String debas(String key, String msg) {

        return null;
    }

    private static String toBase64(byte[] bytes) {
        try {
            return new String(Base64.getEncoder().encode(bytes), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
            char hex_digit = HEX_DIGITS[(bytes[i] >> 4) & 0x0F];
            System.out.println(hex_digit);
            ret.append(hex_digit);
            char hex_digit1 = HEX_DIGITS[bytes[i] & 0x0F];
            System.out.println(hex_digit1);
            ret.append(hex_digit1);

        }
        return ret.toString();
    }


}
