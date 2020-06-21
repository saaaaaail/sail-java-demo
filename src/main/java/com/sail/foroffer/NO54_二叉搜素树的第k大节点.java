package com.sail.foroffer;

import com.sail.foroffer.pojo.TreeNode;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO54_二叉搜素树的第k大节点 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode node = new TreeNode(1);
//        TreeNode node1 = new TreeNode(6);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(4);
//        TreeNode node4 = new TreeNode(1);
//        root.left = node;
//        root.right = node1;
//        node.left = node2;
//        node.right = node3;
//        node2.left = node4;
        root.left = node;
        System.out.println(    kthLargestTopK(root,2));
    }

    /**
     * 第三种方法，右 中 左 的中序遍历的方式，找到k就停止
     */

    /**
     * 第二种方式 topK的方式 100%
     * 思路就是 找到根的最右子节点，然后删除 并且 k--
     * 删除的时候就有问题了
     * 1、删除的这个节点A呢，如果没有左孩子，直接删掉即可
     * 2、如果有左孩子，
     * 3、就陷入到递归里了，要删除这个点A就要找到其前驱节点pre来代替他
     * 4、众所周知，前驱节点pre是其左孩子的最右子节点，所以要删除这个pre
     * 5、要先找到他，找到pre以后呢
     * 6、如果pre就是A的左孩子，那么简单，A的父节点的右孩子指向这个pre即可，就把A给删了
     * 7、如果不是呢，pre是左孩子的最右子节点，就得把pre的值放到A里面，表示pre移到A了，原来的pre就得删掉
     * 8、为了删掉这个pre就回到第 1 步了，要删的这个pre就是第 1 步的A
     */
    public static int kthLargestTopK(TreeNode root, int k) {
        if(root == null){
            return 0;
        }
        int result = 0;
        while(k>0){
            TreeNode parent = null;
            TreeNode tmp = root;
            while(tmp.right!=null){
                parent = tmp;
                tmp = tmp.right;
            }
            result = tmp.val;
            k--;
            if(k==0){break;}

            root = deleteTreeNode(parent,tmp,root);

        }
        return result ;

    }

    /**
     * 这个方法就是用来删除最右子节点的方法，我居然撸出来了，神奇
     * @param nodeParent
     * @param node
     * @param root
     * @return
     */
    public static TreeNode deleteTreeNode(TreeNode nodeParent,TreeNode node,TreeNode root){
        TreeNode pre = node.left;
        if(pre==null){
            if(nodeParent==null){
                node = null;
                root = null;
            }else{
                nodeParent.right = null;
            }
        }else{
            TreeNode preParent = node;
            while(pre.right!=null){
                preParent = pre;
                pre = pre.right;
            }
            if(pre==node.left){
                if(nodeParent==null){
                    node.left = null;
                    node = pre;
                    root = node;
                }else{
                    nodeParent.right = pre;
                    node.left = null;
                    node = pre;
                }
            }else{
                node.val = pre.val;
                return deleteTreeNode(preParent,pre,root);
            }
        }
        return root;
    }


    /**
     * 第一种方式 50%
     * 二叉搜素树先转成双向链表，然后求第k大的
     * @param root
     * @param k
     * @return
     */
    public static  int kthLargest(TreeNode root, int k) {
        TreeNode tail = root;
        while(tail.right!=null){
            tail = tail.right;
        }
        kthLargest(root);
        while(k>1){
            tail = tail.left;
            k--;
        }
        return tail.val;


    }

    /**
     * 转成双向链表
     * @param node
     */
    public static void kthLargest(TreeNode node){
        if(node==null){
            return ;
        }

        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        TreeNode preNode = leftNode;
        if(preNode!=null){
            while(preNode.right!=null){
                preNode = preNode.right;
            }
        }
        TreeNode postNode = rightNode;
        if(postNode!=null){
            while(postNode.left!=null){
                postNode = postNode.left;
            }
        }

        kthLargest(node.left);
        kthLargest(node.right);

        node.left = preNode;
        //首先 一个节点的前驱不为空，后继不为空 那一定是他的左右孩子，如果前序为空或后续为空则，左右孩子必定为空
        //这里是要判断其前驱节点的后继节点有没有赋值，如果没有赋值一定为空
        //因为前序是左孩子的最右孩子，前序的右孩子一定为空
        if(preNode!=null&&preNode.right==null){
            preNode.right = node;
        }
        node.right = postNode;
        if(postNode!=null&&postNode.left==null){
            postNode.left = node;
        }
    }
}
