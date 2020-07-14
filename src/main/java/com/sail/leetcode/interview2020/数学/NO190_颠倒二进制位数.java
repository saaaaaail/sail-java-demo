package com.sail.leetcode.interview2020.数学;

/**
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * 示例 2：
 *
 * 输入：11111111111111111111111111111101
 * 输出：10111111111111111111111111111111
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 *  
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO190_颠倒二进制位数 {
    public static void main(String[] args) {
        NO190_颠倒二进制位数 t = new NO190_颠倒二进制位数();
        t.reverseBits(43261596);
    }

    /**
     * 二进制颠倒，思路，左右指针，0~31 移到n对应的位数上，
     * 判断这一位左指针与右指针不同即表示需要交换（都为0或者都为1，不动就可以）
     * 其实也不用交换，就是将0变成1，将1变成0即可
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int num=1;
        int l = 31;
        int r = 0;
        while(l>r){
            //获得n对应位的数
            int right = n&(num<<r);
            int left = n&(num<<l);
            //不等于0就说明对应位为1
            if(!(right!=0&&left!=0||right==0&&left==0)){
                if(left!=0){
                    //将1变为0
                    n = n^(num<<l);
                    //将0变为1
                    n = n|(num<<r);
                }else{
                    n = n|(num<<l);
                    n = n^(num<<r);
                }
            }
            l--;
            r++;
        }
        return n;
    }
}
