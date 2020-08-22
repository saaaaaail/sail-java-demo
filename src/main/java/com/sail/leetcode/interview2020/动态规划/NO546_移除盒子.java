package com.sail.leetcode.interview2020.动态规划;

/**
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。
 *
 *  
 *
 * 示例：
 *
 * 输入：boxes = [1,3,2,2,2,3,4,3,1]
 * 输出：23
 * 解释：
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 *  
 *
 * 提示：
 *
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-boxes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO546_移除盒子 {

    /**
     * 25ms 98.99%
     * 官方题解提供的思路
     * dp[l][r][k]表示从l到r区间的最大积分和 加上 r后面区间与r字符相同的字符数量k 的最大和
     *
     * 用f(l,r,k)表示移除区间 [l, r]加上该区间右边等于 boxes[r] 的 k 个元素组成的这个序列的最大积分
     * 例: 对于{6,3,6,5,6,6,6,6}序列l==0 r==4 k==3
     * 删除后面四个6 ，再删除前面的区间  获得积分 f(0,(4-1),0) + (3+1)*(3+1)
     * 上面只是一种策略，也可以不删后面四个6 继续向前遍历寻找与boxes[r]相同的字符，这时候找到了boxes[2]==boxes[4] 那么积分就等于 f(0,2,(3+1)) + f((2+1),(4-1),0)
     *
     * 思路链接  https://leetcode-cn.com/problems/remove-boxes/solution/yi-chu-he-zi-by-leetcode-solution/
     */
    int[][][] dp;
    public int removeBoxes(int[] boxes) {
        dp = new int[boxes.length][boxes.length][boxes.length+1];
        return f(boxes,0,boxes.length-1,0);
    }

    public int f(int[] boxes,int l,int r,int k){
        /**
         * k 计数不包括当前r的数量，是r右边区间与r相同的字符数量
         * 如果区间只剩最后一个元素，r右边区间的数量加上当前数 即k+1
         */
        if(l==r){
            return (k+1)*(k+1);
        }
        if(dp[l][r][k]!=0){
            return dp[l][r][k];
        }
        /**
         * 这里是贪心一下，因为要求最大，这里如果r前一个元素跟当前r相同，那么就直接k+1，区间r左移一位，积分的值肯定会更大
         */
        if(boxes[r-1]==boxes[r]){
            return f(boxes,l,r-1,k+1);
        }
        /**
         * 如果上面不满足，说明连续的r已经向左延申到头了，下一个元素不再相等即不再连续，那么下面就考虑l~r区间里有没有与r相同的字符
         *
         * 首先考虑的一种情况 就是不再考虑l~r里相同的字符，直接将r以及r右边的连续数删掉，计算出积分
         */
        int res = f(boxes,l,r-1,0) + (k+1)*(k+1);

        /**
         * 从右向左遍历区间，由于r的前一个数不连续了，直接从r-2开始，如果l~r里找到一个i有boxes[i]等于boxes[r]，那就将r的连续取到i，
         * 如何让i与r连续呢，将i与r中间的数组先计算出积分然后删掉即先计算f(i+1,r-1,0)积分和
         * 那么这样取得积分和为 res = f(l,i,k+1) + f(i+1,r-1,0) 前者就是继续计算l到i的i字符数量为k+1的最大积分和， 后者就是删掉的i~r区间的数的最大积分和
         *
         * 思路就清楚了，找出每一个i 计算res，取res最大的积分和就是当前l~r区间的右边连续k个数的最大积分和
         *
         * 最后用缓存数组存一下
         */
        for(int i=r-2;i>=l;i--){
            if(boxes[i]==boxes[r]){
                res = Math.max(res,f(boxes,l,i,k+1)+f(boxes,i+1,r-1,0));
            }
        }
        dp[l][r][k] = res;
        return res;

    }
}
