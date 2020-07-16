package com.sail.leetcode.interview2020.动态规划;

/**
 * 我们在两条独立的水平线上按给定的顺序写下 A 和 B 中的整数。
 *
 * 现在，我们可以绘制一些连接两个数字 A[i] 和 B[j] 的直线，只要 A[i] == B[j]，且我们绘制的直线不与任何其他连线（非水平线）相交。
 *
 * 以这种方法绘制线条，并返回我们可以绘制的最大连线数。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：A = [1,4,2], B = [1,2,4]
 * 输出：2
 * 解释：
 * 我们可以画出两条不交叉的线，如上图所示。
 * 我们无法画出第三条不相交的直线，因为从 A[1]=4 到 B[2]=4 的直线将与从 A[2]=2 到 B[1]=2 的直线相交。
 * 示例 2：
 *
 * 输入：A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * 输出：3
 * 示例 3：
 *
 * 输入：A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 500
 * 1 <= B.length <= 500
 * 1 <= A[i], B[i] <= 2000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/uncrossed-lines
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO1035_不相交的线 {

    /**
     * 最长公共子序列问题 换皮题
     * 只要找到最长的公共子序列相连，那么条数一定是最多的
     * d[i][j] 表示以i结尾的A与以j结尾的B相连的最长公共子序列的长度
     * 如果A[i]==B[j]（最后两个数相同的话），则d[i][j] = d[i-1][j-1] + 1
     * 如果最后两个数不同，
     * d[i][j] = 0~i-1长度的A与0~j长度的B的最长公共子序列 与 0~i长度的A与0~j-1长度的B的最长公共子序列 的最大值
     * @param A
     * @param B
     * @return
     */
    public int maxUncrossedLines(int[] A, int[] B) {
        int[][] d = new int[A.length+1][B.length+1];
        d[0][0]=0;
        for(int i=1;i<=A.length;i++){
            d[i][0]=0;
        }
        for(int i=1;i<=B.length;i++){
            d[0][i]=0;
        }
        for(int i=1;i<=A.length;i++){
            for(int j=1;j<=B.length;j++){
                if(A[i-1]==B[j-1]){
                    d[i][j] = d[i-1][j-1]+1;
                }
                d[i][j] = Math.max(d[i][j],Math.max(d[i-1][j],d[i][j-1]));
            }
        }
        return d[A.length][B.length];
    }
}
