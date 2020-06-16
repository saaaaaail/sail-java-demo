package com.sail.foroffer;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO29_顺时针打印数组 {

    /**
     * 思路就是设立四个标尺
     * 横向起始sWidth 横向终点width
     * 竖向起始sHeigth 竖向终点height
     * 一轮循环下来，依次将标尺范围向内卷，直到起始标尺大于终点标尺
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {

        if(matrix==null || matrix.length==0 || matrix[0].length==0){
            return new int[0];
        }

        int sWidth = 0;
        int width = matrix[0].length-1;
        int sHeigth = 0;
        int height = matrix.length-1;
        int[] result = new int[(width+1)*(height+1)];

        int count=0;
        while(sWidth<=width&&sHeigth<=height){
            for(int i=sWidth;i<=width;i++){
                result[count++]=matrix[sHeigth][i];
            }
            sHeigth++;
            if(sHeigth>height){
                break;
            }
            for(int i=sHeigth;i<=height;i++){
                result[count++]=matrix[i][width];
            }
            width--;
            if(sWidth>width){
                break;
            }
            for(int i=width;i>=sWidth;i--){
                result[count++]=matrix[height][i];
            }
            height--;
            if(sHeigth>height){
                break;
            }
            for(int i=height;i>=sHeigth;i--){
                result[count++]=matrix[i][sWidth];
            }
            sWidth++;
        }
        return result;
    }
}
