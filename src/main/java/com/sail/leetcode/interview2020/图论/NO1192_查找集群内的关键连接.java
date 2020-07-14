package com.sail.leetcode.interview2020.图论;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。
 *
 * 它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接 connections 是无向的。
 *
 * 从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。
 *
 * 「关键连接」是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。
 *
 * 请你以任意顺序返回该集群内的所有 「关键连接」。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * 输出：[[1,3]]
 * 解释：[[3,1]] 也是正确的。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * 不存在重复的连接
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/critical-connections-in-a-network
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO1192_查找集群内的关键连接 {

    /**
     * 000
     * 001
     * 010
     * 011
     * 100
     * 101
     * 110
     * 111
     * @param args
     */
    public static void main(String[] args) {

        NO1192_查找集群内的关键连接 t = new NO1192_查找集群内的关键连接();
        List<List<Integer>> connections = new ArrayList<>();
        List<Integer> one = new ArrayList<>();one.add(1);one.add(0);
        connections.add(one);
        one = new ArrayList<>();one.add(2);one.add(0);
        connections.add(one);
        one = new ArrayList<>();one.add(3);one.add(2);
        connections.add(one);
        one = new ArrayList<>();one.add(4);one.add(2);
        connections.add(one);
        one = new ArrayList<>();one.add(4);one.add(3);
        connections.add(one);
        one = new ArrayList<>();one.add(3);one.add(0);
        connections.add(one);
        one = new ArrayList<>();one.add(4);one.add(0);
        connections.add(one);
        t.criticalConnections(5,connections);
    }

    /**
     * 使用了tarjan算法，求连通分量 190ms 23.29%
     * 方法里使用Map来表示图key表示节点，value表示与key节点相连的点，使用int、boolean二维数组内存空间都会不够
     *  low[n]表示n号节点在递归栈里面相邻的具有最小dfn[]的点
     *  dfn[n]表示tarjan的时间戳，每次dfs一层（访问一个节点）这个值就+1，当递归到最后所有节点都访问了以后，
     *  最后一层的节点n，相邻的节点都已经在递归栈里面了，就更新这个点的low[n]为相邻的具有最小时间戳dfn[i]的点，表示与之前的点相连通
     *  同时这个值从1开始，对于没有访问的点这个值就是0，可以用这个值来判断一个点有没有访问过
     */
    int num= 0;
    Map<Integer,List<Integer>> edges ;
    int[] dfn ;
    int[] low ;
    int n;
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if(connections==null||connections.size()==0||connections.get(0).size()==0){
            return connections;
        }
        this.n = n;
        edges = new HashMap<>();
        dfn = new int[n];
        low = new int[n];
        for(List<Integer> e:connections){
            int v1 = e.get(0);
            int v2 = e.get(1);
            List<Integer> edge1 = edges.getOrDefault(v1,new ArrayList<>());
            edge1.add(v2);
            edges.put(v1,edge1);
            List<Integer> edge2 = edges.getOrDefault(v2,new ArrayList<>());
            edge2.add(v1);
            edges.put(v2,edge2);
        }
        for(int i=0;i<n;i++){
            if(dfn[i]==0){
                tarjan(i,-1);
            }
        }
        return result;
    }


    public void tarjan(int x,int from){
        //新访问一个点，记录时间戳+1的值
        dfn[x] = low[x] = ++num;
        List<Integer>  edge = edges.get(x);
        //遍历这个点相邻的点
        for(Integer i:edge){
            //说明还没有初始化时间戳，可以访问
            if(dfn[i]==0){
                //一直递归到最深层，会走下面的if分支
                tarjan(i,x);
                //当递归到下一层返回以后，更新这一层的low[x]与之前访问的下一层的low取最小的表示他们与这个最小的都是相连通的
                low[x] = Math.min(low[x],low[i]);
                //如何判断是一个桥，当我当前节点的dfn时间戳，比我之前访问的下一个节点的low还要小，
                // 说明下一个节点没有与递归栈前面时间戳小的节点相通，那么这条边就是桥
                if(dfn[x]<low[i]){
                    List<Integer> one = new ArrayList<>();
                    one.add(x);one.add(i);
                    result.add(one);
                }
            }else if(i!=from){
                //这里就是当相邻的所有节点都按照时间戳依次访问了以后
                //就更新与这个点相邻的具有最小时间戳的点保存到low[x]里面
                low[x] = Math.min(low[x],dfn[i]);
            }
        }
    }
}
