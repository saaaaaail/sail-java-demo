package com.sail.leetcode.interview2020.动态规划;

/**
 *给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 *
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "rabbbit", T = "rabbit"
 * 输出：3
 * 解释：
 *
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2：
 *
 * 输入：S = "babgbag", T = "bag"
 * 输出：5
 * 解释：
 *
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 *
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO115_不同的子序列 {

    /**
     * 这一题是计算 s字符串里面包含几个t字符串的序列
     *
     * dp
     * d[i][j]表示0~i的s字符串包含0~j的t字符串的数量，然后进行倒推思维，从最后一个字符考虑
     * 如果s的第i个字符等于t的第j个字符，
     * 那么d[i][j]一部分是等于d[i-1][j-1]，表示s使用i字符与t的j字符进行比较，那么s与t就同时往前移动一个字符
     * d[i][j]另一部分是等于d[i-1][j]，表示s不使用i字符与t的j字符进行比较，即s往前移一个，t不动
     * 如果s的第i个字符不等于t的第j个字符，
     * d[i][j]就只能等于d[i-1][j]，表示s不使用i字符与t的j字符进行比较，意思还是最后这个字符不等，用s的前一个字符去匹配t的这个字符是否相等。
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {

        int[][] d = new int[s.length()+1][t.length()+1];
        for(int i=0;i<=s.length();i++){
            d[i][0] = 1;
        }
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=t.length();j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    d[i][j] = d[i-1][j-1]+d[i-1][j];
                }else{
                    d[i][j] = d[i-1][j];
                }
            }
        }
        return d[s.length()][t.length()];
    }
}
