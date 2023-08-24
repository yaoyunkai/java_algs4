package com.libyao.algs4.chp2;

import edu.princeton.stdlib.In;
import edu.princeton.stdlib.StdOut;

/*
快速排序

快速排序是一种分治的排序算法。它将一个数组分成两个子数组，将两部分独立地排序。
而快速排序将数组排序的方式则是当两个子数组都有序时整个数组也就自然有序了。

两个动作：切分和排序

切分：
对于某个j, a[j]已经排定；
a[lo]到a[j-1]中的所有元素都不大于a[j]；
a[j+1]到a[hi]中的所有元素都不小于a[j]。

切分的过程:
一般策略是先随意地取a[lo]作为切分元素，即那个将会被排定的元素，
然后我们从数组的左端开始向右扫描直到找到一个大于等于它的元素，再从数组的右端开始向左扫描直到找到一个小于等于它的元素。
这两个元素显然是没有排定的，因此我们交换它们的位置。
如此继续，我们就可以保证左指针i的左侧元素都不大于切分元素，右指针j的右侧元素都不小于切分元素。
当两个指针相遇时，我们只需要将切分元素a[lo]和左子数组最右侧的元素（a[j]）交换然后返回j即可。

因为切分过程总是能排定一个元素，用归纳法不难证明递归能够正确地将数组排序：
如果左子数组和右子数组都是有序的，那么由左子数组（有序且没有任何元素大于切分元素）、
切分元素和右子数组（有序且没有任何元素小于切分元素）组成的结果数组也一定是有序的。

缺点: 时间复杂度会退化为 O n**2


 */
public class Quick {

    public static <T extends Comparable<T>> void sort(T[] a) {
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        // assert isSorted(a);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        StdOut.printf("sort: lo %d, hi %d\n", lo, hi);
        if (hi <= lo) return;

        // if (hi <= lo + 8) {
        //     Insertion.sort(a, lo, hi);
        //     return;
        // }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        // assert isSorted(a, lo, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        StdOut.printf("partition: lo %d, hi %d\n", lo, hi);

        int i = lo;
        int j = hi + 1; // 为了能比较最后一个元素

        T v = a[lo];

        // 终止条件是left指针大于等于right指针
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
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
        // 3 9 6 4 7 8 1 5 2 

        In in = new In("data/tinyText.txt");
        Integer[] allowlist = in.readAllIntegers();
        show(allowlist);
        sort(allowlist);
        show(allowlist);

        // Integer[] arr = {3, 9, 6, 4, 7, 8, 1, 5, 2};
        // sort(arr);
    }
}
