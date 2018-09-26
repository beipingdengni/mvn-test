package com.tian.spring.common.cryption;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.cipher.CryptoCipherFactory;
import org.apache.commons.crypto.utils.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午5:07
 */
public class MainTest {

    public static void main(String[] args) throws Exception{

//        String codeMd5 = EnCryptionUtil.getHexMD5("hello 时间");
//        System.out.println(codeMd5);
//        String codeBaseMd5 = EnCryptionUtil.getBase64MD5("hello 时间");
//        System.out.println(codeBaseMd5);
//
//        String codeMd51 = EnCryptionUtil.getHexSHA("hello 时间");
//        System.out.println(codeMd51);
//        String codeBaseMd51 = EnCryptionUtil.getBase64SHA("hello 时间");
//        System.out.println(codeBaseMd51);
//
//        String codeMd52 = EnCryptionUtil.getHexSHA256("hello 时间");
//        System.out.println(codeMd52);
//        String codeBaseMd52 = EnCryptionUtil.getBase64SHA256("hello 时间");
//        System.out.println(codeBaseMd52);

//        String key = "~20@awx,/)&eox";
//        String content = "加密的world1111";
//        String s = EnCryptionUtil.AESEncode(key, content);
//        System.out.println("s ==> " + s);
//        String s1 = EnCryptionUtil.AESDecode(key, s);
//        System.out.println("s1 ==> " + s1);
//
//
//        String sq = EnCryptionUtil.DESencrypt(content, key);
//        System.out.println("sq ==> " + sq);
//        String sw = EnCryptionUtil.DESdecrypt(sq, key);
//        System.out.println("sw ==> " + sw);


        byte[] bytes = DigestUtils.md5("cehisasdhiasd ");
        System.out.println(Base64.encodeBase64String(bytes));



    }

}
