package com.sail.foroffer;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 *  
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *  
 *
 * 提示：
 *
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO47_礼物最大价值 {

    /**
     * 递归 毫不意外地 超时了
     */
    public int maxValue(int[][] grid){
        return maxValue(grid,0,0,0);
    }
    public int maxValue(int[][] grid,int x,int y,int lastGift) {
        int gift = lastGift + grid[x][y];
        if(x==grid.length-1&&y==grid[0].length-1){
            return gift;
        }
        return Math.max((x<grid.length-1?maxValue(grid,x+1,y,gift):0),(y<grid[0].length-1?maxValue(grid,x,y+1,gift):0));
    }

    /**
     * 动规思路
     * d[x][y] 保存 由0,0 -> x,y 坐标的最长路径的和
     * 那么
     * d[x][y] 有两条路可以走过来，一条是左边，一条是上面，比较这两条路中大的然后加上 grid[x][y]本身的值即可
     * d[x][y] = grid[x][y] + max(d[x-1][y],d[x][y-1])
     */
    public int maxValueDp(int[][] grid){
        int[][] d = new int[grid.length][grid[0].length];
        int sum = 0;
        for(int i=0;i<grid.length;i++){
            sum += grid[i][0] ;
            d[i][0] = sum;
        }
        sum = 0;
        for(int j=0;j<grid[0].length;j++){
            sum += grid[0][j];
            d[0][j] = sum;
        }
        for(int i=1;i<grid.length;i++){
            for(int j=1;j<grid[0].length;j++){
                d[i][j] = grid[i][j]+Math.max(d[i-1][j],d[i][j-1]);
            }
        }
        return d[grid.length-1][grid[0].length-1];
    }
}
