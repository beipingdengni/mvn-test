package com.tian.spring.common.sort;

import com.google.common.primitives.Ints;

import java.util.Arrays;

/**
 * @author tianbeiping
 * @Title: BitMap
 * @ProjectName mvn-test
 * @Description:
 * @date 18/12/4下午2:17
 */
public class BitMap {


    public static void main(String[] args) {


        byte[] data = {0, 0, 0, 0};  //  8 * 3   //5, 7, 14, 15, 16, 17, 22, 23,24

        for (Integer e : Ints.asList(0, 5, 7, 8, 23, 24, 16)) {
            int i = e / 8;
            byte datum = data[i];
            int i1 = e % 8;
            datum |= 0x01 << i1;
            data[i] = datum;
            System.out.println("start=" + e + " i=" + i + " i1=" + i1 + " data=" + datum);
        }
        System.out.println(Integer.toBinaryString(data[0]));
        System.out.println(Integer.toBinaryString(data[1]));
        System.out.println(Integer.toBinaryString(data[2]));
        System.out.println(Integer.toBinaryString(data[3]));
        System.out.println(data[0] + ":" + data[1] + ":" + data[2]);


    }

}
