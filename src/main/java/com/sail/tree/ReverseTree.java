package com.sail.tree;

public class ReverseTree {
    public static void reverseTree(TreeNode rootNode){
        if (rootNode==null){return;}

        TreeNode leftNode = rootNode.getLchild();
        TreeNode rightNode = rootNode.getRchild();

        rootNode.setRchild(leftNode);
        rootNode.setLchild(rightNode);

        reverseTree(leftNode);


        reverseTree(rightNode);

    }

    public static TreeNode getReverseTree(TreeNode rootNode){
        if (rootNode==null){
            return null;
        }
        TreeNode treeNode = new TreeNode(rootNode.getVal());
        treeNode.setRchild(getReverseTree(rootNode.getLchild()));
        treeNode.setLchild(getReverseTree(rootNode.getRchild()));
        return rootNode;
    }


}
