package com.sail.leetcode.interview2020.动态规划;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO32_最长有效括号 {

    /**
     * dp 1ms 100%
     * 注意 这一题的有效括号长度指的是 连续的子串
     * 采用了一个数组d[i]表示以i结尾的字符串的最长有效括号长度
     * lc存到i为止'('左括号的数量
     * rc存到i为止')'右括号的数量， 如果左括号数量大于0，碰到了右括号 就数量抵消掉
     * d[i]的状态转移方程就是
     * 如果当前括号是'('或者 前面没有'('的情况下的')' 这两种情况d[i]=0
     * 如果是前面有'('当前括号为')' 则首先计算 以i结尾的括号嵌套字符串长度d[i-1]+1
     * 然后加上嵌套括号长度之前的并列括号的长度，等于d[i]的最终值
     * 例:
     * ( ) ( ( ) )
     * 1 2 3 4 5 6
     * 以这个字符串最后一个字符为例d[5]的长度肉眼可见为1个完整括号，
     * d[5]=1，d[6]的话由于3号位为'(',d[6] =d[5]+1,还没完d[6]还要加上d[2]
     * d[2]要根据d6的嵌套括号长度计算出来6-2*2=2
     * 最终d[6] = d[5]+1+d[2]
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int lc = 0;
        int rc = 0;
        int[] d =new int[s.length()+1];
        int res = 0;
        d[0]=0;
        for(int i=1;i<=s.length();i++){
            char c = s.charAt(i-1);
            if(c=='('){
                lc++;
                d[i]=0;
            }else if(c==')'){
                if(lc>0){
                    lc--;
                    d[i] = d[i-1]+1;
                    d[i] = d[i] + d[i-2*d[i]];
                }else{
                    rc++;
                    d[i] = 0;
                }
            }
            res = Math.max(res,d[i]);
        }
        return 2*res;
    }
}
