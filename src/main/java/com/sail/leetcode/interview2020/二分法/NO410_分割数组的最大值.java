package com.sail.leetcode.interview2020.二分法;

/**
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 示例:
 *
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * 输出:
 * 18
 *
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO410_分割数组的最大值 {

    public static void main(String[] args) {
        NO410_分割数组的最大值 t = new NO410_分割数组的最大值();
        t.splitArray1(new int[]{5,2,4,1,3,6,0},4);
    }

    /**
     * 1ms 72.72%
     * 思路二 二分法，首先确认结果的范围，多个划分的里面的最大和值的最小值，肯定是max(nums) 与 sum(nums) 之间。
     * 就二分区间选择中间值mid作为最后的结果值，这个值在一次划分里肯定是子数组和的最大值，因此按照这个值，将nums按序分组，
     * 如果分出来的组数量大于m，在nums不变的情况下，说明这个值取小了，因此缩小区间范围为[mid,r]，如果分出来的组数量小于m，说明mid取大了，缩小区间范围为[l,mid]
     */
    private int res = Integer.MAX_VALUE;
    public int splitArray(int[] nums, int m) {
        long l = 0;
        long r = 0;
        for(int i=0;i<nums.length;i++){
            r += nums[i];
            l = Math.max(l,nums[i]);
        }

        while(l<r){
            long mid = (l+r)/2;
            long sum = 0;
            /**
             * 这里为什么要取1呢？其实是提前把最后一个区间加上了，当nums遍历完的时候其会遗留一个sum，不会进入if语句，就结束循环了
             */
            int cnt = 1;
            for(int num:nums){
                sum+=num;
                if(sum>mid){
                    sum = num;
                    cnt++;
                }
            }
            if(cnt>m){
                l = mid+1;
            }else{
                r = mid;
            }
        }
        return (int)l;
    }



    /**
     * dfs 超时
     * 大概思路就是将总和拆分为m个包，遍历nums依次往包里面填充，填充m个包的过程中，由一个包到下一个包的时候传递上一个包的总和，用于计算最大值
     * 当填充完m个包以后，比较这些分配方案里最大值的最小值
     */
    private int res1 = Integer.MAX_VALUE;
    public int splitArray1(int[] nums, int m) {
        long sum = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        long bag = sum/m;
        doSplitArray(nums,bag,0,m,0);
        return res1;
    }

    public void doSplitArray(int[] nums,long bag,int index,int m,long lastSum){
        if(m==0){
            if(index>=nums.length){
                res1 = (int)Math.min(res1,lastSum);
            }
            return ;
        }
        if(index>=nums.length){
            return ;
        }

        long sum = 0;
        if(m==1){
            while(index<nums.length){
                sum+=nums[index++];
            }
        }else{
            while(index<nums.length&&sum+nums[index]<=bag){
                sum+=nums[index++];
            }
        }
        if(sum>= res1){
            return ;
        }

        doSplitArray(nums,bag,index,m-1,Math.max(lastSum,sum));

        if(index<nums.length){
            doSplitArray(nums,bag,index+1,m-1,Math.max(lastSum,sum+nums[index]));
        }

    }
}
