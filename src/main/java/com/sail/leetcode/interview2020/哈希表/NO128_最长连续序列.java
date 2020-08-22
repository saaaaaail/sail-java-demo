package com.sail.leetcode.interview2020.哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO128_最长连续序列 {

    /**
     * 用map存每个数作为连续序列最小值的长度
     * map初始化数组每一个数作为连续长度的起点为 1
     * 然后对于数组里的一个数 num，如果map.get(num)==1，还不能直接返回，
     * 还要递归向后找连续的数num+1的长度，num+1也要继续向下找num+2的长度、
     * 直到map里的返回值为0，返回上一层，加上当前层的长度，并更新当前层num的最长连续序列的长度到map里
     *
     * 如果map里查找一个数num不是0，也不是1，说明在其他查找路径里被更新过了，直接返回
     *
     */
    Map<Integer,Integer> map = new HashMap<>();
    public int longestConsecutive(int[] nums) {
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],1);
        }
        int ans = 0;
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            /**
             * 递归获得以num为起点的连续序列的长度
             */
            int len = getLength(num);
            ans = Math.max(ans,len);
        }
        return ans;
    }

    public int getLength(int num){
        if(!map.containsKey(num)){
            return 0;
        }
        int len = map.get(num);
        if(len==1){
            /**
             * 递归返回的时候，顺便把以当前层的数作为起点的长度保存到map里
             */
            int next = getLength(num+1);
            map.put(num,next+len);
            return next+len;
        }
        return len;
    }
}
