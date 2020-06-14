package com.sail.leetcode;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 美团点评面试题
 * 公交站
 */
public class BusStation {
    public static int N;
    public static int M;
    public static boolean[][] G;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        String[] num = sc.nextLine().split(" ");
        N=Integer.parseInt(num[0]);
        M=Integer.parseInt(num[1]);
        G=new boolean[N+1][N+1];
        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            graph[i]=new ArrayList<>();
        }
        //建图
        for(int k=0;k<M;k++){
            num = sc.nextLine().split(" ");
            int tmp = Integer.parseInt(num[0]);
            for(int i=1;i<=tmp;i++){
                for(int j=i+1;j<=tmp;j++){
                    int u = Integer.parseInt(num[i]);
                    int v = Integer.parseInt(num[j]);
                    if(!G[u][v]){
                        G[u][v]=G[v][u]=true;
                        graph[u].add(v);
                        graph[v].add(u);
                    }
                }
            }
        }
        //for(int i=1;i<=N;i++){
        //    System.out.println(graph[i]);
        //}
        //求最短路径
        System.out.println(dijkstra());




    }
    public static int dijkstra(){
        boolean[] marked = new boolean[N+1];
        int[] d = new int[N+1];
        int min=Integer.MAX_VALUE;
        for(int i=1;i<=N;i++){
            d[i]=Integer.MAX_VALUE;
        }
        d[1]=0;
        int num=1;
        while(num<=N){
            min=Integer.MAX_VALUE;
            for(int i=1;i<=N;i++){
                if(!marked[i] && d[i]<min){
                    min=i;
                }
            }
            //System.out.println(min);
            //System.out.println(marked[1]);
            marked[min]=true;
            for(Integer x:graph[min]){
                if(!marked[x]&&d[min]+1<d[x]){
                    d[x]=d[min]+1;
                }
            }
            num++;
        }
        return d[N];
    }
}
