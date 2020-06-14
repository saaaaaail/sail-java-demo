package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 【二维数组中的查找】 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增顺序排序。
 * 输入一个数，判断数组中是否有这个数
 * @author: sail
 * @create: 2019/05/30 10:53
 */

public class NO4_二维数组中的查找 {
    static int M;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M=Integer.parseInt(sc.nextLine());
        N=Integer.parseInt(sc.nextLine());
        String[] strs;
        int[][] nums = new int[M][N];
        for (int j=0;j<M;j++){
            strs = sc.nextLine().split(",");
            for (int i=0;i<N;i++){
                nums[j][i]=Integer.parseInt(strs[i]);
            }
        }

        System.out.println(search(nums,5));
    }

    public static boolean search(int[][] nums,int target){
        for (int i=0;i<nums.length;i++){
            for (int j=nums[0].length-1;j>=0;j--){
                if (target>nums[i][j]){
                    break;
                }else if (target==nums[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
