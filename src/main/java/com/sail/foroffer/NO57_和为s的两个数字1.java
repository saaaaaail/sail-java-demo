package com.sail.foroffer;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO57_和为s的两个数字1 {

    /**
     * 1ms 99.83%
     * 思路就是 左右指针夹逼
     * target减去左边指针的数得到的结果，是目前能取到的最大的右值，如果目前右指针得数比他大就左移
     * target减去右边指针的数得到的结果，是目前能取到的最小的左值，如果目前左边指针的数比他还小就得右移
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;
        while(l<r){
            while(nums[r]>target&&r>0){
                r--;
            }
            if(l>r){
                break;
            }
            if(nums[l]+nums[r]==target){
                return new int[]{nums[l],nums[r]};
            }
            int needRight = target-nums[l];
            int needLeft = target-nums[r];
            while(nums[r]>needRight){
                r--;
            }
            while(nums[l]<needLeft){
                l++;
            }
        }
        return new int[2];
    }
}
