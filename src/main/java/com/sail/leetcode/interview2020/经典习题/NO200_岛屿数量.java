package com.sail.leetcode.interview2020.经典习题;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *  
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO200_岛屿数量 {


    /**
     * 3ms 47.04% 递归
     * 依次遍历二维数组每一个点，遍历一个点的时候就把相邻能访问到的陆地都染成1即可
     * 其他点进去的时候如果是0就返回0，如果是陆地但是已经被访问了也返回0
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0){
            return 0;
        }
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        int result = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                result+=numIslands(grid,i,j,visit);
            }
        }
        return result;

    }

    public int numIslands(char[][] grid,int x,int y,boolean[][] visit){
        if(visit[x][y]||grid[x][y]=='0'){
            return 0;
        }
        visit[x][y]=true;

        if(x-1>=0){
            numIslands(grid,x-1,y,visit);
        }
        if(x+1<=grid.length-1){
            numIslands(grid,x+1,y,visit);
        }
        if(y-1>=0){
            numIslands(grid,x,y-1,visit);
        }
        if(y+1<=grid[0].length-1){
            numIslands(grid,x,y+1,visit);
        }

        return 1;

    }
}
