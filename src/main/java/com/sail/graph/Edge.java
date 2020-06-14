package com.sail.graph;

public class Edge implements Comparable<Edge>{
    private final int v;
    private final int w;
    private final double weight;
    public Edge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    @Override
    public int compareTo(Edge o) {
        return (int)(this.weight-o.weight);
    }

    //访问边的其中一个点
    public int either(){
        return v;
    }

    //访问边的的另一个点
    public int other(int ver){
        if (ver==v){
            return w;
        }else if (ver==w){
            return v;
        }else {
            throw new RuntimeException("Inconsistent edge");
        }
    }
}
