package com.sail.tree;

public class RBTreeNode {
    int key;
    int value;
    int N;//当前子树的节点总数
    RBTreeNode left;
    RBTreeNode right;
    boolean color;//RED:true BLACK:false

    public RBTreeNode(){}

    public RBTreeNode(int key, int value, int n, boolean color) {
        this.key = key;
        this.value = value;
        N = n;
        this.color = color;
    }


}
