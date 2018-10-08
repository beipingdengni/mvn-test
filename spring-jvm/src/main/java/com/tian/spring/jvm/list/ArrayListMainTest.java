package com.tian.spring.jvm.list;

import java.util.Arrays;

/**
 * @author tianbeiping
 * @Title: ArrayListMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/8上午10:23
 */
public class ArrayListMainTest {


    public static void main(String[] args) {

        /**
         * ArrayList 数组的扩容 量的计算
         */
        int old = 10;
        int newData = old + (old >> 1);
        System.out.println(newData);

        /**
         * ArrayList 数组的扩容后，数组的copy
         * 底层用到 System.arraycopy(original, 0, copy, 0,Math.min(original.length, newLength));
         */
        String[] originString = {"name", "asss"};
        String[] strings = Arrays.copyOf(originString, 4);
        System.out.println(Arrays.asList(strings));
        /**
         * copy 数组计算
         */
        Object[] descString = new Object[4];
        System.arraycopy(originString, 0, descString, 0, originString.length);
        System.out.println(Arrays.asList(descString));

        /**
         * 线程安全 数据
         * CopyOnWriteArrayList  方法上使用 synchronized
         * Vector  内部使用了 ReentrantLock 锁
         */

        /**
         * linked 双向链表
         */


    }

}
