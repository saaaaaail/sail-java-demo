package com.sail.sort;

import java.util.Scanner;

public class MergeSort {

    public static void main(String[] args) {

        MergeSort ms = new MergeSort();
        System.out.println("请输入数字以‘,’隔开。");
        Scanner scanner =new Scanner(System.in);
        String str = scanner.nextLine();
        String[] c = str.split(",");
        int len = c.length;
        int[] nums = new int[len];
        for(int i=0;i<len;i++){
            nums[i]=Integer.parseInt(c[i]);
        }
        ms.mergeSort(nums,0,len-1);
        for(int i=0;i<len;i++){
            System.out.println(nums[i]);
        }
    }

    public void mergeSort(int[] nums,int l,int h){
        if (l<h){
            int mid = (l+h)/2;
            mergeSort(nums,l,mid);
            mergeSort(nums,mid+1,h);
            merge(nums,l,mid,h);
        }
    }

    public void merge(int[] nums,int l,int m,int h){
        int i=l;int j=m+1;
        int kt=l;
        int[] tmp = new int[nums.length];
        while (i<=m&&j<=h){
            if(nums[i]>nums[j]){
                tmp[kt++]=nums[j++];
            }else {
                tmp[kt++]=nums[i++];
            }
        }

        while (i<=m){
            tmp[kt++]=nums[i++];
        }

        while (j<=h){
            tmp[kt++]=nums[j++];
        }

        for(kt=l;kt<=h;kt++){
            nums[kt]=tmp[kt];
        }
    }
}
