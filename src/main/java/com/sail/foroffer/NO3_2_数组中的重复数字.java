package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 不修改数组找出重复数字
 *
 * 在一个长度为n+1的数组里所有数字都在1~n的范围内，所以数组中至少有一个数字是重复的。
 * 请找出数组中任意一个重复的数字，但不能修改数组
 * @author: sail
 * @create: 2019/05/29 20:20
 */

public class NO3_2_数组中的重复数字 {
    /**
     * 数字在1~n范围中，可以使用快慢指针
     * 必存在至少一个重复元素,因此最后一定能找到
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

    /**
     * 环形链式结构求环入口点
     *
     * @param nums
     * @return
     */
    public static int duplicate(int[] nums){
        int fast = 0;
        int slow = 0;

        while (true){
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(fast==slow){
                fast=0;
                while (fast!=slow){
                    fast=nums[fast];
                    slow=nums[slow];
                }
                break;
            }
        }
        return slow;

    }
}
