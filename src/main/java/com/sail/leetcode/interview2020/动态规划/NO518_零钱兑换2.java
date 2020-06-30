package com.sail.leetcode.interview2020.动态规划;

/**
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 *
 *  
 *
 * 示例 1:
 *
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 *
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 *
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO518_零钱兑换2 {

    /**ps: 二维dp的组合数问题和排列数问题 都可以交换嵌套的循环，因为子问题不会变化； 一维的dp组合数问题和排列数问题 不可以交换嵌套的循环，因为会改变子问题
     *
     * 做这题的时候感觉根爬楼梯很像，但是写出来以后结果并不对
     * 爬楼梯是解法一的方式，而本题是解法二的方式，有什么区别呢
     * 第一种是求当金额为i的时候，硬币选择方案，而且是排列性质的，即1、2、2或者2、1、2在金额不变的情况下，都会被选到
     * 第二种是求当限制硬币种类为k时，金额i的选择方案，可以注意到，硬币的顺序固定死了
     * 当第一个coin时此时dp要求得i金额只受这一种硬币影响，
     * 当第二个coin时就变为了两种硬币，然后给之前只有一种硬币的dp补充次数
     * 并且也固定死了每一轮硬币的出现
     *
     * 那可以看出本题固定死了硬币的位置求硬币的组合数，但是为什么是这样呢？
     * 其实这个问题本来是一个二维dp[k][i]表示k个硬币的时候金额i的分配情况 = dp[k-1][i]+dp[k][i-k]
     * k-1个硬币的时候，就满i的金额了，以及k个硬币的时候，只差一个不同金额的硬币
     */


        public int change1(int amount, int[] coins) {
            int[] dp = new int[amount+1];
            dp[0] = 1;
            for (int j = 1; j <= amount; j++){ //枚举金额
                for (int coin : coins){ //枚举硬币
                    if (j < coin) continue; // coin不能大于amount
                    dp[j] += dp[j-coin];
                }
            }
            return dp[amount];
        }


        public int change2(int amount, int[] coins) {
            int[] dp = new int[amount+1];
            dp[0] = 1;
            for (int coin : coins){ //枚举硬币
                for (int j = 1; j <= amount; j++){ //枚举金额
                    if (j < coin) continue; // coin不能大于amount
                    dp[j] += dp[j-coin];
                }
            }
            return dp[amount];
        }


}
