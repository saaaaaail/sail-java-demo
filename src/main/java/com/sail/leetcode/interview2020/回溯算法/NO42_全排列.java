package com.sail.leetcode.interview2020.回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO42_全排列 {

    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        doPermute(nums,0);
        return result;
    }

    public void doPermute(int[] nums,int x){
        if(x==nums.length){
            List<Integer> list =new ArrayList<>();
            for(Integer i:nums){
                list.add(i);
            }
            result.add(list);
        }
        for(int i=x;i<nums.length;i++){
            swap(nums,x,i);
            doPermute(nums,x+1);
            swap(nums,x,i);
        }
    }

    public void swap(int[] nums,int l,int r){
        int tmp = nums[l];
        nums[l]=nums[r];
        nums[r] = tmp;
    }
}
