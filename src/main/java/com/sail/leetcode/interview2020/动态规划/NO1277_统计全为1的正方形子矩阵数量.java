package com.sail.leetcode.interview2020.动态规划;

/**
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 *
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO1277_统计全为1的正方形子矩阵数量 {


    /**
     * 这一题与 最大正方形那一题 有关系噢 可以看看
     * 7ms 86.04%
     *     d[i][j]表示以点i、j为正方形的右下角能构成的最大正方形的边长
     *     d[i][j]与d[i-1][j]、d[i][j-1]、d[i-1][j-1]的关系就是取这仨里各自能构成最大正方形的最小边长的那个，举例
     *     0，1，1
     *     1，1，1
     *     1，1，1
     *     这个矩阵d[2][2]的最大正方行的边长与d[1][1]、d[1][2]、d[2][1]有关系，其中d[1][1]是1、d[1][2]是2、d[2][1]是2，那么d[2][2]也只能是d[1][1]+1=2
     *
     *     那么d[i][j]表示的是边长 与正方形的个数是什么关系 :
     *     1、首先我们会遍历每一个点，并将其当作右下角，这每个点作为右下角构成的正方形都是没有重复的。
     *     2、边长就等于以右下角为基点的正方形的个数
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {

        int[][] d = new int[matrix.length][matrix[0].length];
        int ans = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==1){
                    if(i==0||j==0){
                        d[i][j]=1;
                    }else{
                        d[i][j] = Math.min(d[i-1][j-1],Math.min(d[i][j-1],d[i-1][j]))+1;
                    }
                    ans +=d[i][j];
                }
            }
        }
        return ans;
    }
}
