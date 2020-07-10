package com.sail.leetcode.interview2020.数组;

/**
 *假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO33_搜索旋转排序数组 {

    public int search(int[] nums, int target) {
        if(nums==null||nums.length==0){
            return -1;
        }
        int l=0;
        int r = nums.length-1;
        if(target>nums[r]&&target<nums[l]){
            return -1;
        }
        return doSearch(nums,l,r,target);

    }

    /**
     *  思路就是二分，一边是有序的，一边是无序的，
     *  判断target与有序的边界值，如果target不再有序那一边，那就在无序那一边，
     *  在无序区间里的话继续递归
     *  在有序区间里就二分法查找 二分法查找临界值不需要考虑，始终是左边等于mid+1就行
     *
     *  mid作为切分有序和无序的临界点，应该把mid划分到有序这一边，因为有可能查找值就是mid，
     *  如果按左右来判断是否包含mid，有可能最后旋转了有序区间但是把mid排除到了无序那一边，然后就查不到
     * @param nums
     * @param l
     * @param r
     * @param target
     * @return
     */
    public int doSearch(int[] nums,int l,int r,int target){
        if(nums[l]<=nums[r]){
            while(l<r){
                int mid = (l+r)/2;
                if(nums[mid]==target){
                    return mid;
                }else if(nums[mid]>target){
                    r = mid;
                }else{
                    l = mid+1;
                }
            }
            if(nums[l]==target){
                return l;
            }
        }else{
            int mid = (l+r)/2;
            if(nums[l]<=nums[mid]){
                if(target>nums[mid]||target<nums[l]){
                    return doSearch(nums,mid+1,r,target);
                }else{
                    return doSearch(nums,l,mid,target);
                }
            }else if(nums[mid]<=nums[r]){
                if(target<nums[mid]||target>nums[r]){
                    return doSearch(nums,l,mid-1,target);
                }else{
                    return doSearch(nums,mid,r,target);
                }
            }
        }
        return -1;
    }
}
