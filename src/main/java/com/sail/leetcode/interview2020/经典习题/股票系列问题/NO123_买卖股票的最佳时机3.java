package com.sail.leetcode.interview2020.经典习题.股票系列问题;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO123_买卖股票的最佳时机3 {

    /**
     * 5ms 63.74%
     * 递归分治法
     * 往后遍历数组，全程记录此刻的最小值，
     * 然后判断当前值比最小值大的话，就选择卖出还是不卖出
     *      如果选择卖出就记录利润，最小值初始化
     *      如果选择不卖就继续往后遍历索引，最小值不初始化
     * 当前值比最小值小或者相等的话，就卖不了，只能往后遍历
     *
     * 并不是每个节点都需要做上面的抉择，可以先不断寻找逆序，并记录最小值，因为如果股票不断增大的，买两次与一次的利润是一样的
     * 所以只要股票是在不断递增的就不用卖出，直到到达一个值小于前一个股票值，可以考虑上面的抉择问题
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        return doMaxProfit(prices,0,Integer.MAX_VALUE,0,2);
    }

    public int doMaxProfit(int[] prices,int index,int min,int profit,int count){
        if(index>=prices.length||count==0){
            return profit;
        }

        int l = index;
        min = Math.min(min,prices[l]);
        /**
         * 记录最小值，并寻找股票的峰值
         */
        while(l+1<prices.length&&prices[l]<prices[l+1]){
            l++;
            min = Math.min(min,prices[l]);
        }
        int ans = Integer.MIN_VALUE;
        /**
         * 找到股票峰值以后，决定卖还是不动
         */
        if(prices[l]>min){
            //卖
            ans = Math.max(ans,doMaxProfit(prices,l+1,Integer.MAX_VALUE,profit+prices[l]-min,count-1));
        }
        //不卖
        ans = Math.max(ans,doMaxProfit(prices,l+1,min,profit,count));

        return ans;
    }
}
