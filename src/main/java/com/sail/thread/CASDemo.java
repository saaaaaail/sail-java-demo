package com.sail.thread;

import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {


    public void atomic(){
        AtomicInteger a = new AtomicInteger(4);
        int b = a.addAndGet(1);
        System.out.println(b);
    }

    public static void main(String args[]){



        CASDemo c = new CASDemo();
        Map<Integer,Integer> map = new HashMap<>();
        map.getOrDefault(1,0);
        Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
        c.longestSubstring("bbaaacbd",3);
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.remove(1);
        set.contains(1);
        List<Integer>[] bucket = new List[2];
        List<Integer> list = new ArrayList<>();


    }
    public boolean[] longestSubstring(String s, int k) {
        boolean[][] f = new boolean[2][2];
        return f[0];

    }

    public int divide(String s,int[] sum,int l,int h,int k){
        if(l>h){return 0;}
        int flag=0;
        int left=0;
        int right=0;
        for(int i=l;i<=h;i++){
            if(sum[s.charAt(i)-'a']<k){
                flag=1;
                sum = new int[26];
                for(int j=i+1;j<=h;j++){
                    sum[s.charAt(j)-'a']++;
                }
                right = divide(s,sum,i+1,h,k);

                sum = new int[26];
                for(int j=l;j<=i-1;j++){
                    sum[s.charAt(j)-'a']++;
                }
                left = divide(s,sum,l,i-1,k);
            }
        }
        //区间没有被拆开
        if(flag==0){return h-l+1;}
        return Math.max(left,right);
    }

}
