package com.sail.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: FenGift
 * 华为笔试题2019/5/8 分礼物
 * @description:
 * @author: sail
 * @create: 2019/5/8 19:30
 */

public class FenGift {
    static int N;
    static int K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] num = sc.nextLine().split(" ");
        N=Integer.parseInt(num[0]);
        K=Integer.parseInt(num[1]);
        List<String> res = new ArrayList<>();
        int[] child = new int[K];

        gift(res,0,child);
        System.out.println(res);
        for (int i=0;i<res.size();i++){
            System.out.println(res.get(i));
        }
    }
    public static void gift(List<String> res,int count,int[] child){
        if(count==N){
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<child.length;i++){
                if (child[i]>0){
                    for (int j=0;j<child[i];j++){
                        sb.append("*");
                    }
                }
                if (i!=child.length-1){
                    sb.append("|");
                }

            }
            if (!res.contains(sb.toString())){
                res.add(sb.toString());
            }


            return;
        }
        for (int i=0;i<K;i++){
            child[i]++;
            gift(res,count+1,child);
            child[i]--;
        }
    }
}
