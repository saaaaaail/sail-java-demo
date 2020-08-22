package com.sail.leetcode.interview2020.图论;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO130_被围绕的区域 {
    public static void main(String[] args) {
        NO130_被围绕的区域 t = new NO130_被围绕的区域();
        t.solve2(new char[][]{{'O','X','X','O','X'},{'X','O','O','X','O'},{'X','O','X','O','X'},{'O','X','O','O','O'},{'X','X','O','X','O'}});
    }

    /**
     * 2ms 98.14% 从边缘开始感染
     * 方法二 先从边界O点出发，将能到达的点O都改为-
     * 然后遍历二维数组将剩余的O都改为X，将-都改为O
     * 从边缘出发遍历的节点要少很多很多很多
     */
    public void solve2(char[][] board) {
        if(board==null||board.length==0||board[0].length==0){
            return ;
        }

        for(int j=0;j<board[0].length;j++){
            if(board[0][j]=='O'){
                doSolve2(board,0,j);
            }
        }

        for(int j=0;j<board[0].length;j++){
            if(board[board.length-1][j]=='O'){
                doSolve2(board,board.length-1,j);
            }
        }

        for(int i=0;i<board.length;i++){
            if(board[i][0]=='O'){
                doSolve2(board,i,0);
            }
        }
        for(int i=0;i<board.length;i++){
            if(board[i][board[0].length-1]=='O'){
                doSolve2(board,i,board[0].length-1);
            }
        }

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }
                if(board[i][j]=='-'){
                    board[i][j]='O';
                }
            }
        }
    }

    public void doSolve2(char[][] board,int x,int y){
        board[x][y]='-';

        if(x+1<board.length&&board[x+1][y]=='O'){
            doSolve2(board,x+1,y);
        }
        if(x-1>=0&&board[x-1][y]=='O'){
            doSolve2(board,x-1,y);
        }
        if(y+1<board[0].length&&board[x][y+1]=='O'){
            doSolve2(board,x,y+1);
        }
        if(y-1>=0&&board[x][y-1]=='O'){
            doSolve2(board,x,y-1);
        }
    }

    /**
     * 方法一
     * 58/59 dfs
     * 思路是遍历二维数组找点O是否能到达边界被感染，同时将路径上能到达边界的O点缓存到toBoard数组，结果还是超时
     * 最后遍历二维数组，如果点是O且不能到达边界，则改为X
     * @param board
     */
    public void solve1(char[][] board) {
        if(board==null||board.length==0||board[0].length==0){
            return ;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean[][] toBoard = new boolean[board.length][board[0].length];
        for(int i=1;i<board.length-1;i++){
            for(int j=1;j<board[0].length-1;j++){
                if(board[i][j]=='O'&&!toBoard[i][j]){
                    doSolve1(board,i,j,visited,toBoard);
                }
            }
        }
        for(int i=1;i<board.length-1;i++){
            for(int j=1;j<board[0].length-1;j++){
                if(board[i][j]=='O'&&!toBoard[i][j]){
                    board[i][j]='X';
                }
            }
        }
    }

    public boolean doSolve1(char[][] board,int x,int y,boolean[][] visited,boolean[][] toBoard){
        if((x==0||y==0||x==board.length-1||y==board[0].length-1)&&board[x][y]=='O'){
            return true;
        }
        if(toBoard[x][y]){
            return true;
        }
        visited[x][y] = true;

        boolean v = false;
        if(x+1<board.length&&board[x+1][y]=='O'&&!visited[x+1][y]){
            v = v||doSolve1(board,x+1,y,visited,toBoard);
        }
        if(x-1>=0&&board[x-1][y]=='O'&&!visited[x-1][y]){
            v = v||doSolve1(board,x-1,y,visited,toBoard);
        }
        if(y+1<board[0].length&&board[x][y+1]=='O'&&!visited[x][y+1]){
            v = v||doSolve1(board,x,y+1,visited,toBoard);
        }
        if(y-1>=0&&board[x][y-1]=='O'&&!visited[x][y-1]){
            v = v||doSolve1(board,x,y-1,visited,toBoard);
        }
        if(v){
            toBoard[x][y]=true;
        }
        visited[x][y]=false;
        return v;
    }
}
