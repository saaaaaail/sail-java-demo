package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 数组中的重复数字
 *  找出数组中的重复数字
 *  在一个长度为n的数组里，所有数字都在0~n-1的范围内。数组中某些数字是重复的，
 *  但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个
 *  重复的数字。
 *  例如：输入长度为7的数组 {2,3,1,0,2,5,3}
 *  输出是重复的数字 2 或者 3
 *
 *
 * @author: sail
 * @create: 2019/05/29 18:59
 */

public class NO3_数组中重复的数字 {
    /**
     * 可以修改数组情况下做到时间复杂度为O(n)，空间复杂度为O(1)
     * 思路：
     * 从头到尾依次扫描数组的每一位
     * 首先判断每一位数字m是否与其下标i相等，若相等就继续判断下一位i+1
     * 若不等，就判断数字m是否与下标m对应的数字nums[m]相等，若相等就找到了重复元素，返回
     * 若不等，就交换i下标和m下标的数的位置，将m放到正确的位置
     * 然后重新判断i位置的数
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(",");
        int[] nums = new int[strs.length];
        for (int i=0;i<strs.length;i++){
            nums[i]=Integer.parseInt(strs[i]);
        }
        System.out.println(duplicate(nums));

    }

    public static int duplicate(int[] nums){
        int len = nums.length;
        int i=0;
        while (i<len){
            if (i==nums[i]){
                i++;
            }else if (nums[i]==nums[nums[i]]){
                return nums[i];
            }else {
                int tmp = nums[i];
                nums[i]=nums[nums[i]];
                nums[tmp]=tmp;
            }
        }
        return -1;
    }
}
