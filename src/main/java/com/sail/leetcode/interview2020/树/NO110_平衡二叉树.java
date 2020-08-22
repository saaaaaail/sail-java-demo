package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO110_平衡二叉树 {

    /**
     * 要每一个点都平衡点
     */
    Map<TreeNode,Integer> map = new HashMap<>();
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        boolean res = Math.abs(findHeight(root.left)-findHeight(root.right))<=1;
        return res && isBalanced(root.left) && isBalanced(root.right);
    }

    public int findHeight(TreeNode node){
        if(node==null){
            return 0;
        }
        if(map.containsKey(node)){
            return map.get(node);
        }
        int height = Math.max(findHeight(node.left),findHeight(node.right))+1;
        map.put(node,height);
        return height;

    }
}
