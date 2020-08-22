package com.sail.leetcode;

import javax.jnlp.IntegrationService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定一个整数数组 nums ，小李想将 nums 切割成若干个非空子数组，使得每个子数组最左边的数和最右边的数的最大公约数大于 1 。为了减少他的工作量，请求出最少可以切成多少个子数组。
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,3,2,3,3]
 *
 * 输出：2
 *
 * 解释：最优切割为 [2,3,3,2] 和 [3,3] 。第一个子数组头尾数字的最大公约数为 2 ，第二个子数组头尾数字的最大公约数为 3 。
 *
 * 示例 2：
 *
 * 输入：nums = [2,3,5,7]
 *
 * 输出：4
 *
 * 解释：只有一种可行的切割：[2], [3], [5], [7]
 *
 * 限制：
 *
 * 1 <= nums.length <= 10^5
 * 2 <= nums[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qie-fen-shu-zu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LCP14_切分数组 {

    public static void main(String[] args) {
        LCP14_切分数组 t = new LCP14_切分数组();

        int r = t.splitArray(new int[]{2, 3, 3, 2, 3, 3});
        System.out.println(r);
    }

    //d[i]表示0~i的数组拆分的最小子数组数量  超时了，
    //取0~i中的一个数j，有nums[i]与nums[j]的最大公约数大于1，则d[i] = min(d[j-1] + 1) 对于不同的j要取最小值 时间复杂度O(N2)
    //可以优化的点在
    // 1、j不遍历所有的值，只遍历有最大公约数的值，
    // 2、
    public int splitArray(int[] nums) {
        int[] d = new int[nums.length+1];

        d[0]=0;
        for(int i=1;i<=nums.length;i++){
            d[i] = Integer.MAX_VALUE;
            int num = nums[i-1];
            for(int j=1;j<=i;j++){
                if(hasSamePrime(nums[j-1],num)){
                    d[i] = Math.min(d[i],d[j-1]+1);
                }
            }
        }
        return d[nums.length];
    }

    public boolean hasSamePrime(int x,int y){
        if(x<y){
            int tmp =x;
            x = y;
            y=tmp;
        }
        while(x%y!=0){
            x = x%y;
            if(x<y){
                int tmp =x;
                x = y;
                y=tmp;
            }
        }
        return y>1;
    }
}
