package com.libyao.leetcode.t0026_remove_duplicates_from_sorted_array;

/*
给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 */
public class Solution {
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] == nums[i]) {
                for (int j = i; j < n - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                n--;
                i--;
            }
        }

        return n;
    }

    public int removeDuplicates(int[] nums) {
        // p q 把数组分为三个部分 
        // 要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }


    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] b = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        Solution solution = new Solution();
        solution.removeDuplicates(a);
    }

}
