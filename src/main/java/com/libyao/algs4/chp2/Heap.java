package com.libyao.algs4.chp2;

import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdOut;

/*
堆排序

在堆的构造阶段中，我们将原始数组重新组织安排进一个堆中；
然后在下沉排序阶段，我们从堆中按递减顺序取出所有元素并得到排序结果。


构造堆：
1. 从左往右 swim , 就像连续向优先队列中插入元素一样。
2. 从右至左用sink()函数构造子堆 
数组的每个位置都已经是一个子堆的根结点了，sink()对于这些子堆也适用。
如果一个结点的两个子结点都已经是堆了，那么在该结点上调用sink()可以将它们变成一个堆。
这个过程会递归地建立起堆的秩序。

开始时我们只需要扫描数组中的一半元素，因为我们可以跳过大小为1的子堆。最后我们在位置1上调用sink()方法，扫描结束。

 */
public class Heap {


    private Heap() {
    }

    public static <T extends Comparable<T>> void sort(T[] pq) {
        int n = pq.length;

        // for (int k = 1; k <= n; k++) {
        //     swim(pq, k, n);
        // }

        // heapify phase
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }

        // sortdown phase
        int k = n;
        while (k > 1) {

            // 把最大的元素放到最后
            exch(pq, 1, k);
            k = k - 1;
            sink(pq, 1, k);
        }
    }

    private static <T extends Comparable<T>> void sink(T[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static <T extends Comparable<T>> void swim(T[] pq, int k, int n) {
        while (k > 1 && less(pq, k / 2, k)) {
            exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    private static <T extends Comparable<T>> boolean less(T[] pq, int i, int j) {
        // 在比较和交换时将坐标偏移
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

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
