package com.sail.leetcode.笔试题.拼多多;

import java.util.*;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/9/1 20:44
 */

public class 多多0901_第四题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<M;i++){
            list.add(sc.nextInt());
        }
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int i=1;i<=N;i++){
            if (set.contains(i)){
                continue;
            }
            for (int j=0;j<list.size();j++){
                if (i%list.get(j)==0){
                    int k = i;
                    int p = 1;
                    while(k*p<=N){
                        int plus = k*p;
                        if (!set.contains(plus)){
                            set.add(plus);
                            count++;


                        }
                        p++;

                    }
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
