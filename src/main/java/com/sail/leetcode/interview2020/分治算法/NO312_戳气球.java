package com.sail.leetcode.interview2020.分治算法;

/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO312_戳气球 {

    /**
     * 采用分治法可解 11ms 35%
     * 这一题如果单纯用dfs，取每一项比较，时间复杂度为O(n!)，炸裂
     * 因此采用分治法来处理 思想是倒退着想
     * 假设现在是要计算i~j的元素的最大值，假设最后一个戳破的气球是k 属于i~j
     * 那么在戳破k之前呢，i~k 与 k~j 的最大硬币数都已经算出来了
     * 假设f(i,j)表示i~j的最大的硬币数
     * 那么f(i,k)就表示i~k的最大硬币数
     * f(k,j)表示k~j的最大硬币数
     * f(i,j)=f(i,k)+f(k,j)+最后戳破k的硬币数即nums[i]*nums[k]*nums[j]
     *
     * 因为是要求最大值，要遍历i~j的每一个数当作k，取最大硬币数的k值
     * f(i,j)=max(f(i,j),f(i,k)+f(k,j)+nums[i]*nums[k]*nums[j])
     *
     * 那么递归的终止条件是j-i等于1，因为中间没有间隔选k出来了，直接返回0
     *
     * 然后还要加个缓存数组，对于已经求过的i~j的最大硬币数要暂存起来，避免重复计算
     *
     */
    private int[][] d;
    public int maxCoins(int[] nums) {

        if(nums==null||nums.length==0){
            return 0;
        }
        int[] nums2 = new int[nums.length+2];
        System.arraycopy(nums,0,nums2,1,nums.length);
        nums2[0]=1;
        nums2[nums2.length-1]=1;
        d = new int[nums2.length][nums2.length];
        return doMaxCoins(nums2,0,nums2.length-1);
    }

    public int doMaxCoins(int[] nums,int l,int r){
        if(r-l==1){
            return 0;
        }
        if(d[l][r]!=0){
            return d[l][r];
        }
        int ans = Integer.MIN_VALUE;
        for(int k = l+1;k<r;k++){
            ans = Math.max(ans,doMaxCoins(nums,l,k)+doMaxCoins(nums,k,r)+nums[l]*nums[k]*nums[r]);
        }

        d[l][r] = ans;
        return ans;
    }
}
