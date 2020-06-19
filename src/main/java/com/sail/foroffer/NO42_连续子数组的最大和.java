package com.sail.foroffer;

/**
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *  
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO42_连续子数组的最大和 {

    /**
     * 采用d[i]数组表示从 0 ~ i 的某个数到 i 的连续最大和，计算完d[i]以后，遍历一遍d[i]找max就找到了连续的最大子数和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] d= new int[nums.length];
        d[0]=nums[0];
        /**
         * 状态转移方程就是d[i-1]如果大于0，就加上，否则就把d[i-1]给丢掉
         */
        for(int i=1;i<nums.length;i++){
            d[i] = Math.max(nums[i],d[i-1]+nums[i]);
        }
        /**
         * 计算完以后遍历d[i]找到 到哪个位置为止，连续数的和是最大的
         */
        int result = Integer.MIN_VALUE;
        for(int i = 0;i<d.length;i++){
            result  =Math.max(result,d[i]);

        }
        return result;
    }
}
