package com.sail.foroffer;

import com.sail.foroffer.pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 *  
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO32_从上到下打印二叉树 {

    /**
     * 就是层次遍历，通过while(size>0)条件分层
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if(root==null){
            return new int[0];
        }
        LinkedList<TreeNode> queue = new LinkedList();
        List<Integer> list = new ArrayList();
        queue.offerLast(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                TreeNode node = queue.pollFirst();
                list.add(node.val);

                if(node.left!=null){
                    queue.offerLast(node.left);
                }
                if(node.right!=null){
                    queue.offerLast(node.right);
                }
                size--;
            }

        }

        int[] result = new int[list.size()];
        for(int i=0;i<result.length;i++){
            result[i]=list.get(i);
        }
        return result;
    }
}
