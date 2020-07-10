package com.sail.leetcode.interview2020.字符串;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO3_无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int[] d = new int[s.length()];

        int l = 0;
        int res = Integer.MIN_VALUE;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(i==0){
                set.add(c);
                d[i]=1;
            }else{
                if(set.contains(c)){
                    while(set.contains(c)){
                        set.remove(s.charAt(l++));
                    }
                    d[i] = i-l+1;
                }else{
                    d[i] = d[i-1] +1;
                }
                set.add(c);
            }
            res = Math.max(res,d[i]);
        }
        return res;
    }
}
