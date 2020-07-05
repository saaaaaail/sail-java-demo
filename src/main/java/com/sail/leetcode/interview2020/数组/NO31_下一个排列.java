package com.sail.leetcode.interview2020.数组;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO31_下一个排列 {

    public static void main(String[] args) {
        int[] nums = new int[]{1 ,2,3,6,4,5};
        Arrays.sort(nums,3,6);
        System.out.println(nums
        );
        for(int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }


    /**
     * 思路是慢慢摸索出来的，对于排列的下一个直接后继排列，
     * 是从数组某位往前寻找，如果r比前一个数小就r--，直到一个数r，这个r比前一个数r-1要大，
     * 然后再次从数组末尾到r遍历 ，寻找到一个比r-1要大的最小数min
     * 将min 与r-1交换 然后对r到末尾从小到大排序，得到的就是直接后继排列
     *
     * 考虑特殊情况就是一直到数组头也没有找到r-1<r 即此时r==0
     * 对全数组排序
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int r = nums.length-1;
        while(r>0&&nums[r]<=nums[r-1]){
            r--;
        }
        if(r==0){
            sort(nums,r,nums.length);
        }else{
            int l = nums.length-1;
            while(l>r&&nums[l]<=nums[r-1]){
                l--;
            }
            swap(nums,r-1,l);
            sort(nums,r,nums.length);
        }
    }
    public void swap(int[] nums,int l,int r){
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    public void sort(int[] nums,int l,int r){
        Arrays.sort(nums,l,r);
    }
}
