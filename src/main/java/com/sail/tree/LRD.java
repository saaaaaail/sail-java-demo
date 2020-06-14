package com.sail.tree;

import java.util.ArrayDeque;

public class LRD {
    public static void lrd(TreeNode root){
        int flag=0;
        TreeNode tmpNode = root;
        TreeNode lastNode=null;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty()||tmpNode!=null){
            while (tmpNode!=null){
                stack.push(tmpNode);
                tmpNode=tmpNode.getLchild();
            }
                tmpNode=stack.peek();
                if (tmpNode.getRchild()!=null&&tmpNode.getRchild()!=lastNode){
                    tmpNode=tmpNode.getRchild();

                }else{
                    tmpNode=stack.pop();
                    System.out.println(tmpNode.getVal());
                    lastNode=tmpNode;
                    tmpNode=null;
                }
        }
    }
}
