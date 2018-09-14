package com.tian.spring.dubbo;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午12:46
 */
public class MainTest {

    public static void main(String[] args) {

        String formateString="yyyy-mm-dd HH:mm:ss.SSS";

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(formateString);

        Date date1 = new Date();

        String format2 = simpleDateFormat.format(date1);
        System.out.println(format2);

        long millis24Before = date1.getTime() - 86400000;

        Date date = new Date(millis24Before);
        String format3 = simpleDateFormat.format(date);
        System.out.println(format3);


        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(formateString);

        LocalDateTime now = LocalDateTime.now();

        String format = now.format(dateTimeFormatter);
        System.out.println(format);

        String format1 = now.minusDays(1).format(dateTimeFormatter);
        System.out.println(format1);

        String format5 = now.plusDays(1).format(dateTimeFormatter);
        System.out.println(format5);

        String format6 = now.plus(1,ChronoUnit.DAYS).format(dateTimeFormatter);
        String format7 = now.minus(1,ChronoUnit.DAYS).format(dateTimeFormatter);
        System.out.println(format6);


    }
}
