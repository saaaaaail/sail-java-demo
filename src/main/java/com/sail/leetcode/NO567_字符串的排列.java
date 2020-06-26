package com.sail.leetcode;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO567_字符串的排列 {

    /**
     * 7ms 77.04% 滑动窗口+字典数组
     * 由于字符串只包含小写字母，因此使用字典数组即可，如果包含更多的字符，可以更换成hashmap
     * s1的全排列被s2包含，意味着在s2中一段连续的字符串只要与s1的字符内容一模一样即可，毕竟总有一个与s1全排列的一种相同
     * 所以转换成比较s1的字典数组与s2的字典数组，0(26)固定长度约等于O(1)
     * 然后把滑动窗口架设在s2上，每次往后滑动就删减出框的元素，新增加入框的元素到s2的字典中，每滑一格都比较两个字典是否相同
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int[] dic1 = new int[26];
        int[] dic2 = new int[26];
        int l=0;
        int r=0;
        for(int i=0;i<s1.length();i++){
            char c = s1.charAt(i);
            dic1[c-'a']++;
        }
        while(r<s2.length()){
            while(r<s2.length()&&r-l<s1.length()){
                char rc = s2.charAt(r);
                dic2[rc-'a']++;
                r++;
            }
            if(r>=s2.length()){
                break;
            }
            if(isDicSame(dic1,dic2)){
                return true;
            }
            char lc = s2.charAt(l);
            dic2[lc-'a']--;
            l++;
            char rc = s2.charAt(r);
            dic2[rc-'a']++;
            r++;
        }
        if(isDicSame(dic1,dic2)){
            return true;
        }
        return false;
    }

    public boolean isDicSame(int[] dic1,int[] dic2){
        for(int i=0;i<26;i++){
            if(dic1[i]!=dic2[i]){
                return false;
            }
        }
        return true;
    }
}
