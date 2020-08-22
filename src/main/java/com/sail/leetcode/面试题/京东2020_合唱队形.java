package com.sail.leetcode.面试题;

/**
 * 合唱队N名学生，站成一排从左到右编号为1到N，其中编号为i的学生身高为Hi，现在将这些学生分成若干组(同一组学生编号连续)
 * ，并让每组学生从左到右按身高从低到高进行排序，使得最后所有学生同样满足从左到右身高从低到高（中间位置可以等高），
 * 那么最多能将这些学生分成多少组？
 */
public class 京东2020_合唱队形 {

    /**
     * leetcode NO768 一样的题目
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 构建两个数组，
     * 一个preMax[i]，存i前面不包括i的最大值
     * 一个postMin[i]，存i后面不包括i的最小值
     * 然后进行第三轮循环
     * 双指针表示一个区间并 移动，并且遍历的时候记录区间的最大最小值
     * 如果区间里最小值也大于左边界的最大值，区间最大值也小于右边界的最小值，则认为这个区间是有效的，结果++，初始化下一个区间，最大最小值都初始化
     * 而且找最多的这样的区间，使用贪心法即可，有区间就记录
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int[] postMin = new int[arr.length];
        int[] preMax = new int[arr.length];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            preMax[i] = max;
            max = Math.max(max,arr[i]);
        }
        for(int i=arr.length-1;i>=0;i--){
            postMin[i] = min;
            min = Math.min(min,arr[i]);
        }

        int l =0;
        int ans = 0;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;){
            max = Math.max(max,arr[i]);
            min = Math.min(min,arr[i]);
            if(arr[l]>=preMax[l]&&arr[i]<=postMin[i]&&max<=postMin[i]&&min>=preMax[l]){
                ans++;
                l = ++i;
                max = Integer.MIN_VALUE;
                min = Integer.MAX_VALUE;
            }else{
                ++i;
            }
        }
        return ans;
    }

}
