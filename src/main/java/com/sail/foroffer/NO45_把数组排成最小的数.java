package com.sail.foroffer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *  
 *
 * 提示:
 *
 * 0 < nums.length <= 100
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO45_把数组排成最小的数 {

    public static void main(String[] args) {
        String[] numStrs = new String[10];

    }

    /**
     * 思路在于将num转成字符串，前面的字符小，整个字符就小
     * 对于长度不一样的字符串o1和o2的话，比较起来很麻烦，因此可以先前后拼起来
     * 让长度相同再比较
     * o1+o2 compareTo o2+o1
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            numStrs[i]=String.valueOf(nums[i]);
        }

        Arrays.sort(numStrs,(o1,o2)->{
            String d1 = o1+o2;
            String d2 = o2+o1;
            return d1.compareTo(d2);

        });

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numStrs.length;i++){
            sb.append(numStrs[i]);
        }
        return sb.toString();
    }
}
