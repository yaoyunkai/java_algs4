package com.libyao.algs4.chp2;

import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdOut;

/*
自低向顶的归并排序

 */
public class MergeBU {

    // This class should not be instantiated.
    private MergeBU() {
    }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];  // this copying is unnecessary
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

    }


    public static void sort(Comparable[] a) {
        int n = a.length; // 18
        Comparable[] aux = new Comparable[n];

        // 以长度增长, 1, 2 4 8 16 32 ...
        for (int len = 1; len < n; len *= 2) {
            System.out.printf("len: %d \n", len);

            // 实际上是以长度2的子数组开始排序的
            for (int lo = 0; lo < n - len; lo += len + len) {
                /*
                lo: 0 2 4 6 8 10 ...
                hi: 1 3 5 7 9 11 ...
                
                
                 */
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                System.out.printf("merge, lo:%d hi:%d \n", lo, hi);
                merge(a, aux, lo, mid, hi);
            }
        }
        // assert isSorted(a);
    }


    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    // print array to standard output
    private static <T extends Comparable<T>> void show(T[] a) {
        for (T t : a) {
            StdOut.print(t);
            StdOut.print(" ");
        }
        StdOut.println();
    }


    public static void main(String[] args) {
        In in = new In("data/tinyText.txt");
        Integer[] allowlist = in.readAllIntegers();
        show(allowlist);
        sort(allowlist);
        show(allowlist);
    }
}
