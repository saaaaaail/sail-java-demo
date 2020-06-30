package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.LinkedList;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 * 通过次数56,416提交次数132,390
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO124_二叉树中的最大路径和_hard {


    /**
     * 在第一种滴露的基础上，使用LinkedList记录了路径节点
     */
    private int result2 = Integer.MIN_VALUE;

    public int maxPathSum2List(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        doMaxPathSum2(root,list);
        return result;
    }

    public int doMaxPathSum2(TreeNode node,LinkedList<TreeNode> list){
        if(node == null){
            return 0;
        }

        LinkedList<TreeNode> leftList = new LinkedList<>();
        LinkedList<TreeNode> rightList = new LinkedList<>();
        int left = doMaxPathSum2(node.left,leftList);
        int right = doMaxPathSum2(node.right,rightList);

        LinkedList<TreeNode> currentList = new LinkedList<>();
        int currentSum = node.val;
        currentList.add(node);
        if(left>0){
            currentSum+=left;
            currentList.addAll(leftList);
        }
        if(right>0){
            currentSum+=right;
            currentList.addAll(rightList);
        }
        /**
         * 打印递归过程中记录的所有最大路径和，最后一个就是结果路径
         */
        if(result<currentSum){
            StringBuilder sb = new StringBuilder();
            for(TreeNode n : currentList){
                sb.append(n.val).append(" ");
            }
            System.out.println(sb.toString());
        }
        result = Math.max(result,currentSum);

        list.add(node);
        if(left>right){
            if(left>0){
                list.addAll(leftList);
            }
        }else{
            if(right>0){
                list.addAll(rightList);
            }
        }
        return Math.max(node.val,Math.max(node.val+left,node.val+right));
    }

    /**
     * 对于只能传一个参数返回的时候，还可以使用全局变量，这居然想到，我吐了
     * 思路就是 自底向上递归，每到一层节点就计算当层节点的最大路径和，更新最后的结果
     * 然后呢把当层节点 左右孩子里面路径和大的返回给上一层去判断最大路径和
     */
    //
    private int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        doMaxPathSum(root);
        return result;
    }

    public int doMaxPathSum(TreeNode node){
        if(node == null){
            return 0;
        }

        int left = doMaxPathSum(node.left);
        int right = doMaxPathSum(node.right);

        int currentSum = node.val;
        if(left>0){
            currentSum+=left;
        }
        if(right>0){
            currentSum+=right;
        }
        result = Math.max(result,currentSum);

        return Math.max(node.val,Math.max(node.val+left,node.val+right));
    }
}
