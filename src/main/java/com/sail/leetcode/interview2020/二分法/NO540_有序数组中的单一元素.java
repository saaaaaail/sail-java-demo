package com.sail.leetcode.interview2020.二分法;

/**
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO540_有序数组中的单一元素 {

    /**
     * 0ms 100%
     * 注意时间复杂度为 O(logn) 位运算就淘汰了，要使用二分法
     * 二分法寻找的是这个单一的数在哪个区间，因为如果没有单个数，
     * 正常的重复二数排列是第一个数位于偶数索引，第二个数位于奇数索引
     * 如果往前面插入了一个单个数a，那么a后面的重复二数的第一个数就位于奇数索引了，第二个数位于偶数索引了
     * 以此来进行二分
     * 如果直接找到一个点mid，前面与后面的数都不相同则认为是那个唯一的数
     * 然后判断当前数cur索引是偶数的话，
     *      如果cur与后面的数是相同一对的数，说明唯一的数不在cur的前面，在他的后面。
     *      如果cur与前面的数是相同一对的数，说明唯一的数在cur的前面。
     * 如果当前数cur索引是奇数的话，
     *      如果cur与后面的数相同的一对的数，说明唯一的数在cur前面。
     *      如果cur与后面的数是相同一对的数，说明唯一的数不在cur的前面，在他的后面。
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {

        int l = 0;
        int r = nums.length-1;
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid]!=nums[mid-1]&&nums[mid]!=nums[mid+1]){
                return nums[mid];
            }
            if(mid%2==0){
                if(nums[mid]==nums[mid+1]){
                    l = mid+2;
                }else{
                    r = mid;
                }
            }else{
                if(nums[mid]==nums[mid+1]){
                    r = mid-1;
                }else{
                    l = mid+1;
                }
            }
        }
        return nums[l];
    }
}
