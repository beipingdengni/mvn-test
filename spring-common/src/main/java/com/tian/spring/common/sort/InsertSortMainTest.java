package com.tian.spring.common.sort;

import com.google.common.primitives.Ints;

/**
 * @author tianbeiping
 * @Title: InsertSortMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/12/4下午5:46
 */
public class InsertSortMainTest {


    public static void insert(int[] list, int index) {
        int temp = list[index]; // 2

        for (int i = 0; i < index; i++) {
            if (list[i] > temp) {
                //
                list[index] = list[i];
                list[i] = temp;
                temp = list[index];
            }
        }

    }

    public static void insertSort(int[] list) {

        for (int i = 1; i < list.length; i++) {
            insert(list, i);
        }

    }


    public static void main(String[] args) {
        int[] data = {5, 9, 3, 4, 6, 8, 1, 0};

        insertSort(data);

        System.out.println(Ints.asList(data));

    }

}
