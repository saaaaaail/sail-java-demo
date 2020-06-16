package com.sail.foroffer;

import com.sail.foroffer.pojo.ListNode;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
public class NO25_合并有序链表 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode result = null;
        ListNode tail = null;

        while(p1!=null && p2!=null){
            ListNode target = null;
            if(p1.val<p2.val){
                target = p1;
                p1 = p1.next;
                target.next = null;
            }else{
                target = p2;
                p2 = p2.next;
                target.next = null;
            }
            if(result == null && tail == null){
                result =target;
                tail = target;
            }else{
                tail.next = target;
                tail = tail.next;
            }
        }
        if(p1!=null){
            if(result == null && tail == null){
                result =p1;
            }else{
                tail.next = p1;
            }
        }
        if(p2!=null){
            if(result == null && tail == null){
                result =p2;
            }else{
                tail.next = p2;
            }
        }
        return result;
    }
}
