package com.sail.leetcode.interview2020.经典习题;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO53_最大子序和 {

    /**
     * d[i]表示以i位置结束的最大连续子序列和，
     * 所以对d[i]求最大值就能知道整个序列中最大的子序列和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int[] d = new int[nums.length];
        d[0]=nums[0];
        int result = d[0];
        for(int i=1;i<d.length;i++){
            if(d[i-1]<0){
                d[i]=nums[i];
            }else{
                d[i]=d[i-1]+nums[i];
            }
            result  =Math.max(result,d[i]);
        }
        return result;
    }
}
