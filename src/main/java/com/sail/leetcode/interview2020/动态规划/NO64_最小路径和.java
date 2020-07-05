package com.sail.leetcode.interview2020.动态规划;

/**
 *  给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO64_最小路径和 {

    /**
     * d[i][j] = grid[i][j] + Math.min(d[i-1][j],d[i][j-1]);
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int[][] d = new int[grid.length][grid[0].length];
        d[0][0]=grid[0][0];
        for(int i=1;i<grid.length;i++){
            d[i][0] = d[i-1][0]+grid[i][0];
        }
        for(int j=1;j<grid[0].length;j++){
            d[0][j] = d[0][j-1] + grid[0][j];
        }
        for(int i=1;i<d.length;i++){
            for(int j=1;j<d[0].length;j++){
                d[i][j] = grid[i][j] + Math.min(d[i-1][j],d[i][j-1]);
            }
        }
        return d[d.length-1][d[0].length-1];
    }
}
