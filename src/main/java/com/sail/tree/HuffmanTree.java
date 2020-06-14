package com.sail.tree;

import java.util.*;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] nums = {1,2,3,6,12,24};
        HuffmanTree h = new HuffmanTree();
        TreeNode root = h.huffmanTree(nums);
        h.printTree(root);

    }

    public void printTree(TreeNode node){
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()){
            TreeNode tmpNode = queue.remove();
            System.out.println(tmpNode.getVal());
            TreeNode leftNode = tmpNode.getLchild();
            TreeNode rightNode = tmpNode.getRchild();
            if (leftNode!=null){queue.add(leftNode);}
            if (rightNode!=null){queue.add(rightNode);}

        }

        System.out.println(" ");
    }

    public TreeNode huffmanTree(int[] nums){

        TreeNode leftNode;
        TreeNode rightNode;
        TreeNode mid=null;
        List<TreeNode> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            list.add(new TreeNode(nums[i]));
        }
        while (!list.isEmpty()&&list.size()!=1){

            Collections.sort(list,new TreeNodeComparator());

            leftNode = list.get(0);
            rightNode = list.get(1);

            list.remove(leftNode);
            list.remove(rightNode);

            mid = new TreeNode(leftNode.getVal()+rightNode.getVal());
            mid.setLchild(leftNode);
            mid.setRchild(rightNode);

            list.add(mid);
        }
        return mid;

    }

    class TreeNodeComparator implements Comparator<TreeNode>{
        @Override
        public int compare(TreeNode o1, TreeNode o2) {
            return o1.getVal()-o2.getVal();
        }
    }
}
