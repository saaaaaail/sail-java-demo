package com.sail.foroffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO57_和为s的连续正数序列 {
    public static void main(String[] args) {

    }

    /**
     * 5ms 41.4%
     * 求连续和 自然想到了 滑动窗口的思路  设立一个窗口l 到 r
     * 如果窗口里面元素的和小于target ， r就右移 sum就加上新的元素
     * 如果加上新的元素恰好等于target就记录一下
     * 直到sum+d[r]会大于target的时候  sum = sum-d[l]同时l右移
     * 循环直到r大于target/2+1 跳出
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int[] d = new int[target+1];
        /**
         * 注意噢！！！！！！！！！！！！！！！！！！！！！！！！！！
         * List可以用数组做泛型，，才知道吖你  淦
         */
        List<int[]> result = new ArrayList<>();

        for(int i=1;i<=target;i++){
            d[i] = i;
        }
        int l=1;
        int r=1;
        int sum = 0;
        int posi = 0;

        while(l<=r&&r<=target/2+1){
            while(r<=target/2+1&&sum+d[r]<target){
                sum+=d[r++];
            }
            if(sum+d[r]==target){
                /**
                 * 直接添加超方便
                 */
                result.add( Arrays.copyOfRange(d,l,r+1));
            }
            sum-=d[l++];
        }

        /**
         * 直接转二维数组超方便
         */
        return result.toArray(new int[0][]);
    }
}
