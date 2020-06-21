package com.sail.foroffer;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO53_在排序数组中查找数字1 {

    /**
     * 思路:
     * 二分
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums.length==0){
            return 0;
        }
        int count=0;
        int l=0;
        int r = nums.length-1;
        int mid = (l+r)/2;
        while(l<r){
            if(nums[mid]>target){
                r = mid-1;
            }else if(nums[mid]<target){
                l=mid+1;
            }else{
                break;
            }
            mid = (l+r)/2;
        }
        if(nums[mid]==target){
            int tmp = mid;
            while(tmp<nums.length&&nums[tmp]==target){
                count++;
                tmp++;
            }
            tmp = mid-1;
            while(tmp>=0&&nums[tmp]==target){
                count++;
                tmp--;
            }
        }

        return count;
    }
}
