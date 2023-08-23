package com.libyao.algs4.chp2;


import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdOut;

/*
选择排序

首先，找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置
（如果第一个元素就是最小元素那么它就和自己交换）。
再次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。
如此往复，直到将整个数组排序。这种方法叫做选择排序，因为它在不断地选择剩余元素之中的最小者。

对于长度为N的数组，选择排序需要大约 N**2/2次比较和N次交换。

*/
public class Selection {
    private Selection() {
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int min = i;
            // 找一个最小的
            for (int j = i + 1; j < n; j++) {
                if (less(arr[j], arr[min])) min = j;
            }
            exch(arr, i, min);
            // assert isSorted(arr, 0, i);
        }
        // assert isSorted(arr);
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
        // In in = new In("data/tinyText.txt");
        // Integer[] allowlist = in.readAllIntegers();
        // show(allowlist);
        // sort(allowlist);
        // show(allowlist);

        Person p1 = new Person("tom", 14);
        Person p2 = new Person("pop", 15);
        Person p3 = new Person("lily", 18);
        Person p4 = new Person("ming", 13);
        Person p5 = new Person("sag", 12);
        Person p6 = new Person("rre", 16);

        Person[] persons = new Person[]{p1, p2, p3, p4, p5, p6};

        show(persons);
        sort(persons);
        show(persons);
    }

}
