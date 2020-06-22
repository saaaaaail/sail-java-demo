package com.sail.foroffer;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * 示例 2：
 *
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *  
 *
 * 限制：
 *
 * 1 <= k < s.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO58_左旋转字符串2 {
    public static void main(String[] args) {
        char[] c = new char[10];
        String s = new String(c);

    }


    /**
     * 2ms 32.66%
     * 三次翻转
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        char[] c = s.toCharArray();
        reverseArray(c,0,n-1);
        reverseArray(c,n,c.length-1);
        reverseArray(c,0,c.length-1);
        return new String(c);
    }

    public void reverseArray(char[] c,int start,int end){
        int l = start;
        int r = end;
        while(l<r){
            char tmp = c[l];
            c[l]=c[r];
            c[r]=tmp;
            l++;
            r--;
        }
    }
}
