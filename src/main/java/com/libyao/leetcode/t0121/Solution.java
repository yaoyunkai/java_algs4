package com.libyao.leetcode.t0121;

/*
121. 买卖股票的最佳时机

给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

 */
public class Solution {

    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int result = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > result) result = profit;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a = {7, 1, 5, 3, 6, 4};

        int i = s.maxProfit(a);
        System.out.println(i);

    }

}
