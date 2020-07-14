package com.sail.leetcode.interview2020.数组;

/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 *
 * 不占用额外内存空间能否做到？
 *
 *  
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * 示例 2:
 *
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 面试题_旋转矩阵 {
    /**
     * 要求原地顺时针旋转矩阵90度
     *
     * 通过矩阵翻转与交换操作完成
     */
    public void rotate(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return ;
        }
        int n = matrix.length;
        /**
         * 首先，将矩阵以[i，i]对角线完成翻转，将右上翻转到左下，左下翻转到右上
         */
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        /**
         * 然后将每一行i都完成reverse操作，就能得到
         */
        for(int i=0;i<n;i++){
            int l=0;
            int r=matrix[i].length-1;
            while(l<r){
                int tmp = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = tmp;
                l++;
                r--;
            }
        }

    }
}
