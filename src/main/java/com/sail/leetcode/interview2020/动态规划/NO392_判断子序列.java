package com.sail.leetcode.interview2020.动态规划;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 *
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 *
 * 返回 false.
 *
 * 后续挑战 :
 *
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢:
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO392_判断子序列 {


    public static void main(String[] args) {
        NO392_判断子序列 t = new NO392_判断子序列();
        t.isSubsequence("abc",
                "ahbgdc");
    }

    /**
     * 2ms 47.34%
     * 双指针，因为不是连续的字符串，就没有重复字符串里不匹配重新遍历的需要
     * O(N)依次进行下去
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        int l = 0;
        int r = 0;
        while(l<s.length()&&r<t.length()){
            if(s.charAt(l)==t.charAt(r)){
                l++;
                r++;
            }else{
                r++;
            }
        }
        return l==s.length();
    }

    //8ms 14.47%
    //这一题是判断s是不是t的子序列
    //d[i][j]表示0~i的s 的序列是否位于0~j的t中
    //d[i][j] = d[i-1][j-1] if i==j
    //d[i][j] = d[i][j-1]
    //初始化 i=0的时候d[i][j] = true
    public boolean isSubsequence(String s, String t) {
        boolean[][] d = new boolean[s.length()+1][t.length()+1];
        for(int i=0;i<=s.length();i++){
            for(int j=i;j<=t.length();j++){
                if(i==0||j==0){
                    d[i][j] = true;
                }else{
                    if(s.charAt(i-1)==t.charAt(j-1)){
                        d[i][j] = d[i][j]||d[i-1][j-1];
                    }
                    d[i][j] = d[i][j] ||d[i][j-1];
                }
            }
        }
        return d[s.length()][t.length()];
    }
}
