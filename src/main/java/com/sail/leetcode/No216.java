package com.sail.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemo
 * @description: 组合总和3
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * @author: sail
 * @create: 2019/04/29 14:17
 */

public class No216 {
    public static void main(String[] args) {
        No216 no216 = new No216();
        System.out.println(no216.combinationSum3(2,18));
    }
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        com(list,res,k,n,0,0);
        return res;
    }

    public void com(List<Integer> list,List<List<Integer>> res,int k,int n,int count,int sum){
        if(count==k&&sum!=n){return;}
        if(count==k){
            res.add(new ArrayList(list));
            return;
        }

        int rest = n-sum;
        int last = (list.isEmpty())?0:list.get(list.size()-1);
        if(rest<=last){
            return;
        }else{
            for(int j=last+1;j<=rest;j++){
                if (j>9){return;}
                list.add(j);
                com(list,res,k,n,count+1,sum+j);
                list.remove(list.size()-1);
            }
        }

    }
}
