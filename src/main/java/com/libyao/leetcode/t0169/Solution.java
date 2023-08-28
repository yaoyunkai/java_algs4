package com.libyao.leetcode.t0169;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class Solution {

    public int majorityElement2(int[] nums) {
        HashMap<Integer, Integer> st = new HashMap<>();

        for (int num : nums) {
            if (st.containsKey(num)) {
                st.put(num, st.get(num) + 1);
            } else {
                st.put(num, 1);
            }
        }

        Map.Entry<Integer, Integer> maxItem = null;

        for (Map.Entry<Integer, Integer> item : st.entrySet()) {
            if (maxItem == null || item.getValue() > maxItem.getValue()) {
                maxItem = item;
            }
        }

        return maxItem.getKey();
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


}
