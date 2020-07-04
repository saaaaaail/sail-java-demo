package com.sail.leetcode.interview2020.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO15_三数之和 {

    /**
     *
     *  23ms  82.77%
     *  先排序
     *  两数之和用左右指针，去重也用左右指针
     *  三数之和用三个指针，i从头遍历作为target，剩下两个指针为i+1 和 length-1
     *  去重的话均用当前指针与下一个指针数比，相同的话就后跳一个
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if(nums[i]>0){
                break;
            }
            if(i>0&& nums[i]==nums[i-1]){
                continue;
            }
            int target = -nums[i];
            int l = i+1;
            int r= nums.length-1;
            while(l<r){
                int sum = nums[l]+nums[r];
                if(sum == target){
                    List<Integer> one = new ArrayList<>();
                    int left = nums[l];
                    int right = nums[r];
                    one.add(nums[i]);
                    one.add(left);
                    one.add(right);
                    result.add(one);
                    /**
                     * 这里是用来去重
                     */
                    while(l<r&&nums[l] == left){
                        l++;
                    }
                    while(r>l&&nums[r] == right){
                        r--;
                    }
                }else if(sum<target){
                    l++;
                }else if(sum>target){
                    r--;
                }
            }
        }
        return result;
    }
}
