package com.sail.leetcode.interview2020.经典习题;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO7_整数反转 {


    /**
     * 2ms
     * 不转换效率最高，直接处理int
     * 有个要点，如何判断result已经溢出了
     * 乘以10再除以10看看是不是等于原来的数
     */
    public int reverse1(int x) {

        int result = 0;
        while(x!=0){
            if((result*10)/10!=result){
                return 0;
            }
            int c = x%10;
            x = x/10;
            result= 10*result+c;
        }
        return result;
    }

    /**
     * 3ms 23.96%
     * 转换为字符串处理
     * @param x
     * @return
     */
    public int reverse(int x) {
        if(x==0){
            return 0;
        }
        boolean isPositive = true;
        long tmp = (long)x;
        if(tmp<0){
            tmp = Math.abs(tmp);
            isPositive=false;
        }
        String numStr = String.valueOf(tmp);
        char[] c = numStr.toCharArray();
        int l=0;
        int r=c.length-1;
        while(l<r){
            char tmpc = c[l];
            c[l]=c[r];
            c[r]=tmpc;
            l++;
            r--;
        }
        tmp = Long.parseLong(new String(c));
        if(!isPositive){
            tmp = -tmp;
        }
        if(tmp>Integer.MIN_VALUE&&tmp<Integer.MAX_VALUE){
            return (int)tmp;
        }
        return 0;
    }
}
