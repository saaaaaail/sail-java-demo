package com.sail.leetcode.笔试题.拼多多;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/9/1 19:29
 */

public class 多多的野蛮六 {

    private static int res = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] games = new int[n][m];
        for (int i=0;i<games.length;i++){
            for (int j=0;j<games[0].length;j++){
                games[i][j] = scanner.nextInt();
            }
        }
        int land = 0;
        boolean[][] visited = new boolean[n][m];
        char[][] flags = new char[n][m];
        char C = 'A';
        for (int i=0;i<games.length;i++){
            for (int j=0;j<games.length;j++){
                if (games[i][j]==1&&!visited[i][j]) {
                    link=0;
                    land += countLand(games, i, j, visited);
                    C = (char)(C+1);
                    ranse(games,i,j,new boolean[n][m],link,flags,C);
                }
            }
        }

        for (int i=0;i<games.length;i++){
            for (int j=0;j<games.length;j++){
                if (games[i][j]==0) {
                    Set<Character> set = new HashSet<>();
                    int sum = 0;
                    int around = 0;

                    if (i-1>=0&&games[i-1][j]!=0){
                        if (!set.contains(flags[i-1][j])){
                            sum+=games[i-1][j];
                            set.add(flags[i-1][j]);
                            around++;
                        }
                    }
                    if (i+1<games.length&&games[i+1][j]!=0){
                        if (!set.contains(flags[i+1][j])){
                            sum+=games[i+1][j];
                            set.add(flags[i+1][j]);
                            around++;
                        }
                    }
                    if (j-1>=0&&games[i][j-1]!=0){
                        if (!set.contains(flags[i][j-1])){
                            sum+=games[i][j-1];
                            set.add(flags[i][j-1]);
                            around++;
                        }
                    }
                    if (j+1<games[0].length&&games[i][j+1]!=0){
                        if (!set.contains(flags[i][j+1])){
                            sum+=games[i][j+1];
                            set.add(flags[i][j+1]);
                            around++;
                        }
                    }
                    if (land>around){
                        sum++;
                    }
                    res = Math.max(res,sum);
                }
            }
        }
        System.out.println(res);
    }

    private static int link = 0;
    public static int countLand(int[][] games,int x,int y,boolean[][] visited){
        if (x<0||y<0||x>=games.length||y>=games[0].length||visited[x][y]||games[x][y]==0){
            return 1;
        }
        visited[x][y]=true;
        link++;
        countLand(games,x+1,y,visited);
        countLand(games,x-1,y,visited);
        countLand(games,x,y+1,visited);
        countLand(games,x,y-1,visited);
        return 1;
    }

    public static void ranse(int[][] games,int x,int y,boolean[][] visited,int link,char[][] gamesC,char C){
        if (x<0||y<0||x>=games.length||y>=games[0].length||visited[x][y]||games[x][y]==0){
            return ;
        }
        visited[x][y]=true;
        games[x][y] = link;
        gamesC[x][y] = C;
        ranse(games,x+1,y,visited,link,gamesC,C);
        ranse(games,x-1,y,visited,link,gamesC,C);
        ranse(games,x,y+1,visited,link,gamesC,C);
        ranse(games,x,y-1,visited,link,gamesC,C);

    }

    public static void dfsLand(int[][] games,int x,int y,boolean[][] visited,boolean hasMove,int land,int count){
        if (x<0||y<0||x>=games.length||y>=games[0].length||visited[x][y]||(!hasMove&&games[x][y]!=0)){
            return ;
        }
        if (games[x][y]==0&&hasMove){
            res = Math.max(res,count);
            return;
        }
        visited[x][y]=true;

        if (games[x][y]==0&&!hasMove){
            if (land>=3){
                count++;
            }
            dfsLand(games,x+1,y,visited,true,land,count);
            dfsLand(games,x-1,y,visited,true,land,count);
            dfsLand(games,x,y+1,visited,true,land,count);
            dfsLand(games,x,y-1,visited,true,land,count);
        }else if (games[x][y]!=0){
            count+=games[x][y];
            dfsLand(games,x+1,y,visited,true,land,count);
            dfsLand(games,x-1,y,visited,true,land,count);
            dfsLand(games,x,y+1,visited,true,land,count);
            dfsLand(games,x,y-1,visited,true,land,count);
        }

        visited[x][y]=false;

    }
}
