package com.sail.leetcode.笔试题.美团;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/8/29 17:42
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小团是一个莫得感情的CtrlCV大师，他有一个下标从1开始的序列A和一个初始全部为-1的序列B，两个序列的长度都是n。他会进行若干次操作，每一次操作，他都会选择A序列中一段连续区间，将其粘贴到B序列中的某一个连续的位置，在这个过程中他也会查询B序列中某一个位置上的值。
 *
 * 我们用如下的方式表示他的粘贴操作和查询操作：
 *
 * 粘贴操作：1  k x y，表示把A序列中从下标x位置开始的连续k个元素粘贴到B序列中从下标y开始的连续k个位置上，原始序列中对应的元素被覆盖。（数据保证不会出现粘贴后k个元素超出B序列原有长度的情况）
 *
 * 查询操作：2 x，表示询问当前B序列下标x处的值是多少。
 *
 *
 *
 * 输入描述
 * 输入第一行包含一个正整数n，表示序列A和序列B的长度。(1<=n<=2000)
 *
 * 输入第二行包含n个正整数，表示序列A中的n个元素，第 i 个数字表示下标为 i 的位置上的元素，每一个元素保证在10^9以内。
 *
 * 输入第三行是一个操作数m，表示进行的操作数量。(1<=m<=2000)
 *
 * 接下来m行，每行第一个数字为1或2，具体操作细节详见题面。
 *
 * 输出描述
 * 对于每一个操作2输出一行，每行仅包含一个整数，表示针对某一个询问的答案。
 *
 *
 * 样例输入
 * 5
 * 1 2 3 4 5
 * 5
 * 2 1
 * 2 5
 * 1 2 3 4
 * 2 3
 * 2 5
 * 样例输出
 * -1
 * -1
 * -1
 * 4
 *
 * 提示
 * 输入样例2
 * 5
 * 1 2 3 4 5
 * 9
 * 1 2 3 4
 * 2 3
 * 2 5
 * 1 2 2 3
 * 2 1
 * 2 2
 * 2 3
 * 2 4
 * 2 5
 *
 * 输出样例2
 * -1
 * 4
 * -1
 * -1
 * 2
 * 3
 * 4
 */
public class 美团0829_小团复制粘贴 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());
        String[] asStr = sc.nextLine().split(" ");
        int[] as = new int[len+1];
        for(int i=1;i<as.length;i++){
            as[i] = Integer.parseInt(asStr[i-1]);
        }

        int[] bs = new int[len+1];
        Arrays.fill(bs,-1);
        int num = Integer.parseInt(sc.nextLine());
        int[][] op = new int[num][4];
        for(int i=0;i<num;i++){
            String[] line = sc.nextLine().split(" ");
            if (line[0].equals("1")){
                op[i][0] = 1;
                op[i][1] = Integer.parseInt(line[1]);
                op[i][2] = Integer.parseInt(line[2]);
                op[i][3] = Integer.parseInt(line[3]);
            }
            else if (line[0].equals("2")){
                op[i][0] = 2;
                op[i][1] = Integer.parseInt(line[1]);
            }
        }
        for (int i=0;i<op.length;i++){
            if (op[i][0]==1){
                int l = 0;
                int ax = op[i][2];
                int bx = op[i][3];
                while(l<op[i][1]){

                    bs[bx] = as[ax] ;
                    l++;
                    ax++;
                    bx++;
                }
            }else if (op[i][0]==2){
                System.out.println(bs[op[i][1]]);
            }
        }
    }
}
