package com.sail.graph;

import java.util.Scanner;
import java.util.Stack;

public class GraphDemo {

    public static void main(String[] args) {
        //创建图
        DiGraph diGraph = new DiGraph(13);
        diGraph.addEdge(0,1);
        diGraph.addEdge(0,5);
        diGraph.addEdge(0,6);
        diGraph.addEdge(2,3);
        diGraph.addEdge(3,5);
        diGraph.addEdge(5,4);
        diGraph.addEdge(6,4);
        diGraph.addEdge(6,9);
        diGraph.addEdge(7,6);
        diGraph.addEdge(8,7);
        diGraph.addEdge(9,10);
        diGraph.addEdge(9,11);
        diGraph.addEdge(9,12);
        diGraph.addEdge(11,12);
        //diGraph.addEdge(12,9);
        //dfs找环+顶点排序
        DfsGraph dfsGraph = new DfsGraph();
        dfsGraph.dfsGraph(diGraph);
        System.out.println(dfsGraph.circle());
        //拓扑排序
        Topological topological = new Topological(diGraph);
        Stack<Integer> stack = (Stack<Integer>)topological.order();
        System.out.println(stack.peek());
        System.out.println(topological.order());
        //
        Scanner scanner = new Scanner(System.in);
        String ss = scanner.next();
        System.out.println(ss);
    }
}
