package com.sail.leetcode.interview2020.数学;

import java.util.Arrays;

/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO67_二进制求和 {
    public static void main(String[] args) {
        addBinary("1010","1011");
    }

    /**
     * 2ms 98.57%
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        int len = Math.max(a.length(),b.length())+1;
        char[] res = new char[len];
        int l1=a.length()-1;
        int l2=b.length()-1;
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        int l = len-1;
        int c = 0;
        while(l1>=0||l2>=0){
            res[l] = (char)c;
            if(l1>=0&&l2>=0){
                res[l] += (ac[l1--]+bc[l2--]-'0');
            }else if(l1>=0){
                res[l] += ac[l1--];
            }else{
                res[l] += bc[l2--];
            }
            c=0;
            /**
             * 注意这里是字符'1'，不要写错啦
             */
            if(res[l]>'1'){
                c=1;
                res[l] -= 2;
            }
            l--;
        }
        res[l]='0';
        if(c==1){
            res[l]=(char)(c + '0');
        }
        if(res[l]=='0'){
            return String.valueOf(Arrays.copyOfRange(res,l+1,len));
        }
        return String.valueOf(res);
    }
}
