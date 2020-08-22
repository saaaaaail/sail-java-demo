package com.sail.leetcode.面试题;

import java.util.Scanner;

/**
 * NN位同学站成一排，音乐老师要请其中的(N-KN−K)位同学出列，使得剩下的KK位同学排成合唱队形。
 *
 * 合唱队形是指这样的一种队形：设K位同学从左到右依次编号为1,2,…,K1,2,…,K，他们的身高分别为T_1,T_2,…,T_KT1​,T2​,…,TK​， 则他们的身高满足T_1<...<T_i>T_{i+1}>…>T_K(1 \le i \le K)T1​<...<Ti​>Ti+1​>…>TK​(1≤i≤K)。
 *
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 */
public class 牛客网_合唱队形 {


    /**
     * 这一题呢就是 最长子序列问题，不过呢？他要找先递增再递减的，
     * 那么我们可以先从左到右计算递增的最长子序列，并暂存每个以i结尾的最大值dpre[i]
     * 然后计算从右到左递增的最长子序列（注意这里必须从右往左依次计算，因为每个位置的值都有用，不能从左往右计算递减序列），并暂存每个以i结尾的最大值dpost[i]
     * 然后遍历 计算dpre[i]+dpost[i]的最大值，这就是那个先递增后递减序列的最大值
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.nextLine());
        String tmp = sc.nextLine();

        String[] data = tmp.split(" ");
        int[] nums = new int[total];
        for(int i=0;i<total;i++){
            nums[i]=Integer.parseInt(data[i]);
        }
        int[] dpre = new int[data.length];
        int[] dpost = new int[data.length];

        //d[i] =
        dpre[0]=1;
        for(int i=1;i<total;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(nums[i]>=nums[j]){
                    max = Math.max(max,dpre[j]);
                }
            }
            dpre[i] = max+1;
        }

        dpost[total-1]=1;
        for(int i=total-2;i>=0;i--){
            int max = 0;
            for(int j=total-1;j>i;j--){
                if(nums[i]>=nums[j]){
                    max = Math.max(max,dpost[j]);
                }
            }
            dpost[i]= max+1;
        }

        int sum = 0;
        for(int i=0;i<total;i++){
            sum = Math.max(sum,dpre[i]+dpost[i]);
        }
        //这里加个1是因为中间的数计算了两遍
        System.out.println(total-sum+1);
    }
}
