package com.sail.leetcode;

import com.sail.tree.TreeNode;

/**
 * @program: JavaDemo
 * @description: 对称二叉树
 * @author: sail
 * @create: 2019/05/31 19:35
 */

public class No101 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.setLchild(new TreeNode(2));
        root.setRchild(new TreeNode(2));

        TreeNode left = root.getLchild();
        left.setLchild(new TreeNode(3));
        left.setRchild(new TreeNode(4));

        TreeNode right = root.getRchild();
        right.setRchild(new TreeNode(3));
        right.setLchild(new TreeNode(5));
        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(TreeNode root){
        if (root==null){return true;}
        return Symmetric(root.getLchild(),root.getRchild());
    }

    public static boolean Symmetric(TreeNode left,TreeNode right){
        if (left==null&&right==null){
            return true;
        }
        if (left==null&&right!=null||left!=null&&right==null){
            return false;
        }
        if (left.getVal()!=right.getVal()){
            return false;
        }
        return Symmetric(left.getLchild(),right.getRchild())&&
                Symmetric(left.getRchild(),right.getLchild());
    }
}
