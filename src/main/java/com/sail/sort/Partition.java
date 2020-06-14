package com.sail.sort;

import java.util.Scanner;

public class Partition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] c = str.split(",");
        int[] array = new int[c.length];
        for (int i=0;i<array.length;i++){
            array[i]=Integer.parseInt(c[i]);
        }

        Partition par = new Partition();
        par.partition(array,0,array.length-1);
        for (int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }


    }

    public void partition(int[] nums,int i,int j){
        if (i>=j){return;}
        int p =parti(nums,i,j);
        partition(nums,i,p-1);
        partition(nums,p+1,j);

        for (int k=0;k<nums.length;k++){
            System.out.print(nums[k]+" ");
        }
        System.out.println(" ");
    }

    public int parti(int[] nums,int i,int j){
        int p=nums[i];
        while (i<j){
            while (i<j &&p<nums[j]){j--;}
            if(i==j){break;}
            nums[i]=nums[j];i++;

            while (i<j &&p>nums[i]){i++;}
            if(i==j){break;}
            nums[j]=nums[i];j--;
        }
        nums[i]=p;

        return i;
    }
}
