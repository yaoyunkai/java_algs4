package com.libyao.algs4.chp2;

import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdOut;

/*
归并排序
N log N

将两个有序的数组归并成一个更大的有序数组
原地归并

两个动作: 合并和递归, 先排序再merge


 */
public class Merge {
    private Merge() {
    }

    private static void sort(Comparable[] array, Comparable[] aux, int lo, int hi) {
        System.out.printf("sort, lo: %d, hi: %d \n", lo, hi);

        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(array, aux, lo, mid);
        sort(array, aux, mid + 1, hi);
        merge(array, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];
        sort(array, aux, 0, array.length - 1);
        // assert isSorted(a);
    }

    private static <T extends Comparable<T>> void merge(T[] arr, T[] aux, int lo, int mid, int hi) {
        // assert isSorted(arr, lo, mid);
        // assert isSorted(arr, mid + 1, hi);
        
        /*
        合并操作：
        前提：左右两个数组都是已经排序的
        
         */

        System.out.printf("merge, lo: %d, hi: %d \n", lo, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int i = lo, j = mid + 1;

        // k:index 要操作arr的位置
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];  // 说明处理完了左边的数组
            else if (j > hi) arr[k] = aux[i++];  // 说明处理完了右边的数组
            else if (less(aux[j], aux[i])) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
        // assert isSorted(arr, lo, hi);
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

    public static void main(String[] args) {
        // Integer[] aux = new Integer[10];
        // Integer[] arr = {1, 2, 3, 7, 8, 3, 4, 5, 6, 9};
        // merge(arr, aux, 0, 4, 9);

        In in = new In("data/tinyText.txt");
        Integer[] allowlist = in.readAllIntegers();
        show(allowlist);
        sort(allowlist);
        show(allowlist);
    }

}
