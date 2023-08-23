package com.libyao.algs4.chp2;

import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdOut;

/*
希尔排序

希尔排序为了加快速度简单地改进了插入排序，交换不相邻的元素以对数组的局部进行排序，
并最终用插入排序将局部有序的数组排序。

希尔排序的思想是使数组中任意间隔为h的元素都是有序的。
这样的数组被称为h有序数组。
一个h有序数组就是h个互相独立的有序数组编织在一起组成的一个数组.

在进行排序时，如果h很大，我们就能将元素移动到很远的地方，为实现更小的h有序创造方便。
用这种方式，对于任意以1结尾的h序列，我们都能够将数组排序。

实现希尔排序的一种方法是对于每个h，用插入排序将h个子数组独立地排序。
但因为子数组是相互独立的，一个更简单的方法是在h-子数组中将每个元素交换到比它大的元素之前去（将比它大的元素向右移动一格）。
只需要在插入排序的代码中将移动元素的距离由1改为h即可。

 */
public class Shell {

    private Shell() {
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            /*
            n = 19
            h = 13
            

             */
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    exch(arr, j, j - h);
                }
            }
            h /= 3;
        }
    }

    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static <T extends Comparable<T>> void show(T[] a) {
        for (T t : a) {
            StdOut.print(t);
            StdOut.print(" ");
        }
        StdOut.println();
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // @SuppressWarnings("SameParameterValue")
    private static <T extends Comparable<T>> boolean isSorted(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static <T extends Comparable<T>> boolean isHsorted(T[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i - h])) return false;
        return true;
    }

    public static void main(String[] args) {
        In in = new In("data/tinyText.txt");
        Integer[] allowlist = in.readAllIntegers();

        // System.out.println(allowlist.length);
        
        show(allowlist);
        sort(allowlist);
        show(allowlist);
    }

}