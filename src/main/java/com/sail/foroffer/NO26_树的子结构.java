package com.sail.foroffer;

import com.sail.foroffer.pojo.TreeNode;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO26_树的子结构 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        /**
         * A B都不能为空
         */
        if(A==null || B==null){
            return false;
        }

        return travelTreeStructure(A,B,false);

    }

    /**
     * 思路是考虑节点匹配时的四种情况
     * 当A节点是空，B节点还不是空 说明B肯定不是A的子结构，算作匹配失败的
     * 当A节点不是空，B节点是空  这个时候如果在匹配的子树中就，算账匹配成功的
     * 当A节点是空，B节点是空  这个时候如果在匹配的子树中就，算账匹配成功的
     * 当A节点不是空，B节点不是空
     * 就得比较了
     * 如果现在正在一颗头节点命中的子树中，如果当前A的val等于B的val则继续比较A左孩子B左孩子，A的右孩子B的右孩子，同时满足
     *                                  如果不等直接返回这颗子树匹配失败
     * 如果不在一颗头节点命中的子树中，则要考虑A与B头节点相同val的子树匹配情况，
     *                              A的左孩子与B头节点的匹配情况，
     *                              A的右孩子与B头节点的匹配情况，
     * 取或的关系，有一个能匹配成功就认为B是A的子结构
     * @param A
     * @param B
     * @param inATree
     * @return
     */
    public boolean travelTreeStructure(TreeNode A, TreeNode B, boolean inATree){
        /**
         * 如果A遍历到一个节点为空，B还不为空。则B肯定不是A的子结构
         */
        if(A==null && B!=null){
            return false;
        }
        /**
         * 如果遍历到A不为空的节点，B不为空的节点，则需要判断
         */
        if(A!=null && B!=null){
            boolean result = false;
            if(inATree){
                if(A.val == B.val){
                    return travelTreeStructure(A.left,B.left,true) && travelTreeStructure(A.right,B.right,true);
                }else{
                    return false;
                }
            }else{
                if(A.val == B.val){
                    result = travelTreeStructure(A.left,B.left,true) && travelTreeStructure(A.right,B.right,true);
                }
            }
            return result || travelTreeStructure(A.left,B,false) || travelTreeStructure(A.right,B,false);
        }

        /**
         * 对于
         * A==null && B==nul
         * A！=null && B==null
         * 两种情况，
         * 如果目前正在一颗匹配的子树中则返回true
         * 如果目前不在匹配的子树中返回false
         */
        return inATree;

    }
}
