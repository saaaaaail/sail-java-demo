package com.sail.tree;

public class RBTree {

    private static final boolean RED =true;
    private static final boolean BLACK=false;

    private RBTreeNode root=null;

    public boolean isRed(RBTreeNode node){
        if (node==null){return false;}
        return node.color;
    }

    public int size(RBTreeNode node){
        return node.N;
    }

    //右孩子左旋，方向向上转
    public RBTreeNode rotateLeft(RBTreeNode node){
        RBTreeNode tmp = node.right;
        node.right=tmp.left;
        tmp.left=node;

        tmp.color=node.color;
        node.color=RED;

        tmp.N=node.N;
        node.N=1+size(node.left)+size(node.right);
        return tmp;
    }

    //左孩子右旋，方向向上转
    public RBTreeNode rotateRight(RBTreeNode node){
        RBTreeNode tmp = node.right;
        node.left=tmp.right;
        tmp.right=node;

        tmp.color=node.color;
        node.color=RED;

        tmp.N=node.N;
        node.N=size(node.left)+size(node.right)+1;
        return tmp;
    }

    public void flipColors(RBTreeNode node){//颜色翻转
        node.left.color=BLACK;
        node.right.color=BLACK;
        node.color=RED;
    }

    public RBTreeNode put(int key, int val){
        root = put(root,key,val);
        root.color=BLACK;
        return root;
    }

    public RBTreeNode put(RBTreeNode node, int key, int val){
        if(node==null){
            node=new RBTreeNode(key,val,1,RED);
        }
        if(key<node.key){
            node.left=put(node.left,key,val);
        }else if(key>node.key){
            node.right=put(node.right,key,val);
        }else {
            node.value = val;
        }
        if (!isRed(node.left)&&isRed(node.right)){rotateLeft(node);}
        if (isRed(node.left)&&isRed(node.left.left)){rotateRight(node);}
        if (isRed(node.left)&&isRed(node.right)){flipColors(node);}
        node.N=size(node.right)+size(node.left)+1;
        return node;
    }


}
