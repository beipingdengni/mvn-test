package com.tian.spring.common.sort;

import com.google.common.primitives.Ints;

/**
 * @author tianbeiping
 * @Title: InsertSortMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/12/4下午4:57
 */
public class SelectionSortMainTest {


    public static int maxData(int[] list, int length) {
        int base = list[0];
        int index = 0;
        for (int i = 0; i < length; i++) {
            int j= i + 1;
            if (list[j] > base) {
                base = list[j];
                index = j;
            }
        }
        return index;
    }

    public static void insertSort(int[] list) {
        int temp = 0;
        for (int i = list.length - 1; i >= 0; i--) {
            int index = maxData(list, i);
            temp = list[i];
            list[i] = list[index];
            list[index] = temp;
        }


    }


    public static void main(String[] args) {

        int[] data = {5, 9, 3, 4, 6, 8, 1, 0};

        insertSort(data);

        System.out.println(Ints.asList(data));
    }

}
