package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

/**
 *二叉搜索树中的两个节点被错误地交换。
 *
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 *
 * 输入: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 *
 * 输入: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * 进阶:
 *
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO99_恢复二叉搜索树 {


    /**
     * 根据题意，只有两个节点 被错误的交换了，
     * 因此，通用思路就是 中序遍历 找逆序对，
     * 如果有多个逆序对，
     * 保存第一个逆序对的第一个元素 以及
     * 最后一个逆序对的第二个元素
     * 遍历结束以后，就将这俩节点的值更新交换
     *
     * 下面是递归的方式，常数的存储空间，在中序遍历时记录前置节点，
     * 中序遍历的前置节点一定是小于后续节点的，因此如果前置节点大于后续节点，就是一个逆序队
     * 记录pre与当前node，如果碰到第二个逆序对，只更新第二个节点指针即可。
     *
     * 也可以先使用一个list将中序遍历记录下来以后找逆序对。
     */
    TreeNode l;
    TreeNode r;
    TreeNode pre;
    public void recoverTree(TreeNode root) {
        doRecoverTree(root);
        if(l!=null&&r!=null){
            int tmp = l.val;
            l.val = r.val;
            r.val = tmp;
        }
    }

    public void doRecoverTree(TreeNode node){
        if(node==null){
            return ;
        }
        doRecoverTree(node.left);
        if(pre!=null&&pre.val>node.val){
            if(l==null){
                l = pre;
            }
            r = node;
        }
        pre = node;
        doRecoverTree(node.right);
    }
}
