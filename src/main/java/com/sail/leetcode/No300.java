package com.sail.leetcode;

/**
 * @program: JavaDemo
 * @description: 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * @author: sail
 * @create: 2019/05/05 19:16
 */

public class No300 {
    public static void main(String[] args) {
        No300 no300 = new No300();
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int res = no300.lengthOfLIS(nums);
        System.out.println(res);
    }

    public int lengthOfLIS(int[] nums) {
        int res=1;
        int[] dn = new int[nums.length];//保存以i结尾的最长序列
        int min=nums[0];int imin=0;

        for (int i=1;i<nums.length;i++){
            if (nums[i]>min){
                for (int j=0;j<i;j++){
                    if(nums[i]>nums[j]){
                        int tmp=dn[j]+1;
                        dn[i]=Math.max(dn[i],tmp);
                    }
                }
            }else{
                dn[i]=1;
            }

            if (min>nums[i]){
                min=nums[i];
                imin=i;
            }

            if (res<dn[i]){
                res=dn[i];
            }
        }
        return  res;
    }


}
