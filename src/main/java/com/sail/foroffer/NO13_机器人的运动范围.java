package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 机器人的运动范围
 * 地上有一个m行n列的方格。一个机器人从坐标(0,0)的格子开始移动，
 * 它每次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标的数位之和大于k的格子
 * @author: sail
 * @create: 2019/05/31 20:21
 */

public class NO13_机器人的运动范围 {
    static int[][] nums;
    static int k;
    static int M;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = Integer.parseInt(sc.nextLine());
        N = Integer.parseInt(sc.nextLine());
        for (int i=0;i<M;i++){
            String[] strs = sc.nextLine().split(",");
            for (int j=0;j<N;j++){
                nums[i][j] = Integer.parseInt(strs[j]);
            }
        }
        int k = Integer.parseInt(sc.nextLine());
        boolean[][] onstack = new boolean[M][N];
        boolean[][] marked = new boolean[M][N];

        movingCount(0,0,nums,k,marked);

    }

    public static int movingCount(int l,int r,int[][] nums,int k,boolean[][] marked){
        if (l<0||l>=M||r<0||r>=N||marked[l][r]){
            return 0;
        }
        marked[l][r]=true;
        int sum = countSum(l,r);
        if (sum>k){
            return 0;
        }
        int left = movingCount(l,r-1,nums,k,marked);
        int right = movingCount(l,r+1,nums,k,marked);
        int up = movingCount(l+1,r,nums,k,marked);
        int down = movingCount(l-1,r,nums,k,marked);
        return left+right+up+down;
    }

    public static int countSum(int num1,int num2){
        int sum=0;
        while (num1!=0){
            sum+=num1%10;
            num1=num1/10;
        }
        while (num2!=0){
            sum+=num2%10;
            num2=num2/10;
        }
        return sum;
    }
}
