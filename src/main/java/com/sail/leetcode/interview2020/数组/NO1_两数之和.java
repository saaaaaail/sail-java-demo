package com.sail.leetcode.interview2020.数组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class NO1_两数之和 {

    /**
     *  求两数之和 要求求出所有的 例 [2，2，3，3,3] 5 输出[2,3] [2,3] [2,3] [2,3]
     * @param args
     */
    public static void main(String[] args) {
        for(int[] i:twoSum(new int[]{2,2,3,3,3},5)){
            System.out.println(i[0]+" "+i[1]);
        }
    }
    public static List<int[]> twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map =new HashMap<>();
        List<int[]> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int sub = target-nums[i];
            if(map.containsKey(sub)){
                int num = map.get(sub);
                while(num>0){
                    list.add(new int[]{sub,nums[i]});
                    num--;
                }
            }else{
                map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            }
        }
        return list;
    }

    /**
     * 不去重的双指针
     */
    public static List<int[]> twoSum2(int[] nums, int target) {
        HashMap<Integer,Integer> map =new HashMap<>();
        List<int[]> list = new ArrayList<>();
        int l = 0;
        int r= nums.length-1;
        while(l<r){
            int sum = nums[l]+nums[r];
            if(sum == target){
                list.add(new int[]{nums[l],nums[r]});
                /**
                 * 这里是用来去重
                 */
                while(l+1<r&&nums[l+1] == nums[l]){
                    l++;
                }
                while(r-1>l&&nums[r-1] == nums[r]){
                    r--;
                }
            }else if(sum<target){
                l++;
            }else if(sum>target){
                r--;
            }
        }
        return list;
    }
}
