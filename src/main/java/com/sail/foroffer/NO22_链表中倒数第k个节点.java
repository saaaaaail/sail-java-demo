package com.sail.foroffer;


import com.sail.foroffer.pojo.ListNode;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO22_链表中倒数第k个节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        getKthFromEnd(head,0);
    }

    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode first = head;
        ListNode second = head;
        while(k>0){
            first = first.next;
            k--;
            if(first==null&&k>0){
                return first;
            }
        }
        while(first!=null){
            first = first.next;
            second = second.next;
        }
        return second;
    }
}
