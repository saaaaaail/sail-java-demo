package com.sail.leetcode;

import java.util.LinkedList;

/**
 * @program: JavaDemo
 * @description: 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 *
 *
 * 示例：
 * 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 *
 * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}
 *
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *
 * @author: sail
 * @create: 2019/05/07 20:32
 */


public class No117 {
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    static LinkedList<Node> queue;
    static LinkedList<Integer> qindex;
    static int[] floor;
    public Node connect(Node root) {
        /**
         *该方法用到了数组存层次，浪费空间
         if(root==null){return root;}
         int preNum=0;
         Node preNode=null;
         queue = new LinkedList();
         qindex = new LinkedList();
         floor = new int[1000];
         queue.offer(root);
         qindex.offer(1);
         floor[1]=1;
         floor[0]=0;
         while(!queue.isEmpty()&&!qindex.isEmpty()){
         int num = qindex.poll();
         Node node = queue.poll();
         if(floor[preNum]==floor[num]){
         preNode.next=node;
         }
         if(node.left!=null){
         int left=2*num;
         floor[left]=floor[num]+1;
         qindex.offer(left);
         queue.offer(node.left);
         }
         if(node.right!=null){
         int right=2*num+1;
         floor[right]=floor[num]+1;
         qindex.offer(right);
         queue.offer(node.right);
         }
         preNum = num;
         preNode = node;
         }
         return root;
         */

        if(root==null){return null;}
        queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                size--;
                Node node = queue.poll();
                if(size>0){
                    node.next=queue.peek();
                }
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
}
