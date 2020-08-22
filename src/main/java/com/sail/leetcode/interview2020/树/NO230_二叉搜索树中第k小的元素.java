package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO230_二叉搜索树中第k小的元素 {
    private Map<TreeNode,Integer> map = new HashMap<>();
    public int kthSmallest(TreeNode root, int k) {
        return doKthSmallest(root,k);
    }

    /**
     * 思路就是分治法，每一层查找左右子树的节点树与k比较
     * 用map暂存左右子树的节点数量
     * @param node
     * @param k
     * @return
     */
    public int doKthSmallest(TreeNode node, int k){
        if(node==null){
            return 0;
        }

        int leftNum = findSubNodes(node.left);
        if(leftNum==k-1){
            return node.val;
        }else if(leftNum>k-1){
            return doKthSmallest(node.left,k);
        }else{
            return doKthSmallest(node.right,k-leftNum-1);
        }
    }

    public int findSubNodes(TreeNode node){
        if(node==null){
            return 0;
        }
        if(map.containsKey(node)){
            return map.get(node);
        }
        int num = findSubNodes(node.left)+findSubNodes(node.right)+1;
        map.put(node,num);
        return num;
    }
}
