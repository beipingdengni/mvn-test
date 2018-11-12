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

    /**
     * 快速排序
     */
    public static void quickSort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key) {  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            }
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
                System.out.println("key:" + key + " end   index:" + end + " start:" + a[start] + " end:" + a[end]);
                System.out.println(Ints.asList(a));
            }
            //从前往后比较
            while (end > start && a[start] <= key) {//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            }
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                System.out.println("key:" + key + " start index:" + start + " start:" + a[start] + " end:" + a[end]);
                System.out.println(Ints.asList(a));
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) {
            //左边序列。第一个索引位置到关键值索引-1
            quickSort(a, low, start - 1);
        }
        if (end < high) {
            //右边序列。从关键值索引+1到最后一个
            quickSort(a, end + 1, high);
        }
    }

    /**
     * 优化后，快速排序
     */
    public static int partition(int[] array, int lo, int hi) {
        //三数取中
//        int mid = lo + (hi - lo) / 2;
//        if (array[mid] > array[hi]) {
//            swap(array[mid], array[hi]);
//        }
//        if (array[lo] > array[hi]) {
//            swap(array[lo], array[hi]);
//        }
//        if (array[mid] > array[lo]) {
//            swap(array[mid], array[lo]);
//        }
        int key = array[lo];

        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {
                hi--;
            }
            array[lo] = array[hi];
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];
        }
        array[hi] = key;
        return hi;
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static void sort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = partition(array, lo, hi);
        sort(array, lo, index - 1);
        sort(array, index + 1, hi);
    }

    /**
     * 选择排序算法
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
     * 以此类推，直到所有元素均排序完毕。
     *
     * @param numbers
     */
    public static void selectSort(int[] numbers) {
        int size = numbers.length; //数组长度
        int temp = 0; //中间变量

        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }

    /**
     * 插入排序
     *
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     * @param numbers  待排序数组
     */
    public static void insertSort(int[] numbers)
    {
        int size = numbers.length;
        int temp = 0 ;
        int j =  0;

        for(int i = 0 ; i < size ; i++)
        {
            temp = numbers[i];
            //假如temp比前面的值小，则将前面的值后移
            for(j = i ; j > 0 && temp < numbers[j-1] ; j --)
            {
                numbers[j] = numbers[j-1];
            }
            numbers[j] = temp;
        }
    }


    public static void main(String[] args) {
        /**
         * 参考：https://www.cnblogs.com/0201zcr/p/4763806.html
         */
        int[] data = {6, 7, 9, 1, 5, 3};
//        bubbleSort(data);
//        quickSort(data, 0, data.length - 1);
//        sort(data, 0, data.length - 1);
//        selectSort(data);
        insertSort(data);

        System.out.println(Ints.asList(data));


    }
}
