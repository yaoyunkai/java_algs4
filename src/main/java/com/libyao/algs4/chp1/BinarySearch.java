package com.libyao.algs4.chp1;

import edu.princeton.stdlib.In;

import java.util.Arrays;

public class BinarySearch {

    private BinarySearch() {
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param arr the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < arr[mid]) hi = mid - 1;
            else if (key > arr[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static int rank(int[] arr, int key) {
        return _rank(arr, 0, arr.length - 1, key);
    }

    private static int _rank(int[] arr, int lo, int hi, int key) {
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] > key) {
                return _rank(arr, lo, hi - 1, key);
            } else {
                return _rank(arr, lo + 1, hi, key);
            }
        }
        return -1;
    }

    /**
     * Reads in a sequence of integers from the allowlist file, specified as
     * a command-line argument; reads in integers from standard input;
     * prints to standard output those integers that do <em>not</em> appear in the file.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // read the integers from a file
        In in = new In("data/tinyAllowlist.txt");
        int[] allowlist = in.readAllInts();

        // sort the array
        Arrays.sort(allowlist);

        // System.out.println(allowlist);
        System.out.println(Arrays.toString(allowlist));

        int idx = indexOf(allowlist, 54);
        System.out.println(idx);

        int i = rank(allowlist, 54);
        System.out.println(i);

    }

}
