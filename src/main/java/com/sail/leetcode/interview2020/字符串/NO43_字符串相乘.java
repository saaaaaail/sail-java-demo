package com.sail.leetcode.interview2020.字符串;

import java.util.Arrays;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO43_字符串相乘 {

    /**
     * 6ms 54.62%
     * 思路就是以 两个字符串 其中一个字符串为基准 取每一位与另一个字符串相乘
     * 相乘结果相加并处理进位
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")){
            return "0";
        }
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        int l1 = c1.length-1;
        int l2 = c2.length-1;
        char[] res = new char[c1.length+c2.length];
        Arrays.fill(res,'0');

        /**
         * 注意点：当前字符串的位置为i，则在结果字符串里的位置为当字符串的位置加上所乘的字符串的长度
         * 即 i + c2.length，这个位置作为在结果字符串里个位的位置
         */
        for(int i=l1;i>=0;i--){
            multi(c1[i],c2,i+c2.length,res);
        }
        int l = 0;
        for(l=0;l<res.length;l++){
            if(res[l]!='0'){
                break;
            }
        }
        return String.valueOf(res,l,res.length-l);
    }
    public void multi(char cn,char[] cns,int index,char[] res){
        int l = cns.length-1;
        int c = 0;
        while(l>=0||c!=0){
            int num = c;
            if(l>=0){
                num += (int)(cns[l--]-'0')*(cn-'0');
            }

            res[index] += (char)(num%10);
            c = num/10;
            if(res[index]>'9'){
                res[index] -=10;
                c+=1;
            }
            index--;
        }
    }
}
