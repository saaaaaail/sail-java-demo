package com.sail.leetcode.interview2020.链表;


import com.sail.leetcode.interview2020.pojo.ListNode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO2_两数之和 {

    /**
     * 2ms 99.85% 注意最后一个进位也要处理
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int c = 0;
        while(l1!=null||l2!=null){
            ListNode node = new ListNode();
            int sum = c;
            if(l1!=null){
                sum+=l1.val;
            }
            if(l2!=null){
                sum+=l2.val;
            }
            if(sum>9){
                c =1;
                sum = sum -10;
            }else{
                c= 0;
            }
            node.val = sum;
            if(head==null){
                head = node;
                tail = node;
            }else{
                tail.next = node;
                tail = tail.next;
            }
            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
        }
        if(c==1){
            ListNode node = new ListNode(c);
            tail.next = node;
            tail = tail.next;
        }

        return head;
    }
}
