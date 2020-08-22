package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.LinkedList;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO111_二叉树最小深度 {


    /**
     * 1ms 15.33%
     * 层次遍历
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int ans = Integer.MAX_VALUE;
        int level = 0;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            while(size>0){
                TreeNode node = queue.poll();
                if(node.left==null&&node.right==null){
                    ans = Math.min(level,ans);
                    return ans;
                }
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                size--;
            }
        }
        return ans;
    }
}
