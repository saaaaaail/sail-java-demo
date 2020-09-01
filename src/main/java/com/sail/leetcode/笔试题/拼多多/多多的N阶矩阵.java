package com.sail.leetcode.笔试题.拼多多;

import java.util.Scanner;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/9/1 19:03
 */

public class 多多的N阶矩阵 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] res = new int[n][n];
        boolean isOdd = n%2!=0;
        int mid = (n-1)/2;
        for(int i=0;i<res.length;i++){
            for(int j=0;j<res.length;j++){
                if (isOdd&&j==(n-1)/2 || isOdd&&i==(n-1)/2 || i==j ||i+j==n-1){
                    res[i][j]=0;
                    continue;
                }
                if (isOdd){
                    if (i>j&&i<mid){
                        res[i][j] = 3;
                    }else if (i>j&&i>mid&&i+j<n-1){
                        res[i][j] = 4;
                    }else if (i>j&&i+j>n-1&&j<mid){
                        res[i][j] = 5;
                    }else if (i>j&&i+j>n-1&&j>mid){
                        res[i][j] = 6;
                    }else if (i<j&&j<mid){
                        res[i][j] = 2;
                    }else if (i<j&&j>mid&&i+j<n-1){
                        res[i][j] = 1;
                    }else if (i<j&&i+j>n-1&&i<mid){
                        res[i][j] = 8;
                    }else if (i<j&&i+j>n-1&&i>mid){
                        res[i][j] = 7;
                    }
                }else{
                    if (i>j&&i<=mid){
                        res[i][j] = 3;
                    }else if (i>j&&i>mid&&i+j<n-1){
                        res[i][j] = 4;
                    }else if (i>j&&i+j>n-1&&j<=mid){
                        res[i][j] = 5;
                    }else if (i>j&&i+j>n-1&&j>mid){
                        res[i][j] = 6;
                    }else if (i<j&&j<=mid){
                        res[i][j] = 2;
                    }else if (i<j&&j>mid&&i+j<n-1){
                        res[i][j] = 1;
                    }else if (i<j&&i+j>n-1&&i<=mid){
                        res[i][j] = 8;
                    }else if (i<j&&i+j>n-1&&i>mid){
                        res[i][j] = 7;
                    }
                }
            }
        }
        for (int i=0;i<res.length;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<res.length;j++){
                sb.append(res[i][j]);
                if (j<res.length-1){
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }
}
