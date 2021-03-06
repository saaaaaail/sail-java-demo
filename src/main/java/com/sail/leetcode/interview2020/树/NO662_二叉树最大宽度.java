package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.LinkedList;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 示例 1:
 *
 * 输入:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 *
 * 输入:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO662_二叉树最大宽度 {

    /**
     * 层次遍历加上编号，计算每一层的最大宽度 2ms 67.16%
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> numQueue = new LinkedList<>();
        queue.offer(root);
        numQueue.offer(1);
        int result = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            while(size>0){
                TreeNode node = queue.poll();
                Integer num = numQueue.poll();
                max = Math.max(max,num);
                min = Math.min(min,num);
                if(node.left!=null){
                    queue.offer(node.left);
                    numQueue.offer(2*num);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                    numQueue.offer(2*num+1);
                }
                result = Math.max(result,max-min+1);
                size--;
            }
        }
        return result;
    }
}
