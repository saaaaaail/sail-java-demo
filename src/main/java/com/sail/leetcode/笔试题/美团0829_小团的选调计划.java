package com.sail.leetcode.笔试题;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/8/29 16:25
 */

import java.util.Scanner;

/**
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 美团打算选调n名业务骨干到n个不同的业务区域，本着能者优先的原则，公司将这n个人按照业务能力从高到底编号为1~n。编号靠前的人具有优先选择的权力，每一个人都会填写一个意向，这个意向是一个1~n的排列，表示一个人希望的去的业务区域顺序，如果有两个人同时希望去某一个业务区域则优先满足编号小的人，每个人最终只能去一个业务区域。
 *
 * 例如3个人的意向顺序都是1 2 3，则第一个人去1号区域，第二个人由于1号区域被选择了，所以只能选择2号区域，同理第三个人只能选择3号区域。
 *
 * 最终请你输出每个人最终去的区域。
 *
 *
 *
 * 输入描述
 * 输入第一行是一个正整数n，表示业务骨干和业务区域数量。（n≤300）
 *
 * 接下来有n行，每行n个整数，即一个1~n的排列，第 i 行表示 i-1 号业务骨干的意向顺序。
 *
 * 输出描述
 * 输出包含n个正整数，第 i 个正整数表示第 i 号业务骨干最终去的业务区域编号。
 *
 *
 * 样例输入
5
1 5 3 4 2
2 3 5 4 1
5 4 1 2 3
1 2 5 4 3
1 4 5 2 3
 * 样例输出
 * 1 2 5 4 3
 *
 * 规则
 */
public class 美团0829_小团的选调计划 {

    private static boolean[] visited;
    private static int[] res ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        res = new int[num+1];
        int[][] datas = new int[num+1][num];
        visited = new boolean[num+1];
        for (int i=1;i<=num;i++){
            String s = sc.nextLine();
            String[] s1 = s.split(" ");
            for (int j=0;j<num;j++){
                datas[i][j]=Integer.parseInt(s1[j]);
            }
        }
        doSomething(datas);
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=num;i++){
            sb.append(res[i]);
            if(i!=num){
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void doSomething(int[][] datas){
        for(int i=1;i<datas.length;i++){
            for(int j=0;j<datas[0].length;j++){
//                System.out.println(i+" "+j);
                if(!visited[datas[i][j]]){
                    //刚开始记录的是每个资源分区去的人
//                    res[datas[i][j]] = i;
                    //结果是要记录每个人去的资源分区
                    visited[datas[i][j]] = true;
                    res[i] = datas[i][j];
                    break;
                }
            }
        }
    }
}
