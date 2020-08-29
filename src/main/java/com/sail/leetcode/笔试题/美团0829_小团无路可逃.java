package com.sail.leetcode.笔试题;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/8/29 16:52
 */

import java.util.*;

/**
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 小团惹小美生气了，小美要去找小团“讲道理”。小团望风而逃，他们住的地方可以抽象成一棵有n个结点的树，小美位于x位置，小团位于y位置。小团和小美每个单位时间内都可以选择不动或者向相邻的位置转移，假设小美足够聪明，很显然最终小团会无路可逃，只能延缓一下被“讲道理”的时间，请问最多经过多少个单位时间后，小团会被追上。
 *
 *
 *
 * 输入描述
 * 输入第一行包含三个整数n，x，y，分别表示树上的结点数量，小美所在的位置和小团所在的位置。(1<=n<=50000)
 *
 * 接下来有n-1行，每行两个整数u，v，表示u号位置和v号位置之间有一条边，即u号位置和v号位置彼此相邻。
 *
 * 输出描述
 * 输出仅包含一个整数，表示小美追上小团所需的时间。
 *
 *
 * 样例输入


 * 样例输出
 * 2
 */
public class 美团0829_小团无路可逃 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int total=sc.nextInt();//total
        int mei=sc.nextInt();//mei
        int tuan=sc.nextInt();//tuan
        Map<Integer,List<Integer>> path=new HashMap<>();
        int res = 0;
        for(int i=0;i<total-1;i++){
            int pre=sc.nextInt();
            int next=sc.nextInt();
            if (path.containsKey(pre)) {
                path.get(pre).add(next);
            }else{
                List<Integer> temp=new ArrayList<>();
                temp.add(next);
                path.put(pre,temp);
            }

            if (path.containsKey(next)) {
                path.get(next).add(pre);
            }else{
                List<Integer> temp=new ArrayList<>();
                temp.add(pre);
                path.put(next,temp);
            }

        }

        int[] dMei = new int[total+1];
        int[] dTuan = new int[total+1];

        //计算mei到每一点的时间
        resolveTime(dMei,mei,path,new boolean[total+1]);
        //计算tuan到每一点的时间
        resolveTime(dTuan,tuan,path,new boolean[total+1]);
        //要求被抓的最大值，肯定是要找mei到某一点的时间要大于tuan的点，tuan到那一点后要么往前，要么原地等，折返时间只会时间更短
        for (int i=1;i<=total;i++){
            if(dMei[i]>=dTuan[i]){
                res = Math.max(res,dMei[i]);
            }
        }
        System.out.println(res);
    }
    public static void resolveTime(int[] d,int index,Map<Integer,List<Integer>> path,boolean[] visited){
        visited[index] = true;

        List<Integer> nextNodes = path.get(index);
        for (Integer node:nextNodes){
            if (visited[node]){
                continue;
            }
            d[node] = d[index]+1;
            resolveTime(d,node,path,visited);
        }

        visited[index] = false;

    }


}
