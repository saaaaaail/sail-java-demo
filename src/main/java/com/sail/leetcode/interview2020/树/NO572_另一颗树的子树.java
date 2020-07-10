package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO572_另一颗树的子树 {

    /**
     * 判断一颗树是不是另一颗树的子树，与另一道题树是否包含另一颗树不同，
     * 子树表示一整个左孩子或一整个右孩子等于目标树，
     * 而包含一颗树的意思是s的当前树、s的左孩子，s的右孩子有一部分与目标树相同
     * 上面两种情况在边界值的判断上会有区别。
     *
     * 1、比较以当前节点为根的树是否等于t
     * 2、比较以当前节点左孩子为根的树是否等于t
     * 3、比较以当前节点右孩子为根的树是否等于t
     *
     * 使用了一个辅助遍历inTree判断是在一个树里面比较还是在外面递归
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return doIsSubtree(s,t,false);
    }
    public boolean doIsSubtree(TreeNode s, TreeNode t, boolean inTree){
        if(s==null&&t!=null||s!=null&&t==null){
            return false;
        }
        if(s==null&&t==null){
            return true;
        }
        boolean res = false;
        if(inTree){
            if(s.val==t.val){
                return doIsSubtree(s.left,t.left,true)&&doIsSubtree(s.right,t.right,true);
            }else{
                return false;
            }
        }else{
            if(s.val==t.val){
                res = doIsSubtree(s.left,t.left,true)&&doIsSubtree(s.right,t.right,true);
            }
            return res||doIsSubtree(s.left,t,false)||doIsSubtree(s.right,t,false);
        }
    }
}
