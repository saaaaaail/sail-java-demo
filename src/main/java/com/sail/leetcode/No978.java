package com.sail.leetcode;

/**
 * @program: JavaDemo
 * @description: 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 *
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 返回 A 的最大湍流子数组的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 *
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 *
 * 输入：[100]
 * 输出：1
 * @author: sail
 * @create: 2019/04/29 14:57
 */

public class No978 {
    public int maxTurbulenceSize(int[] A) {
        if(A.length==0){return 0;}
        if(A.length==1){return 1;}
        int[] d = new int[A.length];
        int[] flag = new int[A.length];
        d[0]=1;flag[0]=0;
        int max=0;
        int tf=0;
        for(int i=1;i<A.length;i++){
            if(A[i-1]<A[i]){
                tf=-1;
            }else if(A[i-1]>A[i]){
                tf=1;
            }else{
                tf=0;
            }
            flag[i]=tf;

            if(tf*flag[i-1]>0||flag[i-1]==0&&tf>0){
                d[i]=2;
            }else if(tf*flag[i-1]<0||flag[i-1]==0&&tf<0){
                d[i]=d[i-1]+1;
            }else if(tf==0){
                d[i]=1;
            }
            if(d[i]>max){
                max=d[i];
            }

        }
        return max;
    }
}
