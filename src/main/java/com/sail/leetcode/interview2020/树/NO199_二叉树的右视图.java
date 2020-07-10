package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO199_二叉树的右视图 {

    /**
     *  1ms 94.94%
     *  思路就是 先不停地寻找右孩子，没有了就寻找左孩子
     *  如果新进入一个节点，节点的高度比max都要大，说明这个高度第一次出现，就是右边能看到的第一个节点
     *  更新最高值，加入结果，后面即便回溯再次达到这个值右边也看不见。
     *
     *  还有一种思路  层序遍历，记录每层的最后一个节点
     */
    private int max = Integer.MIN_VALUE;
    private List<Integer> result = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        doRightSideView(root,0);
        return result;
    }
    public void doRightSideView(TreeNode node, int height){
        if(node == null){
            return;
        }
        if(height>max){
            result.add(node.val);
            max = height;
        }
        doRightSideView(node.right,height+1);
        doRightSideView(node.left,height+1);
    }
}
