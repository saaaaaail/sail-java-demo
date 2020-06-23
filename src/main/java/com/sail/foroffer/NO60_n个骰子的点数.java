package com.sail.foroffer;

import java.util.Arrays;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 *  
 *
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 *
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *  
 *
 * 限制：
 *
 * 1 <= n <= 11
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO60_n个骰子的点数 {

    /**
     *    这居然是个动态规划问题??
     *     d[i][j]表示扔第i个骰子 数字和j出现的次数
     *     状态转移方程d[i][j] = sum(d[i-1][j-1]+d[i-1][j-2]+d[i-1][j-3]+d[i-1][j-4]+d[i-1][j-5]+d[i-1][j-6]);
     *     的意思是
     *     扔第i个骰子，数字和j出现的次数等于扔第i-1个骰子出现j-1，j-2，j-3，j-4，j-5，j-6的次数和
     *     初始化d[1][1]=1;d[1][2]=1;;d[1][3]=1;;d[1][4]=1;;d[1][5]=1;;d[1][6]=1;
     *     除了这个转移方程以及初始化以外还要注意的是
     *     1、数字和j一定是大于等于i的，如果扔出了i个1就是最小值j
     *     2、j-k计算的是i-1的数字和，因此j-k也要大于等于i-1
     *     所以最后的结果集是从n出发到 6*n
     * @param n
     * @return
     */
    public double[] twoSum(int n) {
        int[][] d = new int[n+1][6*n+1];
        for(int i=1;i<=6;i++){
            d[1][i]=1;
        }

        for(int i=2;i<=n;i++){
            for(int j=i;j<=6*i;j++){
                for(int k=1;k<=6;k++){
                    if(j-k>=i-1){
                        d[i][j] +=d[i-1][j-k];
                    }
                }
            }
        }
        double[] result = new double[6*n];
        for(int i=n-1;i<result.length;i++){
            result[i]=d[n][i+1]/Math.pow(6,n);;
        }
        return Arrays.copyOfRange(result,n-1,result.length);
    }
}
