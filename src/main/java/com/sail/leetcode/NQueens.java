package com.sail.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: N皇后问题
 * @author: sail
 * @create: 2019/05/01 13:38
 */

public class NQueens {
    static int N;
    static boolean[][] matrix;
    static boolean[] mark;
    static boolean[] addx;
    static boolean[][] subx;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        matrix = new boolean[N][N];
        NQueens nQueens = new NQueens();
        mark = new boolean[N];//表示横坐标是否被占用
        addx = new boolean[2*N-1];//表示向上45°倾斜的斜边，这条斜边上的【横坐标+纵坐标】和相同
        subx = new boolean[2][N];//1为正，0为负 表示向下45°倾斜的斜边，这条斜边上的【横坐标-纵坐标】差相同
        System.out.println(nQueens.solveNQueens(N));
    }
    public List<List<String>> solveNQueens(int n) {
        List<String> list = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        NQueens(list,res,0);
        return res;
    }

    public void NQueens(List<String> list,List<List<String>> res,int col){
        if(col==N){
            list = new ArrayList<>();
            for(int i=0;i<N;i++){
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<N;j++){
                    sb.append((matrix[i][j])?"Q":".");
                }
                list.add(new String(sb));
            }
            res.add(list);
            return;
        }
        for(int i=0;i<N;i++){
            int add = i+col;
            int sub = i-col;
            if(addx[add]){continue;}
            if(sub>=0&&subx[1][sub]){continue;}
            if(sub<=0&&subx[0][Math.abs(sub)]){continue;}
            if(mark[i]){continue;}

            addx[add]=true;
            if(sub>=0){subx[1][sub]=true;}
            if(sub<=0){subx[0][Math.abs(sub)]=true;}
            mark[i]=true;

            matrix[i][col]=true;

            NQueens(list,res,col+1);

            matrix[i][col]=false;

            mark[i]=false;
            addx[add]=false;
            if(sub>=0){subx[1][sub]=false;}
            if(sub<=0){subx[0][Math.abs(sub)]=false;}
        }
    }

}
