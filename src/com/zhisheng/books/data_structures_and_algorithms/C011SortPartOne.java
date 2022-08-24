package com.zhisheng.books.data_structures_and_algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2022/8/24
 **/
public class C011SortPartOne {
    /**
     * 插入排序
     * @param a 待排序数组
     */
    public static void insertionSort(int[] a) {
        int length = a.length;

        if (length <= 1) return;

        for (int i = 1; i < length; ++i) {
            // 起始把数组分为两个区间，已排序区间和未排序区间
            // 初始已排序区间只有一个元素，就是数组的第一个元素

            // 未排序第一个元素
            int value = a[i];
            // 已排序区间数组最后一个位置下标
            int j = i - 1;

            // 查找插入的位置
            // 在已排序区间，从后往前比较
            for (; j >= 0; --j) {

                if (a[j] > value) {
                    // 已排序的当前位置的值比要插入的值大，则要插入的位置还在前面，已排序的当前位置的值往后挪一个位置腾出空间
                    a[j + 1] = a[j]; // 数据移动
                } else {
                    // 已排序的当前位置的值小于或等于要插入的值，则直接把值插入当前位置，此处跳出 for 循环，插入方法在下面
                    break;
                }
            }
            a[j + 1] = value; // 插入数据
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 9, 3, 6, 2};

        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
