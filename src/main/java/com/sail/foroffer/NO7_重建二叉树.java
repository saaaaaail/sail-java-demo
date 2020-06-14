package com.sail.foroffer;


import com.sail.tree.LevelTree;
import com.sail.tree.TreeNode;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，重建该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复数字
 * @author: sail
 * @create: 2019/05/30 12:02
 */

public class NO7_重建二叉树 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pre = sc.nextLine();
        String post = sc.nextLine();
        LevelTree.levelTree(constructTree(pre,"#"+post+"#"));
    }

    public static TreeNode constructTree(String pre, String post){
        char c = pre.charAt(0);
        TreeNode node = new TreeNode(c-'0');
        String[] splitPost = post.split(c + "");
        String leftPost = splitPost[0];
        String rightPost = splitPost[1];

        if (!leftPost.equals("#")){
            String leftPre = pre.substring(1,leftPost.length());
            TreeNode leftNode = constructTree(leftPre, leftPost + "#");
            node.setLchild(leftNode);
        }

        if (!rightPost.equals("#")){
            String rightPre = pre.substring(leftPost.length());
            TreeNode rightNode = constructTree(rightPre, "#" + rightPost);
            node.setRchild(rightNode);
        }
        return node;
    }
}
