package com.sail.leetcode.interview2020.数组;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *  
 *
 * 提示：
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO41_缺失的第一个正数 {
    /**
     *  1ms 87.96%
     *  注意O(n)时间复杂度，以及常数大小空间
     *  那么就可以使用数组自身的性质，nums[i]==i，对应索引位置的数是否等于自身
     *  而本题求正数，所以让数组从1开始，即nums[0]=1 nums[1]=2，，，nums[i]=i+1
     *  遍历一遍数组，碰到nums[i]!=i+1时候，假设nums[i]==j的 那么这个j应该放到nums[j-1]才对，所以交换一下nums[j-1]与nums[i]的值
     *  结束后可以认为所有数都在其正确的位置上，从头遍历缺失的数就是最小的正数
     *
     *
     */
    public int firstMissingPositive(int[] nums) {
        if(nums.length==0){
            return 1;
        }
        int tmp = 0;
        for(int i=0;i<nums.length;i++){
            tmp = i;
            //nums[i]大于0小于长度才能放，并且nums[tmp]!=tmp+1
            while(nums[tmp]>0&&nums[tmp]<nums.length&&nums[tmp]!=tmp+1){
                //获得nums[i]处的数是多少
                int num = nums[tmp];
                //如果两个位置的数相等，就没必要交换了，不然死循环了
                if(nums[num-1]==nums[tmp]){
                    break;
                }
                //交换一下，数num应当位于num-1的索引位置，把num安顿好。交换之后再次判断位于tmp的数是否在其对应位置，然后再次交换放到对应位置
                swap(nums,tmp,num-1);
            }
        }
        /**
         * 上面的循环结束以后所有在1到数组长度的数 都应该位于其对应位置，即nums[i]=i+1的状态
         */
        for(int i=0;i<nums.length;i++){
            /**
             * 遍历一遍数组，如果0索引不是1，1索引不是2，2索引不是3.....就是缺失的数
             */
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }

    public void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j]=tmp;
    }
}
