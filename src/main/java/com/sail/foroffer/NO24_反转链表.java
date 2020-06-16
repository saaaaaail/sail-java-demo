package com.sail.foroffer;


import com.sail.foroffer.pojo.ListNode;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 */
public class NO24_反转链表 {

    public static void main(String[] args) {
        ListNode head =new ListNode(0);

        reverseList(head);
    }

    /**
     * 头插法
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode tmpHead = head;
        ListNode reverseHead = null;

        while(tmpHead!=null){
            ListNode tmp = tmpHead;
            tmpHead = tmpHead.next;
            tmp.next = null;

            tmp.next = reverseHead;
            reverseHead = tmp;
        }
        return reverseHead;
    }
}
