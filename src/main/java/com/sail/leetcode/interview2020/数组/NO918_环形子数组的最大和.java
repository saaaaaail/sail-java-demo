package com.sail.leetcode.interview2020.数组;

/**
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
 *
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，而当 i >= 0 时 C[i+A.length] = C[i]）
 *
 * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 *
 * 输入：[5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 *
 * 输入：[3,-1,2,-1]
 * 输出：4
 * 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * 示例 4：
 *
 * 输入：[3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * 示例 5：
 *
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 *  
 *
 * 提示：
 *
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO918_环形子数组的最大和 {

    /**
     * 要求最大和值分为两种情况
     * 一种是最大值不跨环形边界，直接计算即可
     * 一种是最大值跨环形边界，这时候怎么办？
     * 如果最大和值跨环形边界，那么最小和值一定不跨界
     * 可以计算不跨界的最小和值，然后用所有数的和减去最小和值即为跨界的最大值
     * 以上两种情况都计算一遍比较大小即可。
     * 还要排除一种情况，就是全为负值的情况下计算出来的最小和值是等于所有值之和
     * 然后相减为0.这是不对的，至少要选择一个值，所以这时候就直接返回最大和值即可。
     * @param A
     * @return
     */
    public int maxSubarraySumCircular(int[] A) {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int S = 0;
        for(int i=0;i<A.length;i++){
            S += A[i];
        }
        int sum = 0;
        for(int i=0;i<A.length;i++){
            if(sum<0){
                sum = A[i];
            }else{
                sum += A[i];
            }
            max = Math.max(max,sum);
        }

        sum = 0;
        for(int i=0;i<A.length;i++){
            if(sum>0){
                sum = A[i];
            }else{
                sum += A[i];
            }
            min = Math.min(min,sum);
        }
        if(sum==S&&S<0){
            return max;
        }
        return Math.max(max,S-min);
    }
}
