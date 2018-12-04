package com.tian.spring.common.sort;

import com.google.common.primitives.Ints;

/**
 * @author tianbeiping
 * @Title: bubbleSort
 * @ProjectName mvn-test
 * @Description:
 * @date 18/12/4下午4:21
 */
public class bubbleSort {


    public static void bubble(int[] list, int length) {
        int temp = 0;
        for (int i = 0; i < length ; i++) {
            if (list[i] > list[i+1]) {
                temp = list[i+1];
                list[i+1] = list[i];
                list[i] = temp;
            }
        }
    }


    public static void main(String[] args) {

        int[] data = {9, 3, 4, 6, 8, 1, 0};


        for (int i = data.length - 1; i > 0; i--) {
            bubble(data, i);
        }

        System.out.println(Ints.asList(data));


    }


}
