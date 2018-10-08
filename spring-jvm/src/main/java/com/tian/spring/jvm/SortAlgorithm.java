package com.tian.spring.jvm;

import com.google.common.primitives.Ints;

/**
 * @author tianbeiping
 * @Title: SortAlgorithm
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/8下午5:44
 */
public class SortAlgorithm {

    /**
     * 冒泡排序算法
     *
     * @param num
     */
    public static void bubbleSort(int[] num) {
        int temp = 0;
        int length = num.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (num[j] > num[j + 1]) {
                    temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(int[] num, int start, int end) {
        int baseNum = num[start];
        int middle = 0;
        int i = start;
        int j = end;
        do {

            while ((num[i] < baseNum) && i < end) {
                i++;
            }
            while ((num[j] > baseNum) && j > start) {
                j--;
            }
            if (i < j) {
                middle = num[i];
                num[i] = num[j];
                num[j] = middle;
                i++;
                j--;
            }

        } while (i <= j);
        if (start < j) {
            quickSort(num, start, j);
        }
        if (end > i) {
            quickSort(num, i, end);
        }
    }


    public static void main(String[] args) {
        int[] data = {1, 3, 23, 4, 544, 55, 23, 4};
//        bubbleSort(data);
        quickSort(data, 0, data.length-1 );

        System.out.println(Ints.asList(data));


    }
}
