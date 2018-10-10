package com.tian.spring.jvm;

import java.util.Arrays;
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

        

    }
}
