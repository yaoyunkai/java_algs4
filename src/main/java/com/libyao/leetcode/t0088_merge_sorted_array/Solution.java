package com.libyao.leetcode.t0088_merge_sorted_array;

/*
合并两个有序数组

给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

 */
class Solution {
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        // 从尾到头

        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;

        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] aux = new int[m + n];

        int i = 0, j = 0;
        int cur;

        while (i < m || j < n) {
            if (i >= m) {
                cur = nums2[j++];
            } else if (j >= n) {
                cur = nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                cur = nums1[i++];
            } else {
                cur = nums2[j++];
            }

            aux[i + j - 1] = cur;
        }

        for (int k = 0; k < m + n; k++) {
            nums1[k] = aux[k];
        }

    }
}
