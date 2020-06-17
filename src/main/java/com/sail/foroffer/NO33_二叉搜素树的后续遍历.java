package com.sail.foroffer;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 *  
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *  
 *
 * 提示：
 *
 * 数组长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO33_二叉搜素树的后续遍历 {
    public static void main(String[] args) {

    }

    public boolean verifyPostorder(int[] postorder) {
        return isPostorder(postorder,0,postorder.length);
    }

    /**
     * 思路：
     * 知识铺垫：首先要知道后续遍历序列最后一个节序号表示根root
     * 前面的序列存在一个分界点，使得分界点前面的数都小于root，分界点后面的数都大于root
     * 关键点是数组的边界问题
     * leftIndex表示大于等于，rightIndex表示小于，统一标准减少麻烦
     *
     * @param postorder
     * @param leftIndex
     * @param rightIndex
     * @return
     */
    public boolean isPostorder(int[] postorder,int leftIndex,int rightIndex){
        /**
         * 这个范围要一直扩展到仅含一个数字的时候，即子树只有头节点的时候直接返回true
         */
        if(rightIndex-leftIndex<=1){
            return true;
        }
        int root = postorder[rightIndex-1];

        //然后寻找分界点
        int k=leftIndex;
        while(postorder[k]<root&&k<rightIndex-1){
            k++;
        }
        //分界点后到rightIndex-1的数有小于root的就说明不是搜素树直接返回false
        for(int i=k;i<rightIndex-1;i++){
            if(postorder[i]<root){
                return false;
            }
        }

        //判断左右子树是不是
        return (isPostorder(postorder,leftIndex,k) && isPostorder(postorder,k,rightIndex-1));
    }
}
