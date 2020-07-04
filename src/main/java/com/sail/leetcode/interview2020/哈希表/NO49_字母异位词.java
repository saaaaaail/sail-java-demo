package com.sail.leetcode.interview2020.哈希表;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO49_字母异位词 {


    public static void main(String[] args) {
        groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }


    /**
     * 思路就是 对异位词排序 然后用map存
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            String cur = strs[i];
            char[] c =  cur.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            System.out.println(key);
            List<String> list = map.getOrDefault(key,new ArrayList<>());
            list.add(cur);
            map.put(key,list);
        }

        for(List<String> list:map.values()){
            result.add(list);
        }
        return result;
    }

    /**
     * 还有一个思路
     * 将26个字母用前26个质数表示
     * 这样异位词的乘积一定是相同的，不是异位词的乘积一定是不同的
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        int[] c = new int[26];
        int l=1;
        //获得前26个质数，表示26个英文字母
        for(int i=0;i<26;i++){
            while(!isPrime(l)){
                l++;
            }
            c[i]=l++;
        }
        List<List<String>> result = new ArrayList<>();
        Map<Integer,List<String>> map = new HashMap<>();

        for(int i=0;i<strs.length;i++){
            String cur = strs[i];
            int pro = 1;
            for(int j=0;j<cur.length();j++){
                char tmp = cur.charAt(j);
                pro *=c[tmp-'a'];
            }

            List<String> list = map.getOrDefault(pro,new ArrayList<>());
            list.add(cur);
            map.put(pro,list);
        }
        for(List<String> list:map.values()){
            result.add(list);
        }
        return result;
    }

    /**
     * 判断一个数是不是质数
     * @param num
     * @return
     */
    public boolean isPrime(int num){
        if(num==1){
            return false;
        }
        for(int i=2;i<=num/2;i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}
