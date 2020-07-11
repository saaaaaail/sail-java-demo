package com.sail.leetcode.interview2020.动态规划;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO416_分割等和集 {

    /**
     * 背包问题系列目前NO322/NO416/NO518，笔记都是类似的，一个target作为背包，一个数组nums作为填充值
     * 那么首先先写二维dp数组就是d[i][j] 表示的是 nums中0~i的填充数，能占满j大小的背包
     *
     * 转移方程都是类似的，虽然不完全一样
     * 0/1背包问题是 选与不选的问题 即 不选nums的第i个填充的话，d[i-1][j]表示不选第i个num，那么状态与i-1个nums占满j的情况一致
     *                               选nums的第i个填充，d[i-1][j-num[i]]（这要保证j大于nums[i]），状态就与选了i-1个数，第i个数与j刚好差nums[i]大小的情况一致
     * 0/1背包转成一维以后 一般以nums的i作为外层循环，背包作为内层循环，而且当减到一维时，背包的循环要逆序进行，避免使用到本轮修改过的d[i]
     * 0/1背包的二维写法不能使用 for(int j=bag;j>=stones[i];j--)(这是一维的写法，一维的话，上一轮的值直接覆盖了当前轮的值，因此没有问题)
     * 这种循环写法，因为会漏掉一种情况就是
     * 当bag小于stones[i]的时候 这种循环方式直接就跳出去了，实际上
     * 当j<stones[i]时，还要将上一轮的d[i-1][j] 这种情况赋值给 d[i][j]
     *
     *
     *
     *
     * 完全背包问题 就是不选与选几个的问题 以零钱兑换1为例 d[i][j] = 不选d[i-1][j]  选某个面值的硬币d[i-1][j-nums[i]]+1 的最小值
     * 完全背包问题里面 当硬币种类在外圈循环，金额大小在内圈循环的话，是关于硬币种类的组合问题
     *                 当硬币种类在内圈循环，金额大小在外圈循环的话，是硬币种类的排列问题
     * 完全背包问题转成一维度的时候的背包大小要正序遍历，与0/1背包相反
     * @param args
     */
    public static void main(String[] args) {
        boolean b = canPartition2(new int[]{1, 1, 5, 5, 10});
        System.out.println(b);
    }

    /**
     * 注意到d[i][j]都是通过d[i-1][..]转移过来的，i-1的数据不会再使用，那么直接覆盖也是可以的。采用一维数组来存
     * 采用一维数组时，求target的循环必须逆序来写 从后向前
     */
    public static boolean canPartition2(int[] nums) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if(sum%2!=0){
            return false;
        }
        int  target = sum/2;
        return doCanPartition2(nums,target);
    }

    //d[i][j]表示数0~i个数组成j的和
    //
    //
    //
    public static boolean doCanPartition2(int[] nums,int target){
        boolean[] d= new boolean[target+1];
        d[0] = true;
        for(int j=0;j<nums.length;j++){
            /**
             * 这里之所以要逆序是因为，顺序的时候求d[i]的时候会使用到本轮求出来的值，这是不对的，你只能使用上一轮求得的值
             * 逆序的话，使用的时候不会用到本轮修改过的d[i]
             */
            for(int i=target;i>=nums[j];i--){

                    d[i] = d[i] || d[i-nums[j]];

            }
        }
        return d[target];
    }

    /**
     * 这一题是0、1背包问题
     * 即选不选的问题
     * 对于二维的d[i][j]数组一般把i的循环写到外面
     * 一板用二维数组来求 d[i][j]表示0~i的数能否组成j的和
     * 状态转移方程就有 不选第i个数，那么状态与i-1个数组成j的和是一致的 d[i-1][j]
     * 选第i个数，那么状态与刚好相差nums[i]的和的数组是一致的即 d[i-1][j-nums[i]]
     * 注意到j==nums[i]的时候是表示一个数沾满了j，所以可知d[i][0]=true;
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if(sum%2!=0){
            return false;
        }
        int  target = sum/2;
        return doCanPartition(nums,target);
    }

    //d[i][j]表示数0~i个数组成j的和
    //
    //
    //
    public boolean doCanPartition(int[] nums,int target){
        boolean[][] d= new boolean[nums.length][target+1];
        for(int i=0;i<nums.length;i++){
            d[i][0]=true;
        }

        for(int i=1;i<=target;i++){
            for(int j=1;j<nums.length;j++){
                if(i>=nums[j]){
                    d[j][i] = d[j-1][i] || d[j-1][i-nums[j]];
                }
            }
        }
        return d[nums.length-1][target];
    }
}
