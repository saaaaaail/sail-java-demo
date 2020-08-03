package com.sail.leetcode.interview2020.回溯算法;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 *
 *
 * 一个数独。
 *
 *
 *
 * 答案被标成红色。
 *
 * Note:
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO37_解数独 {


    /**
     * 4ms 76.48% dfs 回溯 相当于遍历了所有情况，一旦找到答案就不再回溯直接返回
     *
     */
    //表示每一行，是否包含1~9的数
    boolean[][] row = new boolean[9][10];
    //表示每一列是否包含1~9的数
    boolean[][] col = new boolean[9][10];
    //表示每一个小九宫格是否包含1~9的数
    boolean[][] block = new boolean[9][10];

    public void solveSudoku(char[][] board) {

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.'){
                    int num = board[i][j]-'0';
                    row[i][num] = true;
                    col[j][num] = true;
                    //这个索引将数组分成9宫格 里面每一个索引表示一个小九宫格
                    /**
                     *   0 1 2
                     *   3 4 5
                     *   6 7 8
                     */
                    block[i/3*3+j/3][num] = true;
                }
            }
        }
        doSolveSudoku(board,0,0);

    }

    public boolean doSolveSudoku(char[][] board,int x,int y){
        /**
         * 如果一行里面y超出了界限，就初始化到下一行
         */
        if(y>=board[0].length){
            x++;
            y=0;
        }
        /**
         * 如果x到头了，说明0~x-1行都填满了，返回true
         */
        if(x==board.length){
            return true;
        }

        //对每一个格子都要判断9个数能不能填，通通尝试一遍
        for(int i=1;i<=9;i++){
            /**
             * 这里是过滤掉不为'.'的索引，如果y溢出了也得调整，x溢出了就返回true
             */
            while(y<board[0].length&&board[x][y]!='.'){
                y++;
                if(y>=board[0].length){
                    x++;
                    y=0;
                }
                if(x==board.length){
                    return true;
                }
            }

            /**
             * 如果其中有一个满足就不能填了，换下一个
             */
            if(row[x][i]||col[y][i]||block[x/3*3+y/3][i]){
                continue;
            }
            /**
             * 填写当前i进去
             */
            row[x][i] = true;
            col[y][i] = true;
            block[x/3*3+y/3][i] = true;
            board[x][y] = (char)(i+'0');

            boolean res = false;
            /**
             * 然后去计算下一个点
             */
            res= doSolveSudoku(board,x,y+1);
            if(res){
                //为true就不再恢复状态，直接返回，不然回溯到第一层，结果就没有了
                return true;
            }
            /**
             * 如果不为true，就恢复当前状态，给下一次填写创造条件
             */
            row[x][i] = false;
            col[y][i] = false;
            block[x/3*3+y/3][i] = false;
            board[x][y] = '.';
        }
        return false;
    }
}
