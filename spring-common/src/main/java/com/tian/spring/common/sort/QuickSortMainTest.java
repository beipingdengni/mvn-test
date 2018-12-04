package com.tian.spring.common.sort;

import com.google.common.primitives.Ints;

/**
 * @author tianbeiping
 * @Title: QuickSortMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/12/4下午4:40
 */
public class QuickSortMainTest {


    public static int division(int[] data, int left, int right) {

        int base = data[left];

        while (left < right) {
            // 从序列右端开始，向左遍历，直到找到小于base的数
            while (left < right && data[right] >= base) {
                right--;
            }
            // 找到了比base小的元素，将这个元素放到最左边的位置
            data[left] = data[right];
            // 从序列左端开始，向右遍历，直到找到大于base的数
            while (left < right && data[left] <= base) {
                left++;
            }
            // 找到了比base大的元素，将这个元素放到最右边的位置
            data[right] = data[left];
        }
        // 最后将base放到left位置。此时，left位置的左侧数值应该都比left小；
        // 而left位置的右侧数值应该都比left大。
        data[left] = base;
        return left;
    }

    public static void quickSort(int[] list, int left, int right) {

        if (left < right) {
            // 对数组进行分割，取出下次分割的基准标号
            int base = division(list, left, right);
            System.out.format("base = %d :\t", list[base]);
            printPart(list, left, right);

            // 对“基准标号“左侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            quickSort(list, left, base - 1);

            // 对“基准标号“右侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            quickSort(list, base + 1, right);
        }

    }

    // 打印序列
    public static void printPart(int[] list, int begin, int end) {
        for (int i = 0; i < begin; i++) {
            System.out.print("\t");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(list[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] data = {5, 9, 3, 4, 6, 8, 1, 0};

        quickSort(data, 0, data.length - 1);

        System.out.println(Ints.asList(data));

    }

}
