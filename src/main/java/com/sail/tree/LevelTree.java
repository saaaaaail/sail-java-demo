package com.sail.tree;

import java.util.LinkedList;

/**
 * @program: JavaDemo
 * @description: 分层打印的层次遍历
 *
 *
 * @author: sail
 * @create: 2019/05/05 11:57
 */

public class LevelTree {
    static LinkedList<TreeNode> queue = new LinkedList<>();
    public static void levelTree(TreeNode root){
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size>0){

                TreeNode node = queue.pop();
                System.out.print(node.getVal());
                if (size!=1){
                    System.out.print(" ");
                }
                TreeNode left = node.getLchild();
                TreeNode right = node.getRchild();
                if (left!=null){
                    queue.offer(left);
                }
                if (right!=null){
                    queue.offer(right);
                }
                size--;
            }
            System.out.println("");
        }
    }
}
