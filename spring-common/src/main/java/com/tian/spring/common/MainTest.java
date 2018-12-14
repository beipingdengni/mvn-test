package com.tian.spring.common;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午2:44
 */
public class MainTest {

    public static void main(String[] args) throws ParseException {

        /**
         * 解密: decryption
         * 加密： encryption
         * 加密器: encipher
         */
        System.out.println(Integer.toBinaryString(1 | 4 | 16 | 64));
        System.out.println(1 | 4 | 16 | 64);
        char[] chars = Integer.toBinaryString(1 | 4 | 16 | 64).toCharArray();

        List<Integer> ls = Lists.newArrayList();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                ls.add(i + 1);
            }
        }
        System.out.println(ls);

//        int i = (85 >> 0) & 1;
//        int i = (85 >> 2) & 1;
//        int i = (85 >> 4) & 1;
        int i = (85 >> 6) & 1;
        if (i == 1) {
            System.out.println("ok");
        } else {
            System.out.println("no");
        }


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        TemporalAccessor parse = dateTimeFormatter.parse("13:59:59");
        LocalTime from = LocalTime.from(parse);
        Instant instant = LocalDateTime.of(LocalDate.now(), from).atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(Date.from(instant).getTime());

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");

        Date parse1 = simpleDateFormat.parse("13:59:59");



    }
}
