package com.sail.foroffer;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 *  
 *
 * 限制：
 *
 * 0 <= n < 2^31
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO44_数字序列中某一位数字 {

    /**
     * 思路:计算前先要找一找规律
     * 0~9 直接返回即可
     * 10~99 的数字连起来组成的字符串等于9*10*2=180位字符（2是位数）
     * 100~999 的数字连起来组成的字符串共9*100*3=2700个字符（3是位数）
     * 1000~9999 的数字连起来组成的字符串共9*1000*4=36000个字符（3是位数）
     * 因此
     * 上面的阈值 只要 位数++ 就可以不断计算出来
     * 用n减去阈值，只要小于0就可以确定n的范围，是几位数，起始数就可以知道，在这个位数下的第几个数也能算出来
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        if(n<=9){
            return n;
        }
        n-=9;
        int posi = 2;
        int pow = posi-1;
        int around = (int)(9*posi*Math.pow(10,pow));
        while(n-around>0){
            n -= around;
            posi++;
            pow = posi-1;
            around = (int)(9*posi*Math.pow(10,pow));
        }

        int start = (int)(Math.pow(10,pow));
        int mod = n%posi;
        int targetNumIndex=0;
        if(mod==0){
            targetNumIndex =  n/posi;
        }else{
            targetNumIndex = n/posi+1;
        }
        int targetNum = start +targetNumIndex-1;
        if(mod==0){
            return targetNum%10;
        }else{
            int inNum = posi-mod;
            while(inNum>0){
                targetNum /=10;
                inNum--;
            }
            return targetNum%10;
        }


    }
}
