package com.sail.foroffer;

/**
 * @program: JavaDemo
 * @description: 删除链表的节点
 * 给定单向链表的头指针和一个节点指针，定义一个函数在O(1)时间内删除该节点。
 *
 * @author: sail
 * @create: 2019/06/03 14:39
 */

public class NO18_删除链表的节点 {
    static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
        }
    }
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=null;
        Node tmp = node1;
        while (tmp!=null){
            System.out.println(tmp.data);
            tmp=tmp.next;
        }
        System.out.println(" ");
        deleteNode(node1,node3);
        tmp = node1;
        while (tmp!=null){
            System.out.println(tmp.data);
            tmp=tmp.next;
        }
    }

    public static void deleteNode(Node head,Node node){
        /**
         * 要删除节点的后面一个节点不为空，就用后面节点的数据覆盖当前节点，删除后面的节点
         */
        if (node.next!=null){
            Node tmp = node.next;
            node.data = tmp.data;
            node.next=tmp.next;

        }else if (node==head){
            /**
             * 要删的节点后面节点为空的且为头节点，直接引用置空
             */
            head=null;
            node=null;
        }else {
            /**
             * 否则遍历链表删除
             */
            Node thead = head;
            while (thead.next!=node){
                thead=thead.next;
            }
            thead.next=node.next;
            node=null;
        }
    }
}
