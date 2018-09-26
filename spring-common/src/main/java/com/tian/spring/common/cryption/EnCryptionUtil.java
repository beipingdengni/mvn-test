package com.tian.spring.common.cryption;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
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
    private static final String DIGEST_AES = "AES";
    private static final String DIGEST_AES_PADING = "AES/ECB/PKCS5Padding";  //默认的加密算法


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

    public static String AESEncode(String key, String msg) {

        try {
            SecretKey secretKey = genSecureRandomKey(key, DIGEST_AES);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(DIGEST_AES);
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] bytes = msg.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(bytes);
            //10.将加密后的数据转换为字符串
            String AES_encode = new BASE64Encoder().encode(byte_AES);
            return AES_encode;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String AESDecode(String key, String msg) {

        try {
            SecretKey secretKey = genSecureRandomKey(key, DIGEST_AES);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(DIGEST_AES);
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = new BASE64Decoder().decodeBuffer(msg);
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_content);
            //10.将加密后的数据转换为字符串
            return new String(byte_AES, "utf-8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IOException | IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static SecretKey genSecureRandomKey(String key, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {

        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator generator = KeyGenerator.getInstance(algorithm);
        //2.根据ecnodeRules规则初始化密钥生成器
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        //生成一个128位的随机源,根据传入的字节数组
        generator.init(128, random);
//        generator.init(128, new SecureRandom(key.getBytes("utf-8")));
        //3.产生原始对称密钥
        SecretKey secretKey = generator.generateKey();
        //4.获得原始对称密钥的字节数组
        byte[] encoded = secretKey.getEncoded();
        //5.根据字节数组生成AES密钥
        SecretKey secretkey = new SecretKeySpec(encoded, algorithm);
        return secretkey;
    }

    /**
     * 加密
     */
    public static String DESencrypt(String datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象,ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //按单部分操作加密或解密数据，或者结束一个多部分操作
            byte[] bytes = datasource.getBytes("utf-8");
            byte[] bytes_DES = cipher.doFinal(bytes);
            return new BASE64Encoder().encode(bytes_DES);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     */
    public static String DESdecrypt(String content, String password) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//返回实现指定转换的 Cipher 对象
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            byte[] bytes = new BASE64Decoder().decodeBuffer(content);
            // 真正开始解密操作
            byte[] bytes1 = cipher.doFinal(bytes);
            return new String(bytes1, "utf-8");
        } catch (Throwable e) {
            e.printStackTrace();
        }
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
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0F]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0F]);

        }
        return ret.toString();
    }


}
