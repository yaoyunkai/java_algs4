package com.libyao.leetcode.t0189;

/*
给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

nums = "----->-->"; k =3
result = "-->----->";

reverse "----->-->" we can get "<--<-----"
reverse "<--" we can get "--><-----"
reverse "<-----" we can get "-->----->"
this visualization help me figure it out :)


 */
public class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int a, int b) {
        //用双指针好了
        int i = a, j = b;
        while (i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) return;

        for (int j = 0; j < k; j++) {
            int last = nums[n - 1];
            for (int i = n - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = last;
        }
    }


    public void rotate1(int[] nums, int k) {
        int[] aux = new int[nums.length];
        int n = nums.length;

        System.arraycopy(nums, 0, aux, 0, n);

        for (int i = 0; i < k; i++) {
            int tmp = n - k + i;
            nums[i] = aux[tmp];
        }

        for (int i = k; i < n; i++) {
            nums[i] = aux[i - k];
        }

    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        Solution solution = new Solution();
        solution.rotate(a, 3);

    }
}
