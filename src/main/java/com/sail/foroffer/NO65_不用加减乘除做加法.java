package com.sail.foroffer;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *  
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *  
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO65_不用加减乘除做加法 {

    /**
     * 移位实现加法
     * 异或 操作实现不带进位的加法
     * 与 得到进位 然后左移 再进行的异或
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {

        while(b!=0){
            int tmp = a^b;
            b = (a&b)<<1;
            a =tmp;
        }
        return a;
    }
}
