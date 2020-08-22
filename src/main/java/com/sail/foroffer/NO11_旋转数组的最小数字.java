package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 旋转数组的最小数字
 * 一维旋转数组的最小数字
 * @author: sail
 * @create: 2019/05/30 15:31
 */

public class NO11_旋转数组的最小数字 {


    /**
     * 0ms 100%
     * 思路是二分法，判断哪边是非递增的，那么最小值就在哪边
     * 注意边界需要一直包含mid，边界选择为左闭右闭
     * 所以当区间里面还剩两个元素的时候就要判断跳出了，因为区间不会再缩减了
     * 这时候直接打印输出nums[j]，为什么是j呢
     * 因为一直选择的区间是逆序的区间
     * 最后剩两个元素的时候依然是逆序的
     * @param nums
     * @return
     */
    public int minArray(int[] nums) {
        int i=0;
        int j=nums.length-1;
        if(i==j){
            return nums[i];
        }
        if (nums[i]<nums[j]){
            return nums[i];
        }
        while (Math.abs(i-j)!=1){
            int mid = (i+j)/2;
            /**
             * 若i与mid与j相同的时候，无法判断mid属于前面还是后面的序列，则直接使用顺序查找
             */
            if (nums[i]==nums[mid]&&nums[mid]==nums[j]){

                int k=i;
                while (nums[k]<=nums[k+1]){
                    k++;
                    if(k+1>j){
                        return nums[k];
                    }
                }
                return nums[k+1];
            }
            if (nums[mid]>=nums[i]){
                i=mid;
            }
            if (nums[mid]<=nums[j]){
                j=mid;
            }
        }
        return nums[j];
    }
}
