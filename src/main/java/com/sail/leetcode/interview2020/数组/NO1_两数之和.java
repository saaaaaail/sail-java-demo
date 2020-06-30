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
}
