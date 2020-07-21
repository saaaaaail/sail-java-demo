package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 *
 *
 *
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 *
 *
 *
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO1028_根据先序遍历还原二叉树 {

    public static void main(String[] args) {
        NO1028_根据先序遍历还原二叉树 t = new NO1028_根据先序遍历还原二叉树();
        t.recoverFromPreorder2("1-2--3--4-5--6--7");
    }

    /**
     * 第二种方法，不用切割字符串，仅仅移动字符索引位置，记录节点层数与节点值
     * 然后入栈，如果栈为空，直接入栈
     * 如果栈不为空，判断栈里元素数量等于当前节点层数，说明是栈顶元素刚来的左孩子，标记为左孩子然后入栈
     * 如果再次新来的元素层数等于栈元素数量，说明 新来的元素是栈顶的左孩子
     * 如果新来的元素层数小于栈元素的数量，当前栈顶的孩子已经全部走完了，将栈顶元素出栈直到栈元素数量等于新来的元素层次
     * 这时候为右孩子
     *
     */
    public TreeNode recoverFromPreorder2(String S) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        int pos = 0;


        while(pos<S.length()){
            int deep = 0;
            while(S.charAt(pos)=='-'){
                pos++;
                deep++;
            }
            int value=0;
            while(pos<S.length()&&Character.isDigit(S.charAt(pos))){
                value = value*10+S.charAt(pos++)-'0';
            }
            TreeNode node = new TreeNode(value);
            if(stack.isEmpty()){
                stack.push(node);
            }else{
                /**
                 * stack的元素数量就是栈的深度
                 */
                if(stack.size()==deep){
                    stack.peek().left=node;
                }else{
                    while(stack.size()>deep){
                        stack.pop();
                    }
                    stack.peek().right=node;
                }
                stack.push(node);
            }
        }
        while(stack.size()>1){
            stack.pop();
        }
        return stack.pop();
    }

    /**
     * 这题的难点，，，感觉在字符串的切分
     * 第一种方法，疯狂切分字符串，还有正则匹配，导致效率低的一批
     * @param S
     * @return
     */
    public TreeNode recoverFromPreorder(String S) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(S);
        if(matcher.find()){
            TreeNode head = new TreeNode(Integer.parseInt(S.substring(0,matcher.end())));
            return doRecoverFromPreorder(head,1,S.substring(matcher.end()));
        }
        return null;
    }

    public TreeNode doRecoverFromPreorder(TreeNode node, int deep, String S){
        if(S==null||S.equals("")){
            return node;
        }
        String flag = "";
        int d = deep;
        while(d>0){
            flag +="-";
            d--;
        }
        String regexStart = flag+"[0-9]+";
        Pattern patternStart = Pattern.compile(regexStart);
        Matcher matcherStart = patternStart.matcher(S);
        TreeNode leftNode = null;
        if(matcherStart.find()){
            String nodeVal = S.substring(matcherStart.start()+flag.length(),matcherStart.end());
            leftNode = new TreeNode(Integer.parseInt(nodeVal));
            S = S.substring(matcherStart.end()-1);
        }


        String regexEnd = "[0-9]"+flag+"[0-9]+";
        Pattern patternEnd = Pattern.compile(regexEnd);
        Matcher matcherEnd = patternEnd.matcher(S);
        if(matcherEnd.find()){
            String nodeVal = S.substring(matcherEnd.start()+flag.length()+1,matcherEnd.end());
            TreeNode rigthNode = new TreeNode(Integer.parseInt(nodeVal));
            String leftStr = S.substring(1,matcherEnd.start()+1);
            String rightStr = S.substring(matcherEnd.end());
            if(leftNode!=null){
                node.left = doRecoverFromPreorder(leftNode,deep+1,leftStr);
            }
            node.right = doRecoverFromPreorder(rigthNode,deep+1,rightStr);
        }else{
            if(leftNode!=null){
                node.left = doRecoverFromPreorder(leftNode,deep+1,S.substring(1));
            }
        }

        return node;
    }
}
