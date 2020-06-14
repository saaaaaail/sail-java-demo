package com.sail.tree;

public class Tree {
    private static TreeNode root;
    public Tree(){
        root = new TreeNode();
    }
//5,3,6,2,4,null,null,1

    public static TreeNode getInstance(){
        root = new TreeNode();
        return createTree();
    }

    public static TreeNode createTree(){
        insertNode(5);  //5
        insertNode(3);  //3  6
        insertNode(6);  //2 4
        insertNode(2);  //1
        insertNode(4);
        insertNode(1);
        return root;
    }

    public static boolean insertNode(int val){
        TreeNode tmpNode = root.getLchild();

        if(tmpNode==null){
            tmpNode = new TreeNode();
            tmpNode.setVal(val);
            root.setLchild(tmpNode);
            return true;
        }
        while (tmpNode!=null){
            if(val==tmpNode.getVal()){
                return true;
            }else if(val>tmpNode.getVal()){
                if(tmpNode.getRchild()!=null){
                    tmpNode=tmpNode.getRchild();
                }else {
                    TreeNode node = new TreeNode();
                    node.setVal(val);
                    tmpNode.setRchild(node);
                    return  true;
                }

            }else{
                if(tmpNode.getLchild()!=null){
                    tmpNode=tmpNode.getLchild();
                }else {
                    TreeNode node = new TreeNode();
                    node.setVal(val);
                    tmpNode.setLchild(node);
                    return  true;
                }

            }
        }
        return false;
    }

    public static void main(String args[]){
        Tree tree = new Tree();
        TreeNode treeNode=tree.createTree();
        LDR.ldr(treeNode.getLchild());
        System.out.println(" ");
        DLR.dlr(treeNode.getLchild());
        System.out.println(" ");
        LRD.lrd(treeNode.getLchild());
        //层次遍历
        System.out.println(" ");
        LevelTree.levelTree(treeNode.getLchild());
        System.out.println(" ");
        ReverseTree.reverseTree(treeNode.getLchild());

        //层次遍历
        System.out.println(" ");
        LevelTree.levelTree(treeNode.getLchild());
    }
}
