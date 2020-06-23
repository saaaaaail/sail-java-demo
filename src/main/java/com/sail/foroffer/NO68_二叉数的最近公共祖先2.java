package com.sail.foroffer;

import com.sail.foroffer.pojo.TreeNode;

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
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO68_二叉数的最近公共祖先2 {
    /**
     * 8ms 68.02%
     * 思路就是 递归自底向上，碰到等于p或者q的节点就往上返回，其他的都返空
     * 直到一个节点，他的左右孩子都不为空，那就是最近的祖先节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return root;
        }
        if(p==null){
            return q;
        }
        if(q==null){
            return p;
        }
        return doLowestCommonAncestor(root,p,q);
    }

    public TreeNode doLowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q){
        if(node==null||node==p||node==q){
            return node;
        }


        TreeNode left = doLowestCommonAncestor(node.left,p,q);
        TreeNode right = doLowestCommonAncestor(node.right,p,q);
        if(left!=null&&right!=null){
            return node;
        }
        if(left!=null){
            return left;
        }
        if(right!=null){
            return right;
        }
        return null;
    }
}
