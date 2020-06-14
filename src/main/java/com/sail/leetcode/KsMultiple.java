package com.sail.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * K 的倍数
 * 题目描述
 *  序列中任意个连续的元素组成的子序列称为该序列的子串。
 * 现在给你一个序列P和一个整数K，询问元素和是K的倍数的子串的最大长度。
 * 比如序列【1,2,3,4,5】，给定的整数K为 5，其中满足条件的子串为{5}、{2,3}、{1,2,3,4}、{1,2,3,4,5}，
 *
 * 那么答案就为 5，因为最长的子串为{1,2,3,4,5};如果满足条件的子串不存在，就输出 0。
 *
 * 输入描述:
 * 第一行包含一个整数N, 1 ≤ 𝑁 ≤ 105。
 * 第二行包含 N 个整数𝑝𝑖，𝑝𝑖表示序列P第i个元素的值。0 ≤ 𝑝𝑖 ≤ 105。第三行包含一个整数K，1 ≤ 𝐾 ≤ 105。
 *
 * 输出描述:
 * 输出一个整数ANS，表示答案。
 * 示例1
 * 输入
 * 复制
 * 5
 * 1 2 3 4 5
 * 5
 * 输出
 * 复制
 * 5
 */
public class KsMultiple {
    public static int K;
    static int[] num;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        num = new int[N];
        String[] str=null;
        for(int i=0;i<N;i++){
            num[i]=sc.nextInt();

        }
        K = sc.nextInt();
        int[] sum = new int[N+1];
        int[] mo = new int[N+1];
        Map<Integer,Integer> map = new HashMap();
        sum[0]=0;mo[0]=0;
        map.put(mo[0],0);

        for(int i=1;i<=N;i++){
            sum[i]=sum[i-1]+num[i-1];
            mo[i]=sum[i]%K;
            if(!map.containsKey(mo[i])){
                map.put(mo[i],i);
            }
        }
        int len=0;

        for(int i=N;i>=0;i--){
            int j = map.get(mo[i]);

            if(i-j>len){len=i-j;}

        }

        System.out.println(len);
    }
}
