package com.sail.leetcode.interview2020.动态规划;

/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO221_最大正方形 {


    /**
     * 7ms 40.53%
     * 这一题的关键就是给正方形选择一个基准点能代表独一无二的正方形
     * 这里选择正方形的右下角点，作为正方形的基准点
     * 因为会遍历每一个点作为正方形的右下角，所以每个正方形都不重复
     * 然后当前右下角的正方形i、j
     * 与i-1、j为右下角的正方形，i、j-1为右下角的正方形，i-1、j-1为右下角的正方形都有关系
     * 有什么关系呢？
     * 以d[i][j]表示以点【i,j】为右下角的正方形的能构成正方形最大边长
     * 那么就要求点【i-1,j】【i,j-1】【i-1,j-1】都是正方形的话，他们的最大边是相同的为k
     * 那么点【i,j】构成正方形的最大边为k+1
     * 如果点【i-1,j】【i,j-1】【i-1,j-1】有一个点不能构成最大的正方形，
     * 那么点【i,j】构成正方形的最大边只能是上面三个点里最小的正方形的边+1，
     * 举个例子：
     * 0，1，1
     * 1，1，1
     * 1，1，1
     * 这个矩阵d[2][2]的最大正方行的边长与d[1][1]、d[1][2]、d[2][1]有关系，其中d[1][1]是1、d[1][2]是2、d[2][1]是2，那么d[2][2]也只能是d[1][1]+1=2
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        int[][] d = new int[matrix.length][matrix[0].length];
        int edge = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]=='1'){
                    if(i==0||j==0){
                        d[i][j]=1;
                    }else{
                        d[i][j] = Math.min(d[i-1][j-1],Math.min(d[i-1][j],d[i][j-1]))+1;
                    }
                    edge = Math.max(edge,d[i][j]);
                }
            }
        }
        return edge*edge;
    }
}
