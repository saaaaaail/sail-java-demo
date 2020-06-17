package com.sail.foroffer;

import java.util.*;

public class NO38_字符串的全排列 {
    public static void main(String[] args) {
        permutation1("aab");
    }

    public static String[] permutation1(String s) {
        char[] c = s.toCharArray();
        boolean[] visit = new boolean[c.length];
        Set<String> result = new HashSet<>();
        LinkedList<String> one = new LinkedList<>();
        func(result,c,visit,one);
        return result.toArray(new String[0]);
    }


    /**
     * dfs递归，回溯法，寻找没有访问过的字符
     * 要注意 添加字符与删除字符操作的是同一个元素，这里通过优先删除后面的元素来保证
     * @param result
     * @param c
     * @param visit
     * @param one
     */
    public static void func(Set<String> result,char[] c,boolean[] visit,LinkedList<String> one){

        for(int i=0;i<c.length;i++){
            if(!visit[i]){
                visit[i]=true;
                one.add(c[i]+"");
                if(one.size()==c.length){
                    StringBuilder sb = new StringBuilder();
                    for (String s : one) {
                        sb.append(s);
                    }
                    result.add(sb.toString());
                }else{
                    func(result,c,visit,one);
                }
                one.removeLastOccurrence(c[i]+"");
                visit[i]=false;
            }
        }


    }



    public String[] permutation2(String s) {
        char[] c = s.toCharArray();

        Set<String> result = new HashSet<>();

        dfs(result,c,0);
        return result.toArray(new String[0]);
    }

    /**
     * dfs递归 交换法，在原char数组上操作，好方法
     * @param result
     * @param c
     * @param target
     */
    public void dfs(Set<String> result,char[] c,int target){
        if(target==c.length-1){
            result.add(String.valueOf(c));
            return;
        }

        for(int i=target;i<c.length;i++){
            swap(c,target,i);
            dfs(result,c,target+1);
            swap(c,target,i);
        }
    }

    public void swap(char[] c,int x,int y){
        char tmp = c[x];
        c[x]=c[y];
        c[y]=tmp;
    }
}
