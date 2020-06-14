package com.sail.graph;

import java.util.LinkedList;
import java.util.List;

public class DiGraph {
    final int V;
    private int E;
    private List<Integer>[] adj;
    public DiGraph(int V){
        this.V = V;
        adj = new LinkedList[V];//数组的类型转换写法
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<>();//初始化每一项
        }
    }

    public void addEdge(int v,int w){
        adj[v].add(w);
        E++;
    }

    public int getE(){
        return E;
    }

    public DiGraph Reverse(){
        DiGraph R = new DiGraph(V);
        for (int v=0;v<V;v++){
            for (int w:adj[v]){
                R.addEdge(w,v);
            }
        }
        return R;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }


}
