package com.sail.foroffer;

import com.sail.foroffer.pojo.TreeNode;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 *  
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 *  
 *
 * 限制：
 *
 * 1 <= 树的结点个数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO55_平衡二叉树2 {


    public static void main(String[] args) {
        Boolean t = new Boolean(false);
        NO55_平衡二叉树2 a = new NO55_平衡二叉树2();
        a.test(t);
        System.out.println(t);
    }

    public void test(Boolean t){
        t=true;
    }

    /**
     * 1ms 99.91% 用-1来表示不是平衡二叉树了
     */
    public boolean isBalanced3(TreeNode root) {
        if(root==null){
            return true;
        }
        if(doIsBalanced3(root)!=-1){
            return true;
        }
        return false;
    }

    public int doIsBalanced3(TreeNode node){
        if(node==null){
            return 0;
        }
        if(node.left==null&&node.right==null){
            return 1;
        }
        int leftDepth = doIsBalanced3(node.left);
        int rightDepth = doIsBalanced3(node.right);
        if(leftDepth!=-1&&rightDepth!=-1&&Math.abs(leftDepth-rightDepth)<=1){
            return Math.max(leftDepth,rightDepth)+1;
        }else{
            return -1;
        }
    }

    /**
     * 24MS 8.8% 还是不太行吖 返回结果 使用字符串拼接 是否是平衡树 与 当前树的高度
     */
    public boolean isBalanced2(TreeNode root) {
        if(root==null){
            return true;
        }
        String[] results = doIsBalanced2(root).split(",");
        return Boolean.parseBoolean(results[0]);
    }

    public String doIsBalanced2(TreeNode node){
        if(node == null){
            return "true,0";
        }
        if(node.left == null && node.right == null){
            return "true,1";
        }
        String leftStr = doIsBalanced2(node.left);
        String rightStr = doIsBalanced2(node.right);
        String[] leftStrs = leftStr.split(",");
        String[] rightStrs = rightStr.split(",");
        int leftDepth = Integer.parseInt(leftStrs[1]);
        int rightDepth = Integer.parseInt(rightStrs[1]);
        String result = "";
        if(leftStrs[0].equals("true")&&rightStrs[0].equals("true")&&Math.abs(leftDepth-rightDepth)<=1){
            result+="true,";
        }else{
            result+="false,";
        }
        int depth = Math.max(leftDepth+1,rightDepth+1);
        return result + depth;
    }

    /**
     * 133ms 8% 超慢递归
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        boolean balance = false;
        if(Math.abs(depth(root.left)-depth(root.right))<=1){
            balance = true;
        }
        return isBalanced(root.left)&&isBalanced(root.right)&&balance;
    }

    public int depth(TreeNode node){
        if(node == null){
            return 0;
        }
        if(node.left==null&&node.right==null){
            return 1;
        }

        return Math.max(depth(node.left)+1,depth(node.right)+1);

    }
}
