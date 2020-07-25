package com.sail.leetcode.interview2020.动态规划;

import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *  
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 *  
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO120_三角形最小路径和 {


    /**
     * 将二维数组压缩为一维数组
     * d[j]表示第j列位置的最小元素和
     * 之所以用一维可以做，是因为二维数组都是由i-1计算到i的过程，可以把这个i给忽略掉。
     * d[j]中会暂存上一轮i计算出的对应位置的最小路径和
     *
     */
    private int minResult = Integer.MAX_VALUE;
    private int[] d;
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0||triangle.get(0).size()==0){
            return 0;
        }
        if(triangle.size()==1){
            return triangle.get(0).get(0);
        }
        d = new int[triangle.get(triangle.size()-1).size()];

        for(int i=0;i<d.length;i++){
            /**
             * 这里要倒着来，因为正向计算的话，后面的值会使用本轮算出来的值进行计算
             * 这是不对的，一维数组左边的d[j]表示的都是本轮要算的值
             * 而一维数组右边的d[i]、d[i-1]都是暂存的上一轮的值，这一轮使用的
             */
            for(int j=triangle.get(i).size()-1;j>=0;j--){
                /**
                 * j==0与j==triangle.get(i).size()-1都是边界条件。
                 */
                if(j==0){
                    d[j] = d[j] + triangle.get(i).get(j);
                }else if(j==triangle.get(i).size()-1){
                    d[j] = d[j-1]+triangle.get(i).get(j);
                }else{
                    d[j] = Math.min(d[j],d[j-1])+triangle.get(i).get(j);
                }
                if(i==d.length-1){
                    minResult = Math.min(minResult,d[j]);
                }
            }
        }
        return minResult;
    }

    /**
     * 这一题用dfs的话，倒数第二个例子超时
     * 所以用dp吧 d[i][j]表示从【0，0】到 【i层j列】的最小路径和
     * d[i][j] = Math.min(d[i-1][j],d[i-1][j-1])+triangle.get(i).get(j);
     * 边界条件
     * 再初始化边界条件时也需要注意更新最后一层的最小值
     */
    private int minResult1 = Integer.MAX_VALUE;
    private int[][] dp;
    public int minimumTotal1(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0||triangle.get(0).size()==0){
            return 0;
        }
        if(triangle.size()==1){
            return triangle.get(0).get(0);
        }
        dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
        dp[0][0] = triangle.get(0).get(0);
        /**
         * i=0的边界条件初始化
         */
        for(int i=1;i<dp.length;i++){
            dp[i][0] = dp[i-1][0]+triangle.get(i).get(0);
            if(i==dp.length-1){
                minResult = Math.min(minResult,dp[i][0]);
            }
        }
        /**
         * i为当前层最后一个元素的边界初始化
         */
        for(int i=1;i<dp.length;i++){
            int size = triangle.get(i).size();
            dp[i][size-1] = dp[i-1][size-2] + triangle.get(i).get(size-1);
            if(i==dp.length-1){
                minResult = Math.min(minResult,dp[i][size-1]);
            }
        }
        /**
         * 这里面计算的值都不会到达边界，只会使用边界的值
         */
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<triangle.get(i).size()-1;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1])+triangle.get(i).get(j);
                if(i==dp.length-1){
                    minResult = Math.min(minResult,dp[i][j]);
                }
            }
        }
        return minResult;
    }
}
