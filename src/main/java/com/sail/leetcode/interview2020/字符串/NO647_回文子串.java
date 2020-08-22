package com.sail.leetcode.interview2020.字符串;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO647_回文子串 {

    /**
     * 5ms 61.66%
     * 暴力法
     * 以每一个点为中心进行扩散，并计数
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int ans = 0;
        for(int i=0;i<s.length();i++){
            ans+=count(s,i,i);
            ans+=count(s,i,i+1);
        }
        return ans ;
    }
    public int count(String s,int l,int r){
        int ans = 0;
        while(l>=0&&r<s.length()){
            if(s.charAt(l--)==s.charAt(r++)){
                ans++;
            }else{
                break;
            }
        }
        return ans;
    }
}
