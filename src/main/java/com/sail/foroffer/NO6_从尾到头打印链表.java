package com.sail.foroffer;

import com.sail.foroffer.pojo.ListNode;

/**
 * @program: JavaDemo
 * @description: 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来打印每个节点的值
 * @author: sail
 * @create: 2019/05/30 11:24
 */

public class NO6_从尾到头打印链表 {
    public static void main(String[] args) {
        /**
         * 考虑能否修改链表
         * 能修改的话 头插法
         * 不能修改的话 栈 或者 递归
         */
        ListNode head = new ListNode();
        head.key=0;
        head.next = null;
        ListNode p = head;
        for (int i=1;i<10;i++){
            ListNode tmp = new ListNode();
            tmp.key=i;
            tmp.next=null;
            p.next = tmp;
            p=tmp;
        }
        printList(head);
        play2(head);
        play1(head);

    }

    /**
     * 能修改原链表
     * 方式一:头插法
     */
    public static void play1(ListNode head){
        ListNode tmpHead = null;

        while (head!=null){
            ListNode p = head;
            head = head.next;
            p.next = tmpHead;
            tmpHead = p;
        }

        printList(tmpHead);
    }

    /**
     * 不能修改原链表
     * 方式二:借助递归或者栈
     */
    public static void play2(ListNode head){
        if (head!=null){
            play2(head.next);
            System.out.print(head.key);
            System.out.print(" ");
        }
    }

    public static void printList(ListNode head){
        StringBuilder sb = new StringBuilder();
        while (head!=null){
            sb.append(head.key).append("->");
            head = head.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }
}
