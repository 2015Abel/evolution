package com.evolution.leetcode.arr;

/**
 * @Description: 714. 买卖股票的最佳时机含手续费
 *
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 *
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 *
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 注意:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * @program: evolution
 * @Author: Lzj
 * @Date: 2019/3/1 17:23
 */
public class StockCertifiTrade2 {

    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if(len<=1){
            return 0;
        }
        int[] income = new int[len];
        income[1] = prices[1] - prices[0]-fee;
        income[1] = income[1]<0?0:income[1];
        int max = income[1];
        for(int i=2;i<len;i++){
            for(int k=1;k<=i;k++){
                int curIncome=(prices[i]-prices[k-1]-fee)+income[k-1];
                if(curIncome>max){
                    max = curIncome;
                }
                income[i] = max;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        StockCertifiTrade2 trade2 = new StockCertifiTrade2();
        int prices[] = {1,3,2,8,4,9};
        int fee = 2;
        int res = trade2.maxProfit(prices,fee);
        System.out.println(res);
    }
}
