package com.tian.spring.common.cryption;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午5:07
 */
public class MainTest {

    public static void main(String[] args) {

        String codeMd5 = EnCryptionUtil.getHexMD5("hello 时间");
        System.out.println(codeMd5);
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

        char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

        StringBuilder ret = new StringBuilder();

        byte bt = -13;
//        ret.append(HEX_DIGITS[(bt>>4) & 0xFF]);
//        ret.append(HEX_DIGITS[bt & 0xFF]);
//
//        System.out.println(ret.toString());
//        bt=-13;
        System.out.println(HEX_DIGITS[(-bt >> 4) & 0x0F]);
        System.out.println(HEX_DIGITS[-bt & 0x0F]);

        System.out.println(-13 & 0xFF);
        System.out.println((-13 >> 4) & 0xFF);

        System.out.println(1 << 4);
        System.out.println(Integer.toBinaryString(100));

        System.out.println(Math.pow(2, 4) + Math.pow(2, 3) + 1);
        System.out.println(100 >> 2);


    }
}
