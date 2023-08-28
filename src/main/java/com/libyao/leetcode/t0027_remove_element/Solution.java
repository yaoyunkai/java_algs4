package com.libyao.leetcode.t0027_remove_element;

/*

给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。


 */
public class Solution {


    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                // 将变量放到left位置
                nums[left] = nums[right];
                left++;
            }
        }

        return left;
    }

    public int removeElement1(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                swap(nums, i--, j--);
            }
        }
        return j + 1;
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
