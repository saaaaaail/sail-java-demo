package com.sail.leetcode.interview2020.数组;

import java.util.Arrays;

/**
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 *  
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO238_除自身以外数组乘积 {


    /**
     * 这是除了result返回数组以外，没有额外的空间
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        if(nums==null||nums.length==0){
            return nums;
        }
        int[] result = new int[nums.length];
        Arrays.fill(result,1);
        int plus=1;
        for(int i=0;i<nums.length;i++){
            if(i!=0){
                plus*=nums[i-1];
                result[i]*=plus;
            }
        }
        plus = 1;
        for(int i=nums.length-1;i>=0;i--){
            if(i!=nums.length-1){
                plus*=nums[i+1];
                result[i] *= plus;
            }
        }

        return result;
    }


    /**
     * 这是有额外空间的
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if(nums==null||nums.length==0){
            return nums;
        }
        int[] prePlus = new int[nums.length];
        int[] postPlus = new int[nums.length];
        int plus=1;
        for(int i=0;i<nums.length;i++){
            if(i==0){
                prePlus[i]=1;
            }else{
                plus*=nums[i-1];
                prePlus[i]=plus;
            }
        }
        plus = 1;
        for(int i=nums.length-1;i>=0;i--){
            if(i==nums.length-1){
                postPlus[i]=1;
            }else{
                plus*=nums[i+1];
                postPlus[i] = plus;
            }
        }
        int[] result = new int[nums.length];
        for(int i=0;i<result.length;i++){
            result[i]=prePlus[i]*postPlus[i];
        }
        return result;
    }
}
