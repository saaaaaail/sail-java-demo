package com.sail.tree;

import java.util.ArrayDeque;

public class partitionDemo {
    //快排非递归
    public static void main(String args[]){

        int[] nums={1,9,2,8,3,7,4,6,5};
        partitionDemo p = new partitionDemo();
        p.partiDemo(nums);
        for(int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }

    }
    public void partiDemo(int[] nums){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int p = partition(nums,0,nums.length-1);

        if(p-1>0){
            stack.push(0);
            stack.push(p-1);
        }
        if(p+1<nums.length-1){
            stack.push(p+1);
            stack.push(nums.length-1);
        }
        while(!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            p = partition(nums,l,r);
            if(p-1>l){
                stack.push(l);
                stack.push(p-1);
            }
            if(p+1<r){
                stack.push(p+1);
                stack.push(r);
            }
        }
    }

    public int partition(int[] nums,int l,int r){
        int p=nums[l];
        while(l<r){
            while(l<r&&p<nums[r]){r--;}
            if(l==r){break;}
            nums[l++]=nums[r];
            while(l<r&&p>nums[l]){l++;}
            if(l==r){break;}
            nums[r++]=nums[l];
        }
        nums[r]=p;

        return r;
    }
}
