package com.sail.leetcode.interview2020.树;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 通过次数60,946提交次数90,142
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO96_不同的二叉搜索树 {

    /**
     * 定义一个res暂存n的结果用于剪枝
     */
    private int[] res ;
    public int numTrees(int n) {
        if(n==0){
            return 0;
        }
        res =new int[n+1];
        return doNumTrees(n);
    }

    /**
     * 100% 思路在代码里面注释
     * @param count
     * @return
     */
    public int doNumTrees(int count){
        /**
         * 为了方便计算左右孩子的数目总和定义成1，当左孩子有2个节点，右孩子有3个节点，那么当前节点有多少种？
         * 是左孩子种类数乘以右孩子种类数 计算得到为6种，所以当一边没有孩子的时候，返回1方便乘积
         */
        if(count==0){
            return 1;
        }
        if(count==1){
            return 1;
        }
        /**
         * count数量已经计算过多少种的话直接返回
         */
        if(res[count]!=0){
            return res[count];
        }

        //减去当前节点，然后分配剩下的孩子节点到左右子树上
        int rest = count-1;
        /**
         * 进行遍历考虑所有剩余节点数量，在孩子里的分配情况，从左孩子为0，右孩子为rest 一直遍历到 左孩子为rest，右孩子为0
         * 求所有孩子种类的和，就是当前节点能表示树的总数
         */
        for(int left=0;left<=rest;left++){
            int subCount = doNumTrees(left) * doNumTrees(rest-left);
            res[count]+=subCount;
        }
        return res[count];

    }

}
