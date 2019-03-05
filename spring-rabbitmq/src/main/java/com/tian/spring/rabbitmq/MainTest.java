package com.tian.spring.rabbitmq;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/31下午5:06
 */
public class MainTest {


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        long a = 0;
        for (long l = 0; l < 50000000000l; l++) {
            a = l;
        }
        long end = System.currentTimeMillis();
        System.out.println("====>:" + (end - start) + ":" + a);

    }

}
