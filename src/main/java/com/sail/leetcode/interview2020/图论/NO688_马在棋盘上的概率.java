package com.sail.leetcode.interview2020.图论;

/**
 * 已知一个 NxN 的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为 (0, 0)，最右下角的记为 (N-1, N-1)。 
 *
 * 现有一个 “马”（也译作 “骑士”）位于 (r, c) ，并打算进行 K 次移动。 
 *
 * 如下图所示，国际象棋的 “马” 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。
 *
 *  
 *
 *
 *
 *  
 *
 * 现在 “马” 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，直到移动了 K 次或跳到了棋盘外面。
 *
 * 求移动结束后，“马” 仍留在棋盘上的概率。
 *
 *  
 *
 * 示例：
 *
 * 输入: 3, 2, 0, 0
 * 输出: 0.0625
 * 解释:
 * 输入的数据依次为 N, K, r, c
 * 第 1 步时，有且只有 2 种走法令 “马” 可以留在棋盘上（跳到（1,2）或（2,1））。对于以上的两种情况，各自在第2步均有且只有2种走法令 “马” 仍然留在棋盘上。
 * 所以 “马” 在结束后仍在棋盘上的概率为 0.0625。
 *  
 *
 * 注意：
 *
 * N 的取值范围为 [1, 25]
 * K 的取值范围为 [0, 100]
 * 开始时，“马” 总是位于棋盘上
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO688_马在棋盘上的概率 {

    /**
     * dfs的缓存数组，要包含剩余的k值，x、y坐标
     */
    double[][][] d ;
    public double knightProbability(int N, int K, int r, int c) {
        d  = new double[K+1][N][N];
        /**
         * 八种跳跃位置
         */
        int[][] moves = new int[][]{{1,2},{2,1},{1,-2},{2,-1},{-1,-2},{-2,-1},{-1,2},{-2,1}};
        return dfs(moves,N,K,r,c);

    }

    public double dfs(int[][] moves,int N,int k,int x,int y){

        if(x<0||x>=N||y<0||y>=N){
            return 0d;
        }

        if(k==0){
            return 1d;
        }
        if(d[k][x][y]!=0){
            return d[k][x][y];
        }

        double p=0d;
        /**
         * 遍历8种情况，如果出界了返回0，如果k结束了返回1，所以啊除了最后一层要么出界要么结束返回0跟1，
         * 其他层返回的都是一个小数 或者 0
         * 然后除以8返回上一层
         */
        for(int i=0;i<moves.length;i++){
            p+=dfs(moves,N,k-1,x+moves[i][0],y+moves[i][1]);
        }
        p/=8;
        d[k][x][y]=p;
        return p;
    }
}
