package com.sail.leetcode.interview2020.经典习题;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * 通过次数58,326提交次数153,589
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO76_最小覆盖子串 {
    public static void main(String[] args) {


        Map<Integer,Integer> map = new HashMap<>();
        System.out.println(minWindow2( "ADOBECODEBANC","ABC"));
    }

    /**
     * 17ms 60%
     * 滑动窗口 + 判断s包含t的逻辑修改  之前是比较两个map，时间复杂度太高
     * 判断s是否包含了t的逻辑采用了一个valid参数表示，valid+1 表示这个字符在窗口里存在且数量也与t的相同
     * 因此当valid等于t的map的size的时候就是满足s包含t条件的时候
     * 那么valid什么时候+1呢？
     * 就是当新来一个字符以后，滑动窗口的这个字符的数量等于t的map里这个字符的数量的时候就valid+1，表示这个字符就绪了
     * 那valid什么时候-1呢？
     * 当滑动窗口要移出一个字符的时候，移出之前就要判断这个字符是不是t里面的，如果是还有判断数量是不是与t里面的字符数量相等，说明这个字符此时是就绪的状态
     * 那要移除这个字符就会打破就绪状态，就将valid-1，表示这个字符的就绪状态没有了
     * @param s
     * @param t
     * @return
     */
    public  static String minWindow2(String s, String t) {
        if(s.length()<t.length()){
            return "";
        }
        Map<Character,Integer> tMap = new  HashMap<>();
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<t.length();i++){
            char c = t.charAt(i);
            int num = tMap.getOrDefault(c,0);
            tMap.put(c,num+1);
        }

        int l=0;
        int r=0;
        String result = "";
        int valid = 0;
        while(r<=s.length()){
            if(r<s.length()){
                char c = s.charAt(r);
                if(tMap.containsKey(c)){
                    int  num = map.getOrDefault(c,0);
                    map.put(c,num+1);
                    if(num+1==tMap.getOrDefault(c,0)){
                        valid++;
                    }
                }
            }
            r++;
            while(valid==tMap.size()){
                if(result.equals("")||(result.length()>r-l)&&(r-l>=t.length())){
                    result = s.substring(l,r);
                }
                char lc = s.charAt(l++);
                if(map.containsKey(lc)){
                    int num = map.getOrDefault(lc,0);
                    map.put(lc,num-1);
                    if(num==tMap.getOrDefault(lc,0)){
                        valid--;
                    }
                }
            }
        }
        return result;
    }


    /**
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow1(String s, String t) {
        if(s.length()<t.length()){
            return "";
        }
        Map<Character,Integer> tMap = new  HashMap<>();
        for(int i=0;i<t.length();i++){
            char c = t.charAt(i);
            int num = tMap.getOrDefault(c,0);
            tMap.put(c,num+1);
        }

        int l=0;
        int r=0;
        String result = "";
        Map<Character,Integer> map = new HashMap<>();
        boolean isOk = false;
        while(r<=s.length()){

            while(r-l<t.length()&&r<s.length()){
                char c = s.charAt(r);
                int num = map.getOrDefault(c,0);
                map.put(c,num+1);
                r++;
                isOk = isMatch(map,tMap);
            }
            if(r>s.length()){
                break;
            }
            if(isOk){
                if(result.equals("")||(r-l<result.length()&&r-l>=t.length())){
                    result = s.substring(l,r);
                }
                char c = s.charAt(l);
                int num = map.getOrDefault(c,0);
                if(num>1){
                    map.put(c,num-1);
                }else{
                    map.remove(c);
                }
                if(num-1<tMap.getOrDefault(c,0)){
                    isOk = false;
                }
                l++;
            }else if(r<=s.length()){
                if(r<s.length()){
                    char c = s.charAt(r);
                    int num = map.getOrDefault(c,0);
                    map.put(c,num+1);
                }
                if(isMatch(map,tMap)){
                    isOk=true;
                }
                r++;
            }

        }

        return result;
    }

    public static boolean isMatch(Map<Character,Integer> map,Map<Character,Integer> tMap){
        for(Character c : tMap.keySet()){
            if(map.getOrDefault(c,0)<tMap.getOrDefault(c,0)){
                return false;
            }
        }
        return true;
    }

    /**
     * 172ms 6.93%
     * 思路是使用两个Map比较这俩字符串是否满足需求（现在满足需求是将两个map全量匹配），效率太低了，将满足需求的逻辑需要进行优化
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if(s.length()<t.length()){
            return "";
        }
        Map<Character,Integer> tMap = new  HashMap<>();
        for(int i=0;i<t.length();i++){
            char c = t.charAt(i);
            int num = tMap.getOrDefault(c,0);
            tMap.put(c,num+1);
        }

        int l=0;
        int r=0;
        String result = "";
        Map<Character,Integer> map = new HashMap<>();
        while(r<=s.length()){
            while(r-l<t.length()&&r<s.length()){
                char c = s.charAt(r);
                int num = map.getOrDefault(c,0);
                map.put(c,num+1);
                r++;
            }
            if(r>s.length()){
                break;
            }
            if(isOk(map,tMap)){
                if(result.equals("")||(r-l<result.length()&&r-l>=t.length())){
                    result = s.substring(l,r);
                }
                char c = s.charAt(l);
                int num = map.getOrDefault(c,0);
                if(num>1){
                    map.put(c,num-1);
                }else{
                    map.remove(c);
                }
                l++;
            }else if(r<s.length()){
                char c = s.charAt(r);
                int num = map.getOrDefault(c,0);
                map.put(c,num+1);
                r++;
            }else{
                r++;
            }
        }

        return result;
    }

    public static boolean isOk(Map<Character,Integer> map,Map<Character,Integer> tMap){
        for(Character c : tMap.keySet()){
            if(map.getOrDefault(c,0)<tMap.getOrDefault(c,0)){
                return false;
            }
        }
        return true;
    }
}
