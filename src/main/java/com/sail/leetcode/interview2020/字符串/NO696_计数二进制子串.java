package com.sail.leetcode.interview2020.字符串;

/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 *
 * 重复出现的子串要计算它们出现的次数。
 *
 * 示例 1 :
 *
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 *
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 *
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 *
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 *
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO696_计数二进制子串 {
    /**
     * 8ms 97.66%
     * 主要是保证字符串相同字符的连续，通过last于cur来记录字符发生了变化，
     * 重新计数一个新的子串
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        int last = 0;
        int cur = 0;
        char lastC = '0';
        char curC = '0';
        char[] c = s.toCharArray();
        int res = 0;
        for(int i=0;i<c.length;i++){
            if(last==0&&cur==0){
                curC = c[i];
                cur++;
            }else{
                /**
                 * 新字符仍然是当前字符就数量++
                 */
                if(curC==c[i]){
                    cur++;
                }
                /**
                 * 新字符发生了变化就更新last与cur
                 */
                if(curC!=c[i]){
                    lastC = curC;
                    last = cur;
                    curC = c[i];
                    cur = 1;
                }
                /**
                 * 前一个字符数量大于当前字符，认为可以构成一个等数量字符串
                 */
                if(last>=cur){
                    res++;
                }
            }

        }
        return res;
    }
}
