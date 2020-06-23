package com.sail.foroffer;

import java.util.Arrays;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: True
 *  
 *
 * 示例 2:
 *
 * 输入: [0,0,1,2,5]
 * 输出: True
 *  
 *
 * 限制：
 *
 * 数组长度为 5 
 *
 * 数组的数取值为 [0, 13] .
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO61_扑克牌中的顺子 {

    /**
     * 1ms 87.21%
     * 思路很简单
     * 1、首先nums排序
     * 2、用any记录大小王的张数，l为遍历指针
     * 3、首先判断数组前面有多少张0，any记录一下
     * 4、然后直到第一张不是0的l移动到这儿，判断l+1位置的数是不是刚好比l位置的数大一，如果是l++，继续循环
     * 5、如果跳出循环了判断是不是l走完了，走完了就直接break出循环
     * 6、如果没有走完，是l+1的数比l的数大不止1，就判断有没有any，没有any，则不连续false
     * 7、如果有any，any--，l位置的数+1，继续判断l位置的数与l+1的数的差值
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        if(nums==null || nums.length==0){
            return true;
        }
        Arrays.sort(nums);
        int any=0;
        int l=0;

        while(l<nums.length){
            while(nums[l]==0){
                l++;
                any++;
            }
            while(l+1<nums.length&&nums[l+1]-nums[l]==1){
                l++;
            }
            if(l+1>=nums.length){
                break;
            }
            if(any==0){
                return false;
            }
            nums[l]++;
            any--;

        }
        return true;
    }

}
