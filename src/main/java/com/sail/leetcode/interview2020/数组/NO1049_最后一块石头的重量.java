package com.sail.leetcode.interview2020.数组;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 *
 *  
 *
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO1049_最后一块石头的重量 {

    public static void main(String[] args) {
        Integer[] t = new Integer[10];
        LinkedList<Integer> list = new LinkedList<>();
        Collections.addAll(list,t);

    }

    /**
     * 这是一道0/1背包问题，可以理解为0/1背包问题二维数组的标准写法
     * 首先要保证，所有的石头都能取到，包括边界值i=0的石头也不能丢
     * 这里new d[stones.length+1][bag+1] d数组给stones多留一位
     * 让stones能从1出发，但是从stones取石头的时候，要取i-1索引的石头，这样stones里i=0的石头也能取到
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        if(stones==null||stones.length==0){
            return 0;
        }
        int sum = 0;
        for(int i=0;i<stones.length;i++){
            sum+=stones[i];
        }
        int bag =sum/2;


        int[][] d = new int[stones.length+1][bag+1];
        for(int i=1;i<=stones.length;i++){
            for(int j=bag;j>=0;j--){
                if(j>=stones[i-1]){
                    //第一个注意点，i在d里面是i，在stones里面是i-1，保证i=1的时候stone[0]也能取到。
                    d[i][j] = Math.max(d[i-1][j],d[i-1][j-stones[i-1]]+stones[i-1]);
                }else{
                    //第二个注意点，在二维写法里这种情况不能漏掉，不能使用一维数组的写法，
                    // 例如 for(int j=bag;j>=stones[i];j--){...}
                    //以石头为边界值会漏掉下面的这种情况
                    d[i][j] = d[i-1][j];
                }
            }
        }
        return sum-2*d[stones.length][bag];
    }

}
