package com.sail.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

//1、寻找有向环
//2、基于dfs的顶点排序
public class DfsGraph {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private Stack<Integer> circle=null;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public void dfsGraph(DiGraph g){
        marked = new boolean[g.V];
        onStack = new boolean[g.V];
        edgeTo = new int[g.V];
        pre = new ArrayDeque<>();
        post = new ArrayDeque<>();
        reversePost = new Stack<>();
        for (int v=0;v<g.V;v++){
            if (marked[v]){continue;}
            dfs(g,v);
        }
    }
    public void dfs(DiGraph g, int s){//s起点
        marked[s]=true;
        onStack[s]=true;
        pre.add(s);//前序入队
        //System.out.println(s+" ");
        for (int w:g.adj(s)){
            if (hasCircle()){
                return;
            }else if (!marked[w]){

                edgeTo[w]=s;//往回走的路
                dfs(g,w);
            }else if (onStack[w]){
                circle =new Stack<>();
                for (int i=s;i!=w;i=edgeTo[i]){
                    circle.push(i);
                }
                circle.push(w);
                circle.push(s);
            }
        }
        post.add(s);//后序入队
        reversePost.push(s);//逆后序

        onStack[s]=false;
    }

    public boolean hasCircle(){
        if (circle!=null){return true;}
        return false;
    }

    public Iterable<Integer> circle(){
        return circle;
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }

}
