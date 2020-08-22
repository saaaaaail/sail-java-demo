package com.sail.leetcode.interview2020.二分法;

/**
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * 示例 2：
 *
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 * 示例 3：
 *
 * 输入: piles = [30,11,23,4,20], H = 6
 * 输出: 23
 *  
 *
 * 提示：
 *
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO875_爱吃香蕉的珂珂 {

    public static void main(String[] args) {
        NO875_爱吃香蕉的珂珂 t = new NO875_爱吃香蕉的珂珂();
        t.minEatingSpeed(new int[]{332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184},823855818);
    }

    /**
     * 35ms 16.38%
     * 思路 二分法 首先确认 K的最大值是piles的最大值，因为在H小时内，一个小时吃完一堆即可，
     * 即便我 K远大于piles的最大值但是，我一次还是只能吃一堆，所以最大值就是piles的最大值
     * 最小值 使用点数学其实也可以算出来，但是从1开始二分也可以
     *
     * 取1~K的二分值，为一个小时能吃的数量mid，然后遍历一遍数组，计算以mid为速度，需要小时 TH， 如果TH大于H，则认为mid取小了，
     * 如果TH 小于 H 说明mid取大了，一次取多了。
     * @param piles
     * @param H
     * @return
     */
    public int minEatingSpeed(int[] piles, int H) {
        long l = 1;
        long r = 0;
        for(int i=0;i<piles.length;i++){
            r = Math.max(r,piles[i]);
        }
        while(l<r){
            long mid = (l+r)/2;

            int cnt = 0;
            for(int pile:piles){
                long lpile = pile;
                /**
                 * 用ceil计算不小于它的最小整数，求和计算以mid为吃的速度一共花费的时间
                 */
                double dcnt = Math.ceil((double)lpile/(double)mid);
                cnt += (int)dcnt;
            }
            if(cnt>H){
                l = mid+1;
            }else{
                r = mid;
            }
        }
        return (int)l;
    }
}
