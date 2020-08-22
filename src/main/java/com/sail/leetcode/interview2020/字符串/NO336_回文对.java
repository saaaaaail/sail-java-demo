package com.sail.leetcode.interview2020.字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NO336_回文对 {
    public static void main(String[] args) {
        NO336_回文对 t = new NO336_回文对();
        List<List<Integer>> lists = t.palindromePairs(new String[]{"a","abc","aba",""});
        System.out.println(lists.toString());
    }

    /**
     * 196ms 19.50%
     * 思路 将每个字符及索引位置放入到map里暂存
     *      将每个字符的逆序字符暂存到reverseMap里暂存
     *
     *  然后遍历一遍数组 考虑以下几个情况
     *  1、当前单词的逆序等于自己，且自己不等于空串，如果字符串map里有空串，空串与自回文的单词能组成回文串，就将索引入结果集
     *  上面的情况考虑的就是空字符串的唯一满足情况，然后下面的情况就排除空字符串
     *  2、单词逆序不等于自己，但是字符串map里有自己的逆序，那么可以组合成回文
     *  3、在单词word里找一个拆分点，将单词拆分成两块，pre和post
     *      1、pre如果是回文，就判断字符串map里有没有post的逆序串rPost，如果有，rPost+word能组合成回文串，索引入结果集，顺序别弄反了
     *      2、post如果是回文，判断字符串map里有没有pre的逆序串rPre，如果有，word+rPre能组成回文串，以这个顺序入结果集
     *
     */
    Map<String,String> reverseMap = new HashMap<>();
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<words.length;i++){
            map.put(words[i],i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<words.length;i++){
            String word = words[i];
            String rs = reverse(word);

            /**
             * 单独考虑空串+单回文串
             */
            if(!word.equals("")&&rs.equals(word)&&map.containsKey("")&&map.get("")!=i){
                int index = map.get("");
                List<Integer> record = new ArrayList<>();
                record.add(i);
                record.add(index);
                ans.add(record);
                record = new ArrayList<>();
                record.add(index);
                record.add(i);
                ans.add(record);
            }

            /**
             * 考虑单词对应的逆序串
             */
            if(!word.equals("")&&!rs.equals(word)&&map.containsKey(rs)){
                int index = map.get(rs);
                List<Integer> record = new ArrayList<>();
                record.add(i);
                record.add(index);
                ans.add(record);

            }
            /**
             * 考虑单词中部分回文串
             */
            if(!word.equals("")){
                char[] c = word.toCharArray();
                for(int j=1;j<c.length;j++){
                    String pre = String.valueOf(c,0,j);
                    String post = String.valueOf(c,j,c.length-j);
                    String rPost = null;
                    String rPre = null;
                    if(ispalindrome(pre)&&map.containsKey(rPost=reverse(post))){
                        int index = map.get(rPost);
                        List<Integer> record = new ArrayList<>();
                        record.add(index);
                        record.add(i);
                        ans.add(record);
                    }
                    if(ispalindrome(post)&&map.containsKey(rPre=reverse(pre))){
                        int index = map.get(rPre);
                        List<Integer> record = new ArrayList<>();
                        record.add(i);
                        record.add(index);
                        ans.add(record);
                    }
                }
            }

        }
        return ans;
    }
    public boolean ispalindrome(String s){
        if(reverseMap.containsKey(s)){
            String rs = reverseMap.get(s);
            return rs.equals(s);
        }
        int l = 0;
        int r = s.length()-1;
        while(l<r){
            if(s.charAt(l++)!=s.charAt(r--)){
                return false;
            }
        }
        return true;
    }

    public String reverse(String s){
        if(reverseMap.containsKey(s)){
            return reverseMap.get(s);
        }
        char[] c = s.toCharArray();
        int l=0;
        int r=s.length()-1;
        while(l<r){
            char tmp  = c[l];
            c[l] = c[r];
            c[r] = tmp;
            l++;
            r--;
        }
        String rs = String.valueOf(c);
        reverseMap.put(s,rs);
        return rs;
    }
}
