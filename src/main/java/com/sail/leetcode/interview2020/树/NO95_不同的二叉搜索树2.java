package com.sail.leetcode.interview2020.树;

import com.sail.leetcode.interview2020.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 *  
 *
 * 示例：
 *
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *  
 *
 * 提示：
 *
 * 0 <= n <= 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO95_不同的二叉搜索树2 {

    public static void main(String[] args) {
        List<Integer>[][] d = new List[2][2];
    }

    private List<TreeNode>[][] d;
    public List<TreeNode> generateTrees(int n) {
        d = new List[n+1][n+1];
        return doGenerateTrees(1,n);

    }

    /**
     * 1ms 100%
     * 分治法
     * 首先树的节点编号为1~n，从其中随意取一个k作为根节点
     * 那么比k小的点1~k-1组成的树都应该位于左孩子
     * 比k大的点k+1~n组成的树都应该位于右孩子
     * 要统计所有情况，因此要将1~n的每个点都当作中间节点
     * 对于左右孩子的所有情况应该采用双层循环遍历一下，这样就能得到当前节点的所有情况
     * 每一层从left到right的所有情况合起来就是这一段节点的所有情况
     *
     * 还采用了缓存暂存从left到right已经计算过的所有情况。
     * @param left
     * @param right
     * @return
     */
    public List<TreeNode> doGenerateTrees(int left,int right){
        if(left>right){
            return new ArrayList<>();
        }
        if(d[left][right]!=null){
            return d[left][right];
        }
        List<TreeNode> ans = new ArrayList<>();
        for(int i=left;i<=right;i++){
            List<TreeNode> leftList = doGenerateTrees(left,i-1);
            List<TreeNode> rightList = doGenerateTrees(i+1,right);
            if(leftList.size()==0){
                leftList.add(new TreeNode(-1));
            }
            if(rightList.size()==0){
                rightList.add(new TreeNode(-1));
            }

            for(TreeNode leftNode:leftList){
                for(TreeNode rightNode:rightList){
                    TreeNode node = new TreeNode(i);
                    if(leftNode.val==-1){
                        node.left = null;
                    }else{
                        node.left = leftNode;
                    }
                    if(rightNode.val==-1){
                        node.right = null;
                    }else{
                        node.right = rightNode;
                    }
                    ans.add(node);
                }
            }
        }
        d[left][right]=ans;
        return ans;
    }
}
