package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO337_打家劫舍3 {

    /**
     * 记忆化搜索 3ms 58.8%
     * 一开始用的层序遍历，发现有问题，原因是我可以连续两层不偷直接偷一层最大的即可，这种例子层序遍历解决不了
     * 所以用dfs+缓存数组
     * 两种情况，
     * 1、一种是父节点被选，则当前节点不能选，直接选当前节点的孩子
     * 2、另一种，父节点没有被选，当前节点又是两种情况:
     *      1、选当前节点
     *      2、不选当前节点
     *      判断这两种情况，哪种和更大，返回较大值
     * 最后缓存数组保存的是父节点没有被选的情况即 d[node] = dfs(node,false);
     * 因为即便是父节点被选了，dfs(node,true) = dfs(node.left,false)+dfs(node.right,false); 仍然可以转化为求d[node]
     */
    Map<TreeNode,Integer> map = new HashMap<>();
    public int rob(TreeNode root) {
        if(root==null){
            return 0;
        }
        return dfs(root,false);
    }
    public int dfs(TreeNode node, boolean isFChoose){

        if(node==null){
            return 0;
        }
        if(isFChoose){
            return dfs(node.left,false)+dfs(node.right,false);
        }else{
            if(map.containsKey(node)){
                return map.get(node);
            }
            int rob1 = node.val+dfs(node.left,true)+dfs(node.right,true);
            int rob2 = dfs(node.left,false)+dfs(node.right,false);
            int sum = Math.max(rob1,rob2);
            map.put(node,sum);
            return sum;
        }

    }
}
