package com.sail.tree;

import java.util.ArrayDeque;

public class DLR {
    public static void dlr(TreeNode root){
        TreeNode tmpNode = root;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        //System.out.println(tmpNode.getVal());
        while(!stack.isEmpty()||tmpNode!=null){
            while (tmpNode!=null){
                System.out.println(tmpNode.getVal());
                stack.push(tmpNode);
                tmpNode=tmpNode.getLchild();
            }
            tmpNode = stack.pop();
            tmpNode=tmpNode.getRchild();
        }
    }
}
