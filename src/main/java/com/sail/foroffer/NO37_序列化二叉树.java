package com.sail.foroffer;

import com.sail.foroffer.pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO37_序列化二叉树 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootLeft = new TreeNode(2);
        TreeNode rootRight = new TreeNode(3);
        root.left = rootLeft;
        root.right = rootRight;
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(5);
        rootRight.left = left;
        rootRight.right = right;

        System.out.println(serialize(root));
        System.out.println(deserialize("[1,2,3,null,null,4,5]"));
    }


    /**
     * 思路就是层次遍历，将分别判断左右孩子为不为空改为，只判断node为不为空，
     * 这样左右孩子为空的仍然会入队占位置，出对的时候打印
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if(root==null){
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        queue.offerLast(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                TreeNode node = queue.removeFirst();
                sb.append(node!=null?node.val+",":"null,");
                if(node!=null){
                    queue.offerLast(node.left);
                    queue.offerLast(node.right);
                }

                size--;
            }
        }

        return sb.toString().substring(0,sb.length()-1)+"]";
    }

    /**
     * 反序列仍然是层次遍历，使用队列辅助
     * 每次从队列里取一个node，就从序列里面移两位出来作为左右孩子，同时把左右孩子入队，
     * 反序列化的时候左右孩子为空不需要入队
     * @param data
     * @return
     */
    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data==null||data.equals("")){
            return null;
        }
        String dataSplit = data.substring(1,data.length()-1);
        String[] nodeVals = dataSplit.split(",");
        return generateTree(nodeVals);

    }

    public static TreeNode generateTree(String[] nodeVals){
        int l = 0;
        TreeNode root = new TreeNode(Integer.parseInt(nodeVals[l++]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        while(!queue.isEmpty()&&l<nodeVals.length){
            TreeNode node = queue.removeFirst();
            String leftVal = nodeVals[l++];
            if(leftVal.equals("null")){
                node.left = null;
            }else{
                TreeNode leftNode = new TreeNode(Integer.parseInt(leftVal));
                node.left=leftNode;
                queue.offerLast(leftNode);
            }

            String rightVal = nodeVals[l++];
            if(rightVal.equals("null")){
                node.right = null;
            }else{
                TreeNode rightNode = new TreeNode(Integer.parseInt(rightVal));
                node.right=rightNode;
                queue.offerLast(rightNode);
            }
        }
        return root;
    }
}
