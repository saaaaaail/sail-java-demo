package com.sail.leetcode.interview2020.数组;

import java.util.LinkedList;

/**
 *给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO85_最大矩阵 {

    public static void main(String[] args) {
        NO85_最大矩阵 t = new NO85_最大矩阵();
        t.maximalRectangle2(new char[][]{{'1','1','0','1'},{'1','1','0','1'},{'1','1','1','1'}});
    }

    /**
     * 6ms 83.44%
     * 方法二 使用NO84求直方图里的最小矩阵面积，使用单调栈求解，可以将矩阵的每一层看作一个直方图，连续求i层即可
     */
    public int maximalRectangle2(char[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        int[] numsHeight = new int[matrix[0].length+2];
        int ans = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]=='1'){
                    numsHeight[j+1] +=1;
                }
            }
            ans = Math.max(ans,doMaximalRectangle(numsHeight));
        }
        return ans;
    }

    public int doMaximalRectangle(int[] nums){
        LinkedList<Integer> minStack = new LinkedList<>();
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            if(minStack.isEmpty()){
                minStack.push(i);
            }else{
                while(!minStack.isEmpty()&&nums[minStack.peek()]>nums[i]){
                    int h = minStack.pop();
                    sum = Math.max(sum,nums[h]*(i-minStack.peek()-1));
                }
            }
        }
        return sum;
    }

    /**
     * 暴力法 d[i][j]表示i、j为右下角的矩阵的最大面积
     * 依次遍历二维数组的每一个点，作为矩阵右下角，求最大矩阵面积
     * 怎么求呢？
     * 先求矩阵最底层的宽度，求面积
     * 然后高度上移一格，高度++，求这一层的宽度，与上一层宽度相比求最小宽度，高度乘以最小宽度，重复这一步骤，直到高度溢出或者当前点不是‘1’了
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        int[][] d = new int[matrix.length][matrix[0].length];
        int ans = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]=='0'){
                    continue;
                }
                d[i][j]='1';
                int height = 1;
                int x = i;
                int y = j;
                int w = Integer.MAX_VALUE;
                int sum = 1;
                while(true){

                    int width = 1;
                    while(y-1>=0&&matrix[x][y-1]=='1'){
                        width++;
                        y--;
                    }
                    w = Math.min(w,width);
                    sum = Math.max(sum,w*height);
                    if(x-1<0||matrix[x-1][j]=='0'){
                        break;
                    }
                    height++;
                    x--;
                    y=j;
                }
                d[i][j]=sum;
                ans = Math.max(ans,d[i][j]);
            }
        }
        return ans;
    }
}
