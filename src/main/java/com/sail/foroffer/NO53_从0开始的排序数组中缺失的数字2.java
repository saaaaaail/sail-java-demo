package com.sail.foroffer;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *  
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO53_从0开始的排序数组中缺失的数字2 {

    /**
     * 考虑两个特殊情况，
     * 如果0索引位置不是0，那说明缺的就是0
     * 如果n-1位置就等于n-1，那说明缺的是n
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        if(nums[0]!=0){
            return 0;
        }
        if(nums[nums.length-1]==nums.length-1){
            return nums.length;
        }

        return missingNumber(nums,0,nums.length-1);
    }

    /**
     * 排序数组当然使用二分法去快速查找
     * 二分法有个注意的点就是左右指标l与r
     * 当确定了一个mid的时候 l必须等于mid+1
     * 而r 可以等于mid
     * 这是因为 int整型会将小数点省略吖，l如果不动很容易就死循环了
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public int missingNumber(int[] nums,int l,int r){
        if(l==r){
            if(l>0&&nums[l]-nums[l-1]==2){
                return nums[l]-1;
            }
            if(l<nums.length-1&&nums[l+1]-nums[l]==2){
                return nums[l]+1;
            }

            return nums.length;
        }
        if(r-l==1&&nums[r]-nums[l]==2){
            return nums[r]-1;
        }
        int mid = (l+r)/2;
        if(nums[mid]==mid){
            l=mid+1;
        }else{
            r=mid;
        }
        return missingNumber(nums,l,r);
    }
}
