package com.sail.tree;

public class TreeNode implements Comparable<TreeNode>{
    private int val;
    private TreeNode rchild;
    private TreeNode lchild;
    private TreeNode parent;
    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getRchild() {
        return rchild;
    }

    public void setRchild(TreeNode rchild) {
        this.rchild = rchild;
    }

    public TreeNode getLchild() {
        return lchild;
    }

    public void setLchild(TreeNode lchild) {
        this.lchild = lchild;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(TreeNode o) {
        return this.val-o.val;
    }
}
