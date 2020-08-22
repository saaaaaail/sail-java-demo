package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO145_二叉树后序遍历_迭代写法 {

    /**
     * 1ms 53%
     * 思路:后序 的 左 右 中
     * 一定要左孩子右孩子都为空了才将当前节点出队
     * 1、首先，将所有左孩子依次入队
     * 2、当最左边的左孩子已经为空了以后，从队列里peek出最左边有效的节点
     * 3、判断其有没有右孩子，没有右孩子，则这个节点就可以出队打印了
     * 4、有右孩子，将右孩子子入队，然后继续将右孩子的所有左孩子依次入队，回到第2步
     * 5、如果右孩子没有左孩子或者所有左孩子已经处理完毕出队了，判断这个右孩子有没有右孩子
     * 6、有右孩子回到第4步，没有右孩子就将当前节点出队打印
     * 7、同时还要判断这个刚刚出队的元素是不是栈顶元素的右孩子，如果是继续将栈顶元素出队打印循环这一步
     * 8、同时出队的时候还要记录出队的节点，避免栈顶元素又进入了左孩子。
     *
     * 总结：
     * 总的思路就是当前栈顶元素不断将其左孩子全部入队，没有左孩子判断其有没有右孩子，将一个右孩子入队
     * 如果栈顶元素既没有左孩子也没有右孩子的话，将栈顶元素出队打印
     * 出队打印的时候有两个点要注意一下
     * 一是记录出队的节点，这是为了防止栈顶左孩子回溯到中间节点，中间节点再次将左孩子入队
     * 二是如果出队的元素是栈顶中间节点的右孩子，就直接再次将栈顶出队，循环这个过程
     * 因为根据左 右 中 顺序，右孩子出队完以后说明中间节点的左孩子已经全部打印了，
     * 为了避免回到中间节点的时候，又将左孩子入队，这里直接在右孩子后将中间节点出队
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        /**
         * 这个为了防止从左孩子回到中间节点的时候，再次进入左孩子
         */
        TreeNode lastNode = null;
        while(!stack.isEmpty()){
            TreeNode currentNode = stack.peek();
            TreeNode leftNode = currentNode.left;
            while(leftNode!=null&&leftNode!=lastNode){
                stack.push(leftNode);
                leftNode=leftNode.left;
            }
            currentNode = stack.peek();
            TreeNode rightNode = currentNode.right;

            if(rightNode==null){
                lastNode = stack.pop();
                result.add(lastNode.val);
                /**
                 * 下面这个是右孩子出队的话就直接将中间节点跟着出队，避免再次进入左孩子与右孩子
                 */
                while(!stack.isEmpty()&&stack.peek().right==lastNode){
                    lastNode = stack.pop();
                    result.add(lastNode.val);
                }
            }else{
                stack.push(rightNode);
            }
        }
        return result;
    }
}
