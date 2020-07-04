package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO98_验证二叉搜索树 {


    /**
     * 验证是不是二叉排序二叉树
     * 两种思路
     * 要左右子树都是，当前树节点大于直接前序节点，小于直接后驱节点即可
     *
     * 或者
     *
     * 中序遍历为升序
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return doIsValidBST(root);
    }

    public boolean doIsValidBST(TreeNode node){
        if(node==null){
            return true;
        }

        boolean left = doIsValidBST(node.left);
        boolean right = doIsValidBST(node. right);

        boolean cl = false;
        boolean cr = false;
        if(node.left==null&&node.right==null){
            return left&&right;
        }
        TreeNode preNode = node.left;
        if(preNode!=null){
            while(preNode.right!=null){
                preNode = preNode.right;
            }
        }
        TreeNode postNode = node.right;
        if(postNode!=null){
            while(postNode.left!=null){
                postNode = postNode.left;
            }
        }
        if(preNode == null || preNode!=null&&node.val>preNode.val){
            cl = true;
        }
        if(postNode == null || postNode!=null && node.val<postNode.val){
            cr = true;
        }


        return cl&&cr&&left&&right;
    }
}
