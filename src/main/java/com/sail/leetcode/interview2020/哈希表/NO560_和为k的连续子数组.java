package com.sail.leetcode.interview2020.哈希表;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO560_和为k的连续子数组 {


    /**
     * 这题 虽然是求连续子数和 但是不能调整数组顺序，数组也不是有序的，因此不能用滑动窗口
     * 但是可以使用前缀和+map求解
     * 遍历一遍数组。求出前缀和 同时入map，对于相同的前缀和，使用map计数
     * 如果sum[i]-k也在map中的话，说明找到了一段连续字串的和等于k
     * 注意特殊值，字串和为0，一开始是等于1的，就是从一开始的连续和就等于k
     * @param nums
     * @param k
     * @return
     */
    public  int subarraySum(int[] nums, int k) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int[] sums = new int[nums.length];
        int sum = 0;
        for(int i=0;i<sums.length;i++){
            sum+=nums[i];
            sums[i]=sum;
        }
        int result = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(sums[i]-k)){
                result+=map.get(sums[i]-k);
            }
            map.put(sums[i],map.getOrDefault(sums[i],0)+1);
        }
        return result;
    }
}
