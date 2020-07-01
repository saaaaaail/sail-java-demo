package com.sail.leetcode.interview2020.回溯算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 *
 * 提示：
 *
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自 百度百科 - 皇后 ）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO51_8皇后问题 {

    public static void main(String[] args) {
        solveNQueens(4);
    }

    /**
     * 简单回溯
     * 思路 使用i一行一行的确认，每一行使用j判断有没有根前i-1行的点冲突
     * 如果冲突了返回上一行 选择的j开始
     * 因此要用到i表示行数
     * j表示列数
     * posi[i]表示i这一行确认的列数，必须要记录，返回到上一行的时候也方便
     * 要注意返回上一行的时候清理这一行的posi[i]的值为0，避免回溯回去了以后再次到这一行时，使用了上一次回溯的旧数据
     * 判断是不是在一行 采用遍历前i-1行的posi数组，
     * 横纵坐标之和相等或者横纵坐标只差相等表示在同一斜线上
     * 纵坐标相等表示在同一竖线上，同一横线不可能
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        boolean[][] visit = new boolean[n][n];
        int i=0;
        int j=0;
        StringBuilder sb = new StringBuilder();
        List<int[]> result = new ArrayList<>();
        int[] posi = new int[n];
        while(i<n&&i>=0){
            j=posi[i];
            if(visit[i][j]){
                visit[i][j]=false;
                j++;
            }

            boolean isLine = false;
            do{
                isLine = false;
                for(int k=0;k<i;k++){
                    if((posi[k]==j||k+posi[k]==i+j||k-posi[k]==i-j)){
                        isLine = true;
                        break;
                    }
                }
                j++;
            }while (isLine&&j-1<n);
            j--;
            if(j<n){
                posi[i]=j;
                visit[i][j] = true;
                i++;
                if(i==n){
                    for(int tmpI:posi){
                        sb.append(tmpI).append(" ");
                    }
                    sb.append("\n");
                    i--;
                }
            }else{
                posi[i]=0;
                i--;
            }
        }

        System.out.println(sb.toString());

        return null;
    }
}
