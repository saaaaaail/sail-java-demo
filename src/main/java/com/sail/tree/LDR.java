package com.sail.tree;

import java.util.ArrayDeque;

public class LDR {
    public static void ldr(TreeNode root){
        TreeNode tmpNode = root;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while(!stack.isEmpty()||tmpNode!=null){
            while (tmpNode!=null){
                stack.push(tmpNode);
                tmpNode=tmpNode.getLchild();
            }
            tmpNode = stack.pop();
            System.out.println(tmpNode.getVal());
            tmpNode = tmpNode.getRchild();
        }
    }
}
