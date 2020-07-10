package com.sail.leetcode.interview2020.链表;

import com.sail.leetcode.interview2020.pojo.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO206_反转链表 {


    /**
     * 使用的递归的方式
     */
    private ListNode newHead = null;
    public ListNode reverseList(ListNode head) {
        if(head==null){
            return head;
        }
        doReverseList(head);
        return newHead;
    }

    public ListNode doReverseList(ListNode node){
        if(node.next==null){
            newHead = node;
            return node;
        }else{
            ListNode tmp = node.next;
            node.next = null;
            ListNode tail = doReverseList(tmp);
            tail.next = node;
            tail = tail.next;
            return tail;
        }
    }
}
