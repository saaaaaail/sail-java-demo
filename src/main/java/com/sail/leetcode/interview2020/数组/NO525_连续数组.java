package com.sail.leetcode.interview2020.数组;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 *
 * 输入: [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO525_连续数组 {

    public static void main(String[] args) {
        NO525_连续数组 t = new NO525_连续数组();
        int maxLength = t.findMaxLength2(new int[]{0, 1,0,1});
        System.out.println(maxLength);
    }


    /**
     * 将0修改为-1，然后使用前缀和来计算 时间复杂度O(N)
     * 思路见代码里面
     */
    public int findMaxLength2(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int sum=0;
        int result = 0;
        /**
         * 用来暂存前缀和以及其第一次出现的索引
         */
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                //碰见0了就-1
                sum=sum-1;
            }else{
                //碰见1了就+1
                sum = sum+1;
            }
            /**
             * 如果碰见sum为0的时候，说明从0~i的0（将0当做-1）跟1的数量是相同
             */
            if(sum==0){
                /**
                 * 就更新一下 i+1是指包括当前i这个位置的元素
                 */
                result = Math.max(result,i+1);
            }else{
                //如果sum不是0，就判断一下前面有没有出现这个前缀和的索引
                if(map.containsKey(sum)){
                    //如果出现了就用当前位置前缀和索引减去之前这个前缀和索引，得到的中间和为0的序列长度
                    result = Math.max(result,i-map.get(sum));
                }else{
                    //如果没有出现这个前缀和就将第一次出现的位置记录下来，后面使用
                    map.put(sum,i);
                }
            }
        }
        return result;
    }

    /**
     * 错误解法，滑动窗口，没有实现，移动窗口的时机确定不了会遗漏情况
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int[] count = new int[2];
        int l=0;
        int r=0;
        int result = Integer.MIN_VALUE;
        while(r<=nums.length){
            while(r<nums.length&&count[0]!=count[1]||r==0){
                count[nums[r++]]++;
            }
            while(l<r&&r==nums.length&&count[0]!=count[1]){
                count[nums[l++]]--;
                if(r-l<=result){
                    r++;
                }
            }
            if(r>nums.length){
                return result;
            }
            if(count[0]==count[1]){
                result = Math.max(result,r-l);
                if(l<r){
                    count[nums[l++]]--;
                }

            }
        }
        return result;
    }
}
