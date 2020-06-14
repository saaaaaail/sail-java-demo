package com.sail.search;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println("请输入数字以‘,’隔开。");
        Scanner scanner =new Scanner(System.in);
        String str = scanner.nextLine();
        String[] c = str.split(",");
        int len = c.length;
        int[] nums = new int[len];
        for(int i=0;i<len;i++){
            nums[i]=Integer.parseInt(c[i]);
        }
        System.out.println("请输入目标");
        int tar = scanner.nextInt();
        System.out.println("v");
        System.out.println("查找结果："+bs.Binary(nums,tar));
    }

    private int Binary(int[] nums,int tar){
        int len = nums.length;
        int i=0;int j=len-1;
        int mid=(i+j)/2;
        while (i<=j){
            if(nums[mid]>tar){
                j=mid-1;
            }else if (nums[mid]<tar){
                i=mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
