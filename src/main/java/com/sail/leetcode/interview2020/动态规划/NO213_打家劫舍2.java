package com.sail.leetcode.interview2020.动态规划;

import java.util.Arrays;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO213_打家劫舍2 {

    //d[i]表示0~i的最大金额
    //d[i] = Math.max(d[i-1],d[i-2]+nums[i]);
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] d = new int[nums.length+1];
        //抢第一个
        d[1] = nums[0];
        int ans1 = d[1];
        //这里就不抢最后一个
        for(int i=2;i<nums.length;i++){
            d[i] = Math.max(d[i-1],d[i-2]+nums[i-1]);
            ans1 = Math.max(ans1,d[i]);
        }

        Arrays.fill(d,0);
        //不抢第一个
        d[1]=0;
        int ans2 = d[1];
        //这里就可以抢最后一个，但是也不一定抢，如果金额和不是最大的话
        for(int i=2;i<=nums.length;i++){
            d[i] = Math.max(d[i-1],d[i-2]+nums[i-1]);
            ans2 = Math.max(ans2,d[i]);
        }
        return Math.max(ans1,ans2);


    }
}
