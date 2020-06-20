package com.sail.foroffer;

import java.util.HashSet;
import java.util.Set;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 *  
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
 *
 * 提示：
 *
 * s.length <= 40000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO48_最长不含重复字符的子字符串 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
    public static int lengthOfLongestSubstring(String s) {

        if(s==null ||s.length()==0){
            return 0;
        }
        return longestSubstring(s);
    }

    /**
     * dp 10% 效率低
     * d[i]与d[i-1]的关系就是，如果d[i-1]的序列里与字符i不重复，就直接加一
     * 如果重复了，就找到d[i-1]的序列里的重复的字符i的位置到当前字符i的位置的距离就是d[i]
     * @param s
     * @return
     */
    // d[i]表示从0开始以i结尾的最长连续串的长度
    //d[i]  如果不包含charAt(i)的字符d[i-1]+1
    //如果包含了s.substring(0,i).indexOf(charAt(i)).substring
    public static int longestSubstring(String s){

        int[] d = new int[s.length()];
        d[0]=1;
        for(int i=1;i<s.length();i++){
            char c = s.charAt(i);
            String subStr = s.substring(i-d[i-1],i);
            if(!subStr.contains(c+"")){
                d[i] = d[i-1]+1;
            }else{
                d[i] =  d[i-1] - subStr.lastIndexOf(c+"");
            }
        }
        int result = 0;
        for(int i=0;i<s.length();i++){
            result = Math.max(result,d[i]);
        }
        return result;
    }


    /**
     * 维护一个滑动窗口 l-i，使用set来装这些元素，主要是O1的访问，能快一些
     * 如果set没有就将i位置的元素装入set，然后i++  即i右移
     * 如果set存在了，就将l位置的元素移除，然后l++右移，如果依然重复，继续l++右移，同时删除set里l对应位置的元素
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {

        if(s==null ||s.length()==0){
            return 0;
        }
        int l=0;
        int result = 0;
        Set<Character> set = new HashSet<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            while(set.contains(c)){
                set.remove(s.charAt(l++));
            }
            set.add(c);
            result = Math.max(result,i-l+1);
        }
        return result;
    }
}
