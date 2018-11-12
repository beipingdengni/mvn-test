package com.tian.spring.jvm;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/9/12 20:21
 */
public class YanMainTest {


    /**
     * -Xms10m -Xmx20m -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        String shopId = "1212,3,23423";
        List<String> strings = Arrays.asList(shopId.split(","));
        int[] stringsw = Arrays.asList(shopId.split(",")).stream().mapToInt(Integer::valueOf).toArray();
        boolean b = Arrays.asList(shopId.split(",")).stream().map(Integer::valueOf).anyMatch(e -> 1212 == e);

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime localDateTime1 = localDateTime.minusDays(3);
        String format = localDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(format);


        System.out.println(8 & 4);
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Short.MAX_VALUE);
        System.out.println(Short.MIN_VALUE);

        List<Discount> ls = Lists.newArrayList();
        ls.add(new Discount(1));
        ls.add(new Discount(13));
        ls.add(new Discount(1));
        ls.add(new Discount(122));

        Collections.sort(ls, Comparator.comparingInt(Discount::getWait));

        System.out.println(ls);


    }


    @Data
    @AllArgsConstructor
    public static class Discount {
        private int wait;
    }
}
