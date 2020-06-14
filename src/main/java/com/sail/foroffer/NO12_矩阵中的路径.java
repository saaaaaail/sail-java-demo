package com.sail.foroffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemo
 * @description: 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以在矩阵任意一个位置开始，每一步可以在矩阵中向左、右、上、下移动一格。
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * @author: sail
 * @create: 2019/05/31 20:09
 */

public class NO12_矩阵中的路径 {

    public static void main(String[] args) {
        String[][] p = new String[][]{{"A","B","T","G"},{"C","F","C","G"},{"J","D","E","H"}};
        boolean[][] visited = new boolean[3][4];
        System.out.println(p[2][3]);
        String path = "BFCE";
        List<String> pathPos = new ArrayList<>();
        for (int i=0;i<p.length;i++){
            for (int j=0;j<p[0].length;j++){
                searchForNextNode(p,visited,i,j,path,0,pathPos);
            }
        }
    }

    public static boolean searchForNextNode(String[][] p,boolean[][] visited,int row,int col,String path,int pos,List<String> pathPos){
        String cur = p[row][col];
        String tar = String.valueOf(path.charAt(pos));
        if (cur.equals(tar)){
            visited[row][col]=true;
            pathPos.add(row+","+col);
            if (row+1<p.length&&!visited[row+1][col]&&pos+1<path.length()){
                searchForNextNode(p,visited,row+1,col,path,pos+1,pathPos);
            }
            if (row-1>=0&&!visited[row-1][col]&&pos+1<path.length()){
                searchForNextNode(p,visited,row-1,col,path,pos+1,pathPos);
            }
            if (col+1<p[0].length&&!visited[row][col+1]&&pos+1<path.length()){
                searchForNextNode(p,visited,row,col+1,path,pos+1,pathPos);
            }
            if (col-1>=0&&!visited[row][col-1]&&pos+1<path.length()){
                searchForNextNode(p,visited,row,col-1,path,pos+1,pathPos);
            }
            if (pos==path.length()-1){
                StringBuilder sb = new StringBuilder();
                for (int i=0;i<pathPos.size();i++){
                    sb.append("【"+pathPos.get(i)+"】");
                    if (i!=pathPos.size()-1){
                        sb.append("->");
                    }
                }
                System.out.println(sb.toString());
            }
            visited[row][col]=false;
            pathPos.remove(row+","+col);
        }
        return false;
    }

}
