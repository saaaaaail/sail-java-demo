package com.sail.leetcode.interview2020.动态规划;

import javax.jnlp.IntegrationService;
import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO322_零钱兑换 {

    /**
     * 第一种 动态规划
     * d[i]表示i金额下最少的硬币数
     * d[i]等于 相差一个硬币面值的 d[i-coin]+1的最小值
     * 在这个计算的过程中由于存在无解的情况，到d数组里就是那个数没有计算过
     * 要人为过滤
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int l=coins.length-1;
        int count=0;
        int[] d = new int[amount+1];
        d[0]=0;
        Arrays.sort(coins);
        for(int i=1;i<=amount;i++){
            int res = Integer.MAX_VALUE;
            for(int coin:coins){
                if(i>=coin){
                    int sub = d[i-coin];
                    //这里对于无解的必须跳过，不然污染了有解的情况
                    if(sub==-1){
                        continue;
                    }
                    res = Math.min(res,d[i-coin]+1);
                }
            }
            d[i]=res!=Integer.MAX_VALUE?res:-1;
        }
        return d[amount];

    }

    public static void main(String[] args) {
        int i = coinChangeDfs(new int[]{1, 2, 5}, 11);
        System.out.println(i);
    }

    /**
     * 还可以使用dfs 4ms 96.38%
     */
    private static int maxResult = Integer.MAX_VALUE;
    public static int coinChangeDfs(int[] coins, int amount) {
        Arrays.sort(coins);
        doCoinChange(coins,coins.length-1,amount,0);
        return maxResult== Integer.MAX_VALUE?-1:maxResult;
    }

    public static void doCoinChange(int[] coins,int l,int amount,int cnt){
        if(l<0){
            return;
        }

        int num = amount/coins[l];
        while(num>=0){
            int rest = amount-coins[l]*num;
            if(rest==0){
                maxResult = Math.min(maxResult,cnt+num);
                return;
            }
            if(cnt+num+1>maxResult){
                return;
            }
            doCoinChange(coins,l-1,rest,cnt+num);
            num--;
        }
    }
}
