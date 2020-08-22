package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *  
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO114_二叉树展开为链表 {

    /**
     * 0ms 100%
     * 递归
     * 将左孩子变为一条链表
     * 将右孩子变为一条链表
     * 将左孩子的链表插入到根节点与右孩子链表之间
     * 返回拼接好的尾节点
     * @param root
     */
    public void flatten(TreeNode root) {
        doFlatten(root);
    }

    public TreeNode doFlatten(TreeNode node){

        if(node==null){
            return null;
        }
        if(node.left==null&&node.right==null){
            return node;
        }
        TreeNode leftTail = doFlatten(node.left);
        TreeNode rightTail = doFlatten(node.right);
        if(leftTail!=null){
            TreeNode tmpRight = node.right;
            node.right = node.left;
            node.left = null;
            leftTail.right = tmpRight;
        }
        return rightTail!=null?rightTail:leftTail;
    }
}
