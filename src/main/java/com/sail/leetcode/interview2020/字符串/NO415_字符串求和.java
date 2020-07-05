package com.sail.leetcode.interview2020.字符串;

import java.util.Arrays;

public class NO415_字符串求和 {
    public static void main(String[] args) {
        char q = '2';
        char w = '3';
        System.out.println((char)(q+w));

    }


    /**
     * 从末尾开始算即可,有一个字符串到0了就不加他了即可
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int c = 0;
        int l1 = num1.length()-1;
        int l2 = num2.length()-1;

        char[] res = new char[Math.max(num1.length(),num2.length())+1];
        int lc = res.length-1;
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        while(l1>=0||l2>=0){
            char num = (char)c;
            if(l1<0){
                num += c2[l2--];
            }else if(l2<0){
                num += c1[l1--];
            }else{
                num += (c1[l1--]+c2[l2--]-'0');
            }
            c=0;
            if(num>'9'){
                num -=10;
                c=1;
            }
            res[lc--] = num;
        }
        /**
         * 算完以后还要考虑有没有进位
         */
        res[lc] = '0';
        if(c==1){
            res[lc] += (char)c;
        }


        if(res[lc]=='0'){
            return String.valueOf(Arrays.copyOfRange(res,lc+1,res.length));
        }

        return String.valueOf(res);
    }
}
