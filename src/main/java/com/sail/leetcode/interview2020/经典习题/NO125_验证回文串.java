package com.sail.leetcode.interview2020.经典习题;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO125_验证回文串 {
    public static void main(String[] args) {

    }

    /**
     * 2ms 99.88%
     * 思路就是左右指针夹逼
     * 碰到不是数字不是大小写字母的就一直移动
     * 考虑边界条件
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int l=0;
        int r=s.length()-1;
        while(l<r){
            char lc = s.charAt(l++);
            while(l<s.length()&&!((lc>='a'&&lc<='z')||(lc>='A'&&lc<='Z')||(lc>='0'&&lc<='9'))){
                lc = s.charAt(l++);
            }
            char lr = s.charAt(r--);
            while(r>=0&&!((lr>='a'&&lr<='z')||(lr>='A'&&lr<='Z')||(lr>='0'&&lr<='9'))){
                lr = s.charAt(r--);
            }
            if(l>=s.length()&&r<0){
                break;
            }
            if(lc>='A'&&lc<='Z'){
                lc = (char)(lc+32);
            }
            if(lr>='A'&&lr<='Z'){
                lr = (char)(lr+32);
            }
            if(lc!=lr){
                return false;
            }
        }
        return true;
    }
}
