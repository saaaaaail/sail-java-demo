package com.sail.leetcode.interview2020.数组;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO34_在排序数组中查找元素的第一个和最后一个位置 {

    private int[] result = new int[2];
    public int[] searchRange(int[] nums, int target) {

        result[0]=-1;
        result[1]=-1;
        if(nums==null||nums.length==0){
            return result;
        }
        doSearchRange(nums,0,nums.length-1,target);
        return result;
    }

    /**
     * 这一题先找左边界，再找右边界，二分法新解法，学到了没？
     * 如何找左边界，即在一个区间内，向右逼近 nums[mid]>=target
     * 找右边界，在一个区间内，向左逼近 nums[mid]<=target
     * @param nums
     * @param l
     * @param r
     * @param target
     */
    public void doSearchRange(int[] nums,int l,int r,int target){

        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid]>=target){
                r=mid;
            }else{
                l=mid+1;
            }
        }
        if(nums[l]!=target){
            return ;
        }
        result[0]=l;
        r = nums.length;
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid]<=target){
                l=mid+1;
            }else{
                r=mid;
            }
        }
        result[1]=l-1;

    }
}
