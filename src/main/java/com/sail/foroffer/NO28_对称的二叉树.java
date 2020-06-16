package com.sail.foroffer;

import com.sail.foroffer.pojo.TreeNode;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO28_对称的二叉树 {
    public boolean isSymmetric(TreeNode root) {
        if(root==null){return true;}
        return doSymmetric(root.left,root.right);
    }


    /**
     * 关键思路：判断一个节点的树是不是镜像的，就是要判断其左孩子的右节点与其右孩子的左节点是不是镜像的
     * 首先约束条件有
     * 左孩子为空右孩子不为空、左孩子不为空右孩子为空 都不是镜像的返回false
     * 左右都为空，肯定是镜像的，返回true
     * 左右都不为空就要使用上面的关键思路进行判断啦
     * 见代码
     * @param left
     * @param right
     * @return
     */
    public boolean doSymmetric(TreeNode left,TreeNode right){
        if(left==null&&right!=null||left!=null&&right==null){
            return false;
        }
        if(left==null&&right==null){
            return true;
        }
        if(left.val!=right.val){
            return false;
        }
        TreeNode leftChild = left;
        TreeNode rightChild = right;

        return doSymmetric(leftChild.right,rightChild.left) && doSymmetric(leftChild.left,rightChild.right);

    }
}
