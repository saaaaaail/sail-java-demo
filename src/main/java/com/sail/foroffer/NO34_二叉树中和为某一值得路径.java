package com.sail.foroffer;

import com.sail.foroffer.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 *  
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO34_二叉树中和为某一值得路径 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> onePath = new ArrayList<>();
        if(root!=null){
            path(root,sum,0,onePath,result);
        }
        return result;
    }

    /**
     * 递归
     * 一直走到根节点，判断和是不是等于给定值的，走的同时记录路径，走完了删除路径
     * @param node
     * @param sum
     * @param lastSum
     * @param onePath
     * @param result
     */
    public void path(TreeNode node,int sum,int lastSum,List<TreeNode> onePath,List<List<Integer>> result){
        onePath.add(node);
        if(node.left==null && node.right==null){
            if(node.val+lastSum==sum){
                List<Integer> one = new ArrayList<>();
                for(TreeNode treeNode:onePath){
                    one.add(treeNode.val);
                }
                result.add(one);
            }
        }

        if(node.left!=null){
            path(node.left,sum,lastSum+node.val,onePath,result);
        }
        if(node.right!=null){
            path(node.right,sum,lastSum+node.val,onePath,result);
        }
        onePath.remove(node);
    }
}
