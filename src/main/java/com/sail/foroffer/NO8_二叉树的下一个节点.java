package com.sail.foroffer;

import com.sail.tree.TreeNode;

/**
 * @program: JavaDemo
 * @description: 二叉树的下一个节点
 * 给定一颗二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点？
 * 树中的节点除了有两个分别指向左、右子节点的指针，还有一个指向父节点的指针
 * @author: sail
 * @create: 2019/05/30 12:57
 */

public class NO8_二叉树的下一个节点 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode('a');
        root.setRchild(new TreeNode('c'));
        root.setLchild(new TreeNode('b'));

        TreeNode left = root.getLchild();
        left.setLchild(new TreeNode('d'));
        left.setRchild(new TreeNode('e'));

        TreeNode right = root.getRchild();
        right.setLchild(new TreeNode('f'));
        right.setRchild(new TreeNode('g'));

        left = left.getRchild();
        left.setRchild(new TreeNode('i'));
        left.setLchild(new TreeNode('h'));

        System.out.println(getNext(root,left));

    }

    public static TreeNode getNext(TreeNode root, TreeNode node){
        TreeNode right = node.getRchild();
        TreeNode parent = node.getParent();
        if (right!=null){
            /**
             * 当前节点的右孩子不为空就找右孩子的最左节点
             * 下一个节点为这个最左节点
             */
            while (right.getRchild()!=null){
                right=right.getRchild();
            }
            return right;
        }else if (parent!=null&&parent.getLchild()==node){
            /**
             * 右孩子为空，当前节点的父节点不为空，且父节点的左孩子为当前节点
             * 下一个节点为父节点
             */
            return parent;
        }else if (parent!=null&&parent.getRchild()==node){
            /**
             * 父节点不为空，并且父节点的右孩子为当前节点，就循环找父节点
             * 直到找到一个父节点其父父节点左孩子为父节点
             * 下一个节点为这个父父节点
             */
            TreeNode parpar = parent.getParent();
            while (parpar!=null&&parpar.getLchild()!=parent){
                parent=parpar;
                parpar=parent.getParent();
            }
            if (parpar!=null){
                return parpar;
            }
        }
        return null;
    }
}
