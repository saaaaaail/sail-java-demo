package com.sail.foroffer;

import com.sail.foroffer.pojo.ListNode;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 */
public class NO52_两个链表的第一个公共节点 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int a1 = 0;
        int a2 = 0;
        ListNode heada = headA;
        while(heada!=null){
            heada = heada.next;
            a1++;
        }
        ListNode headb = headB;
        while(headb!=null){
            headb =headb.next;
            a2++;
        }
        int sub = 0;
        if(a2>a1){
            sub = a2-a1;
            while(sub>0){
                headB = headB.next;
                sub--;
            }
        }else if(a2<a1){
            sub = a1-a2;
            while(sub>0){
                headA = headA.next;
                sub--;
            }
        }

        while(headA!=headB){
            headB = headB.next;
            headA = headA.next;
        }
        return headA;

    }
}
