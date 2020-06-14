package com.sail.leetcode;

import com.sail.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: No107
 * @description: 107. 二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * @author: sail
 * @create: 2019/5/3 15:45
 */

public class No107 {
    /**
     * 按层来遍历树，使用一个数组floor[]保存层数，使用一个队列qnum保存数节点的编号（1~N）
     * 根节点为1号节点，记录上一个节点的层数，与从队列中出队的节点的层数比较
     * 不同的话说明是新的层数
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.setLchild(new TreeNode(9));
        root.setRchild(new TreeNode(20));
        TreeNode node = root.getRchild();
        node.setLchild(new TreeNode(15));
        node.setRchild(new TreeNode(7));
        No107 no107 =new No107();
        System.out.println(no107.levelOrderBottom(root));
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root,res);
        Collections.reverse(res);
        return res;
    }

    public void levelOrder(TreeNode root,List<List<Integer>> res){
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> qnum = new LinkedList<>();
        int[] floor = new int[100];
        List<Integer> list = new ArrayList<>();

        int count=0;
        int precount;
        floor[0]=0;
        floor[1]=1;
        qnum.offer(1);
        queue.offer(root);
        while (!queue.isEmpty()&&!qnum.isEmpty()){
            TreeNode node = queue.poll();
            precount=count;
            count = qnum.poll();

            if (floor[precount]!=floor[count]&&count!=1){
                res.add(list);
                list = new ArrayList<>();
            }
            list.add(node.getVal());

            if (node.getLchild()!=null){
                int left=count*2;
                floor[left]=floor[count]+1;
                qnum.offer(left);
                queue.offer(node.getLchild());
            }
            if (node.getRchild()!=null){
                int right = count*2+1;
                floor[right]=floor[count]+1;
                qnum.offer(right);
                queue.offer(node.getRchild());
            }
        }
        if (!list.isEmpty()){
            res.add(list);
        }
    }
}
