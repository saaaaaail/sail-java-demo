package com.sail.foroffer;


import java.util.Scanner;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 */
public class NO21_调整数组顺序使奇数在前偶数在后 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(",");
        int[] nums = new int[strs.length];
        for (int i=0;i<strs.length;i++){
            nums[i]=Integer.parseInt(strs[i]);
        }
        System.out.println(exchange(nums));
    }

    /**
     * 左右指针包夹
     * 碰到左边的偶数
     * 碰到右边的奇数
     * 这两数就交换然后继续夹逼
     * @param nums
     * @return
     */
    public static int[] exchange(int[] nums) {
        int l=0;
        int r=nums.length-1;
        while(l<r){
            while(l<r&&isOu(nums[r])){
                r--;
            }
            while(l<r&&!isOu(nums[l])){
                l++;
            }
            if(l<r){
                int tmp = nums[l];
                nums[l]=nums[r];
                nums[r] = tmp;
            }
        }
        return nums;
    }

    public static boolean isOu(int num){
        return num%2==0;
    }
}
