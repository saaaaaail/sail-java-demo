package com.sail.leetcode;

/**
 * @program: No312
 * @description: 312.戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * @author: sail
 * @create: 2019/5/4 13:15
 */

public class No312 {
    public static void main(String[] args) {
        int[] nums = new int[]{3,1,5,8};
        No312 no312 = new No312();
        System.out.println(no312.maxCoins(nums));
    }

    /**
     * 状态转移方程：dp[i][j]=Math.max(dp[i][j],dp[i][k-1]+newNums[i-1]*newNums[k]*newNums[j+1]+dp[k+1][j]);
     * dp[i][j]表示i到j的最大硬币和
     * k表示i~j里最后取得的硬币位置
     * 因此先取dp[i][k-1]与dp[k+1][j]的值
     * 当k两边硬币取完儿以后，还剩的硬币是0~i-1、k、j+1~length，
     * 因此取k的时候获得的硬币数目为 nums[i-1]*nums[k]*nums[j+1]
     * @param nums
     * @return
     */

    public int maxCoins(int[] nums) {
        int[] newNums = new int[nums.length+2];
        newNums[0]=1;
        newNums[newNums.length-1]=1;
        for (int i=0;i<nums.length;i++){
            newNums[i+1]=nums[i];
        }
        int[][] dp = new int[newNums.length][newNums.length];
        //1~newNums.length-2
        for (int j=1;j<newNums.length-1;j++){
            for (int i=j;i>=1;i--){
                for (int k=i;k<=j;k++){
                    dp[i][j]=Math.max(dp[i][j],dp[i][k-1]+newNums[i-1]*newNums[k]*newNums[j+1]+dp[k+1][j]);
                }
            }
        }

        return dp[1][newNums.length-2];

    }
}
