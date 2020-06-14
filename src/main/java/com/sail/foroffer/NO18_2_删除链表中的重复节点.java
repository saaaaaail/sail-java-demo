package com.sail.foroffer;

/**
 * @program: JavaDemo
 * @description: 删除链表中的重复节点
 * 在一个排序链表中删除重复节点
 * @author: sail
 * @create: 2019/06/03 15:54
 */

public class NO18_2_删除链表中的重复节点 {
    static class Node{
        int data;
        Node next;
        public Node(int data){this.data=data;}
    }
    public static void main(String[] args) {
        Node head = new Node(0);
        Node thead = head;
        thead.next = new Node(0);
        thead = thead.next;
        thead.next = new Node(0);
        thead = thead.next;
        thead.next = new Node(1);
        thead = thead.next;
        thead.next = new Node(2);
        thead = thead.next;
        thead.next = new Node(3);
        thead = thead.next;
        thead.next = new Node(3);
        thead = thead.next;
        thead.next = new Node(3);
        thead = thead.next;
        thead.next = new Node(3);
        thead = thead.next;
        thead.next = null;
        deleteRepeatedNode2(head);


    }

    /**
     * 删除重复的节点数量至1
     * @param head
     */
    public static void deleteRepeatedNode(Node head){
        Node thead = head;
        Node repeat = head;
        while (thead!=null){
            while (repeat.next!=null&&repeat.next.data==thead.data){
                repeat=repeat.next;
            }
            thead.next=repeat.next;
            thead = thead.next;
            repeat = thead;
        }
        printNode(head);
    }

    /**
     * 节点一旦重复就删除所有
     * 使用thead作为统一的头节点
     * 三个指针:前置节点指针、当前节点指针、当前节点重复指针
     */
    public static void deleteRepeatedNode2(Node head){
        Node thead = new Node(-1);
        thead.next=head;
        Node repeat = head;
        Node pre = thead;
        boolean needDelete = false;
        while (head!=null){
            needDelete = false;
            while (repeat.next!=null&&head.data==repeat.next.data){
                repeat = repeat.next;
                needDelete = true;
            }
            if (!needDelete){
                //如果当前节点下个节点不重复，则涉及到
                //前置节点的替换、当前节点替换、重复节点等于当前节点
                pre = head;
                head = head.next;
                repeat = head;
            }else {
                //如果有重复，则涉及到
                //当前节点的替换、前置节点不变，前置节点的指向替换、重复节点等于当前节点
                head = repeat.next;
                pre.next = head;
                repeat = head;
            }
        }
        printNode(thead.next);
    }

    public static void printNode(Node head){
        StringBuilder sb = new StringBuilder();
        while (head!=null){
            sb.append(head.data).append("->");
            head = head.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }
}
