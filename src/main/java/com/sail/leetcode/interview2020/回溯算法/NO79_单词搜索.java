package com.sail.leetcode.interview2020.回溯算法;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO79_单词搜索 {

    /**
     * dfs 6ms 92.08%
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean result = false;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]!=word.charAt(0)){
                    continue;
                }
                result = doExist(board,visited,i,j,word,0);
                if(result){
                    return result;
                }
            }
        }
        return result;
    }

    public boolean doExist(char[][] board,boolean[][] visited,int x,int y,String word,int index){

        char c = word.charAt(index);
        if(visited[x][y]||board[x][y]!=c){
            return false;
        }
        if(index==word.length()-1){
            return true;
        }
        visited[x][y]=true;
        boolean res = false;
        if(x-1>=0&&!visited[x-1][y]){
            res =res||doExist(board,visited,x-1,y,word,index+1);
        }
        if(x+1<board.length&&!visited[x+1][y]){
            res =res||doExist(board,visited,x+1,y,word,index+1);
        }
        if(y-1>=0&&!visited[x][y-1]){
            res =res||doExist(board,visited,x,y-1,word,index+1);
        }
        if(y+1<board[0].length&&!visited[x][y+1]){
            res =res||doExist(board,visited,x,y+1,word,index+1);
        }

        visited[x][y]=false;
        return res;
    }
}
