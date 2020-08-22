package com.sail.leetcode.interview2020.动态规划;

import java.util.Arrays;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO132_分割回文串2 {
    public static void main(String[] args) {
        NO132_分割回文串2 t = new NO132_分割回文串2();
        int aab = t.minCut("a");
        System.out.println(aab);
    }

    /**
     * 16ms 55.94%
     *  代码分为两部分
     *  第一部分dp[i][j]表示i~j的字符串是不是回文串
     *  第一块代码，计算s字符串里面两个字符间的字符串是不是回文串
     *
     *  第二部分d[i] 表示0~i的字符串里最少有几个回文串组成
     *  第二块代码就是，0~i中选择一个j如果j~i是回文串，那么d[i]的数量就等于0~j的最少回文串数量+1
     *  即d[i]  =d[j-1]+1;然后要遍历找出0~i的每一个满足j~i是回文串的j，求最小值，就是最后要的答案
     * @param s
     * @return
     */
    public int minCut(String s) {
        int[] d = new int[s.length()+1];
        Arrays.fill(d,s.length()-1);
        boolean[][] dp = new boolean[s.length()+1][s.length()+1];
        for(int j=1;j<=s.length();j++){
            dp[j][j] = true;
            for(int i=j-1;i>=1;i--){
                if(s.charAt(i-1)==s.charAt(j-1)){
                    dp[i][j] = dp[i][j] ||(i+1 == j)|| dp[i+1][j-1];
                }
            }
        }
        d[0]=0;
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=i;j++){
                if(dp[j][i]){
                    d[i] = Math.min(d[i],d[j-1]+1);
                }
            }
        }
        return d[s.length()];
    }
}
