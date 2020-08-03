package com.sail.leetcode.interview2020.数组;

/**
 * 给定一个整数矩阵，找出最长递增路径的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例 1:
 *
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 *
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO329_矩阵中最长递增路径 {


    /**
     * 8ms 98.64%
     * 因为要向4个方向扩展，还是使用了dfs，但是加了数组d缓存中间结果
     * d[i][j]表示的就是以点i、j为路径起点的最长递增路径，每次计算出来就暂存起来
     * 有其他路径经过点i、j的时候就直接返回d[i][j]
     */
    private int[][] d ;
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        d = new int[matrix.length][matrix[0].length];
        int result = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                result = Math.max(result,doLongestIncreasingPath(matrix,i,j));
            }
        }
        return result;

    }

    public int doLongestIncreasingPath(int[][] matrix,int x, int y){
        if(d[x][y]!=0){
            return d[x][y];
        }

        int path = 0;
        if(x+1<matrix.length&&matrix[x][y]<matrix[x+1][y]){
            path = Math.max(path,doLongestIncreasingPath(matrix,x+1,y));
        }
        if(x-1>=0&&matrix[x][y]<matrix[x-1][y]){
            path = Math.max(path,doLongestIncreasingPath(matrix,x-1,y));
        }
        if(y+1<matrix[0].length&&matrix[x][y]<matrix[x][y+1]){
            path = Math.max(path,doLongestIncreasingPath(matrix,x,y+1));
        }
        if(y-1>=0&&matrix[x][y]<matrix[x][y-1]){
            path = Math.max(path,doLongestIncreasingPath(matrix,x,y-1));
        }
        d[x][y] = path+1;
        return path+1;
    }
}
