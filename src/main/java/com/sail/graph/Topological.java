package com.sail.graph;

import java.util.Stack;

public class Topological {

    private Stack<Integer> order;//拓扑排序序列
    private Iterable<Integer> pre;
    private Iterable<Integer> post;
    public Topological(DiGraph g){
        DfsGraph dfsGraph = new DfsGraph();
        dfsGraph.dfsGraph(g);
        if (!dfsGraph.hasCircle()){
            order = (Stack<Integer>) dfsGraph.reversePost();
            pre = dfsGraph.pre();
            post = dfsGraph.post();
        }
        //System.out.println(order);
    }

    public Iterable<Integer> order(){
        return order;
    }
    public Iterable<Integer> pre(){
        return pre;
    }
    public Iterable<Integer> post(){
        return post;
    }

    public boolean isDAG(){
        return order!=null;
    }
}
