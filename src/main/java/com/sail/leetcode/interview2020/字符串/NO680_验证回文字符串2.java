package com.sail.leetcode.interview2020.字符串;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO680_验证回文字符串2 {

    /**
     * 36ms 5.18%
     * 思路就是 从两边逼近
     * 两边字符相等，左指针右移，右指针左移
     * 两边字符不等，要么左指针右移 要么右指针左移，表示删掉一个元素，使用d来计数这个删掉的元素个数，不能超过1个
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        if(s==null||s.length()==0){
            return true;
        }
        return validPalindrome(s,0,s.length()-1,1);
    }

    public boolean validPalindrome(String s,int l,int r,int d){
        if(d<0){
            return false;
        }
        if(l>=r){
            return true;
        }

        boolean res = false;
        if(s.charAt(l)==s.charAt(r)){
            res = validPalindrome(s,l+1,r-1,d);
        }

        return res || validPalindrome(s,l+1,r,d-1)|| validPalindrome(s,l,r-1,d-1);
    }
}
