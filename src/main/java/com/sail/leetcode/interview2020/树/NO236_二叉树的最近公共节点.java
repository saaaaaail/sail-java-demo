package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO236_二叉树的最近公共节点 {

    /**
     * 思路就是找左右孩子都不为空的那个点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return doLowestCommonAncestor(root,p,q);
    }

    public TreeNode doLowestCommonAncestor(TreeNode node,TreeNode p,TreeNode q){
        if(node == null){
            return null;
        }
        if(node==p||node==q){
            return node;
        }
        TreeNode left = doLowestCommonAncestor(node.left,p,q);
        TreeNode right = doLowestCommonAncestor(node.right,q,p);
        if(left!=null&&right!=null){
            return node;
        }
        if(left==null&&right!=null){
            return right;
        }
        if(right==null&&left!=null){
            return left;
        }
        return null;
    }
}
