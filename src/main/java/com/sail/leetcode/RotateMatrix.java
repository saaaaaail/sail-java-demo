package com.sail.leetcode;

import java.util.Scanner;

/**
 * @program: RotateMatrix
 * @description: 华为笔试题2019/5/8 旋转矩阵
 * @author: sail
 * @create: 2019/5/8 19:01
 */

public class RotateMatrix {
    static int N;
    static int M;
    static int[][] matrix;
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);

        N=Integer.parseInt(sc.nextLine());
        matrix = new int[N][N];
        for (int i=0;i<N;i++){
            String[] nums = sc.nextLine().split(" ");
            for (int j=0;j<N;j++){
                matrix[i][j]=Integer.parseInt(nums[j]);
            }
        }
        M=Integer.parseInt(sc.nextLine());
        //int[][] ma1 = new int[N][N];
        int[][] ma2 = new int[N][N];
        int[][] ma3 = new int[N][N];
        int[][] ma4 = new int[N][N];
        //计算ma2
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                ma2[j][N-i-1]=matrix[i][j];
            }
        }
        //计算ma3
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                ma3[N-i-1][N-j-1]=matrix[i][j];
            }
        }
        //计算ma4
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                ma4[N-j-1][i]=matrix[i][j];
            }
        }
        M=(M+1)%4;

        switch (M){
            case 1:
                for (int i=0;i<N;i++){
                    StringBuilder sb = new StringBuilder();
                    for (int j=0;j<N;j++){
                        sb.append(""+matrix[i][j]);
                        if (j!=N-1){
                            sb.append(" ");
                        }else {
                            System.out.println(sb.toString());
                        }
                    }
                }
                break;
            case 2:
                for (int i=0;i<N;i++){
                    StringBuilder sb = new StringBuilder();
                    for (int j=0;j<N;j++){
                        sb.append(""+ma2[i][j]);
                        if (j!=N-1){
                            sb.append(" ");
                        }else {
                            System.out.println(sb.toString());
                        }
                    }
                }
                break;
            case 3:
                for (int i=0;i<N;i++){
                    StringBuilder sb = new StringBuilder();
                    for (int j=0;j<N;j++){
                        sb.append(""+ma3[i][j]);
                        if (j!=N-1){
                            sb.append(" ");
                        }else {
                            System.out.println(sb.toString());
                        }
                    }
                }
                break;
            case 4:
                for (int i=0;i<N;i++){
                    StringBuilder sb = new StringBuilder();
                    for (int j=0;j<N;j++){
                        sb.append(""+ma4[i][j]);
                        if (j!=N-1){
                            sb.append(" ");
                        }else {
                            System.out.println(sb.toString());
                        }
                    }
                }
                break;
        }

    }
}
