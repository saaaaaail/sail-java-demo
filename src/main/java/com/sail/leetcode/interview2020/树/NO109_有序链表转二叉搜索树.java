package com.sail.leetcode.interview2020.树;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/8/18 8:17
 */

import com.sail.foroffer.pojo.ListNode;
import com.sail.foroffer.pojo.TreeNode;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO109_有序链表转二叉搜索树 {

    /**
     * 0ms 100%
     * 双指针 找中间节点以及中间节点的前序节点
     * 将链拆为左子树链和右子树链
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode tmp = head;
        ListNode pre = null;
        ListNode mid = head;
        ListNode first = head;
        while(first.next!=null&&first.next.next!=null){
            first = first.next.next;
            pre = mid;
            mid = mid.next;
        }
        TreeNode node = new TreeNode(mid.val);

        if(pre!=null){
            pre.next=null;
        }
        /**
         * tmp指针不等于mid说明有左孩子
         */
        if(tmp!=mid){
            node.left = sortedListToBST(tmp);
        }

        /**
         * mid.next不为空 说明有右孩子
         */
        node.right = sortedListToBST(mid.next);

        return node;
    }
}
