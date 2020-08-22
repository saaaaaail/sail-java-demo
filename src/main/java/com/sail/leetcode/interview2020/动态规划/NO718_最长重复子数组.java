package com.sail.leetcode.interview2020.动态规划;

/**
 * 给两个整数数组  A  和  B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 * 作者：fe-lucifer
 * 链接：https://leetcode-cn.com/problems/uncrossed-lines/solution/ni-de-yi-fu-wo-ba-liao-zui-chang-gong-gong-zi-xu-3/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class NO718_最长重复子数组 {

    /**
     * 这道题是 求最长公共子串 注意与 最长公共子序列的区别
     * d[i][j]表示0~i的A与0~j的B两个字符串最长的公共子串长度，注意是以i，j结尾的
     * 那么，如果A[i]==B[j] 最后俩数相同 d[i][j] = d[i-1][j-1] + 1
     * 如果最后俩数不同呢，以i，j结尾的d[i][j]=0，因为不连续了
     */
    public int findLength(int[] A, int[] B) {
        int d[][] = new int[A.length+1][B.length+1];
        d[0][0]=0;
        int result = Integer.MIN_VALUE;
        for(int i=1;i<=A.length;i++){
            for(int j = 1;j<=B.length;j++){
                if(A[i-1]==B[j-1]){
                    d[i][j] = d[i-1][j-1]+1;
                }else{
                    d[i][j] = 0;
                }
                result = Math.max(result,d[i][j]);
            }
        }
        return result;
    }
}
