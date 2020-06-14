package com.sail.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * 黑白矩阵
 *
 * 题目概述：
 * 定义一个矩阵为黑白矩阵，当且仅当对于矩阵内的任意一个元素，其上下左右的数字完全一致（如果存在）且不和本身重复
 * 现给出一个矩阵A，给定大小为n*m，内部数据都为整型，请问至少修改矩阵中的几个元素才能使得矩阵A为黑白矩阵？
 * 输入描述：
 * 第一行：n m
 * 之后n行，每行有m个整数，用空格分割
 * 输出描述：
 * 一个整数
 * 输入样例：
 * 3 3
 * 1 1 1
 * 1 1 1
 * 1 1 1
 * 输出样例：
 * 4
 * 输入样例2：
 * 3 3
 * 1 1 1
 * 1 5 1
 * 1 1 1
 * 输出样例2：
 * 4
 * 输入样例3：
 * 3 4
 * 1 2 3 1
 * 2 3 1 2
 * 3 1 2 3
 * 输出样例3：
 * 6
 */
public class BlackWhiteMatrix {
    static int N;
    static int M;
    static int[][] matrix;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] num;
        num = sc.nextLine().split(" ");
        N=Integer.parseInt(num[0]);
        M=Integer.parseInt(num[1]);
        matrix = new int[N][M];
        for (int i=0;i<N;i++){
            num = sc.nextLine().split(" ");
            for(int j=0;j<M;j++){
                matrix[i][j]=Integer.parseInt(num[j]);
            }
        }//建矩阵ok

        Map<Integer,Integer> map1 = new HashMap<>();//奇数
        int count1=0;
        Map<Integer,Integer> map2 = new HashMap<>();//偶数
        int count2=0;
        for(int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                if((i+j)%2==1){
                    count1++;
                    if (map1.containsKey(matrix[i][j])){
                        int x = map1.get(matrix[i][j]);
                        map1.put(matrix[i][j],x+1);
                    }else{
                        map1.put(matrix[i][j],1);
                    }
                }else {
                    count2++;
                    if (map2.containsKey(matrix[i][j])){
                        int x = map2.get(matrix[i][j]);
                        map2.put(matrix[i][j],x+1);
                    }else{
                        map2.put(matrix[i][j],1);
                    }
                }
            }
        }//存map ok
        map1.put(0,0);
        map2.put(0,0);
        int max1=Integer.MIN_VALUE;
        int max2=Integer.MIN_VALUE;
        Iterator<Integer> it1 = map1.keySet().iterator();
        Iterator<Integer> it2 = map2.keySet().iterator();
        while(max1==max2){

            if (max1==Integer.MIN_VALUE||
                    (map1.get(max1)<map2.get(max2))){
                int tmp1 = max1;
                max1=0;
                while (it1.hasNext()){
                    Integer x = it1.next();
                    if (x!=tmp1 && map1.get(x)>map1.get(max1)){
                        max1=x;
                    }
                }
                System.out.println(max1);
            }
            if (max2==Integer.MIN_VALUE||
                    (map2.get(max2)<map1.get(max1))){
                int tmp2 = max2;max2=0;
                while (it2.hasNext()){
                    Integer x = it2.next();
                    if (x!=tmp2 && map2.get(x)>map2.get(max2)){
                        max2=x;

                    }
                }
                System.out.println(max2);
            }

        }
        if (max1==0){
            System.out.println(count2-map2.get(max2));return;
        }
        if (max2==0){
            System.out.println(count1-map1.get(max1));return;
        }
        System.out.println(count1-map1.get(max1)+count2-map2.get(max2));

    }
}
