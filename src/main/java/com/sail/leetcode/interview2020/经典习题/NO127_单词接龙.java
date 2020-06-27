package com.sail.leetcode.interview2020.经典习题;

import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO127_单词接龙 {

    public static void main(String[] args) {

//        String begin = "ymain";
//        String end = "oecij";
//        String[] s = new String[]{"ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"};
        String begin = "hit";
        String end = "cog";

        String[] s = new String[]{"hot","dot","dog","lot","log","cog"};

        List<String> wordList = new ArrayList<>(Arrays.asList(s));
        System.out.println(ladderLength2(begin,end,wordList));
        LinkedList<Integer> list = new LinkedList<>();
        Integer e = new Integer(3);
        list.remove(e);
    }

    /**
     * 双向BFS  15ms  96.42%
     * 即从beginWord 和 endWord同时开始
     * 需要一个set作为是否访问过校验 visit
     * 单向bfs的队列 替换为 beginSet集合与endSet集合
     * 集合中保存的就是当前层次的word，那么层次之间的word在beginSet与endSet之间如何替换呢
     * 在寻找当前层次word相连的word（这些word就是下一层的word）的时候新建一个set保存这些word，
     * 最后把beginSet或者endSet的引用指向上面的set，跳转到下一层的word集合中去
     *
     */
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int result = doLadderLength2(beginWord,endWord,wordList);
        return result;
    }

    public static int doLadderLength2(String beginWord,String endWord,List<String> wordList){
        Set<String> wordSet = new HashSet<>();
        for(String s:wordList){
            wordSet.add(s);
        }
        //反向BFS的时候，注意本题endWord必须存在与wordList中才能找到，加个限制条件
        if(!wordSet.contains(endWord)){
            return 0;
        }

        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);

        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);

        Set<String> visit = new HashSet<>();
        int path=0;
        while(!beginSet.isEmpty()&&!endSet.isEmpty()){
            path++;
            if(beginSet.size()>endSet.size()){
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }
            //用于保存于beginSet相连的word，属于层次遍历下一层的东西
            Set<String> nextLevelSet = new HashSet<>();
            for(String word:beginSet){
                char[] c= word.toCharArray();
                for(int i=0;i<c.length;i++){
                    char tmp = c[i];
                    for(char j='a';j<='z';j=(char)(j+1)){
                        c[i] = j;
                        String nextWord = String.valueOf(c);
                        if(wordSet.contains(nextWord)){
                            if(endSet.contains(nextWord)){
                                return path+1;
                            }
                            if(!visit.contains(nextWord)){
                                visit.add(nextWord);
                                nextLevelSet.add(nextWord);
                            }
                        }
                    }
                    c[i] = tmp;
                }
            }
            //本层word遍历完成以后，下一层的word都加入了nextLevelSet里面，将set集合引用指向下一层的set
            beginSet = nextLevelSet;
        }
        return 0;
    }
    /**
     * 单向BFS 广度优先找最近的节点 87ms 59.75%
     * 广度优先遍历就需要使用队列 与 是否访问的校验（也就是只进过依次队列）
     * 把list放到set里面来判断nextWord是不是list里面的
     * 把进入队列的字符串都进visit set，表示已访问
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        int result = doLadderLength(beginWord,endWord,wordList);
        return result;
    }

    public static int doLadderLength(String chooseWord,String endWord,List<String> wordList){
        Set<String> wordSet = new HashSet<>();
        for(String s:wordList){
            wordSet.add(s);
        }
        LinkedList<String> queue = new LinkedList<>();
        Set<String> visit = new HashSet<>();
        queue.offer(chooseWord);
        visit.add(chooseWord);
        int path=0;
        while(!queue.isEmpty()){
            path++;
            int size = queue.size();
            while(size>0){
                String word= queue.poll();

                //找这个word相连的word
                char[] c = word.toCharArray();
                for(int i=0;i<c.length;i++){
                    char tmp = c[i];
                    for(char j='a';j<='z';j = (char)(j+1)){
                        c[i] = j;

                        String nextWord = String.valueOf(c);
                        if(wordSet.contains(nextWord)){
                            if(!visit.contains(nextWord)){
                                if(nextWord.equals(endWord)){
                                    return path+1;
                                }
                                visit.add(nextWord);
                                queue.offer(nextWord);
                            }
                        }
                    }
                    c[i]=tmp;
                }
                size--;
            }
        }
        return 0;

    }


    /**
     * 深度优先遍历，妥妥的超时，耗时点在于 判断两个单词是否仅相差一位
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean[] visit =new boolean[wordList.size()];
        int result = doLadderLength(0,beginWord,endWord,visit,wordList);
        if(result==Integer.MAX_VALUE){
            return 0;
        }
        return result;
    }

    public int doLadderLength(int path,String chooseWord,String endWord,boolean[] visit,List<String> wordList){
        path++;
        if(chooseWord.equals(endWord)){
            return path;
        }
        if(path>=wordList.size()){
            return Integer.MAX_VALUE;
        }

        int minPath = Integer.MAX_VALUE;
        for(int i=0;i<wordList.size();i++){
            String nextWord = wordList.get(i);
            if(!visit[i]&&isNextWord(chooseWord,nextWord)){
                visit[i]=true;
                minPath = Math.min(minPath, doLadderLength(path,nextWord,endWord,visit,wordList));
                visit[i]=false;
            }
        }
        return minPath;
    }

    public boolean isNextWord(String chooseWord,String word){
        int t = 0;
        for(int i=0;i<word.length();i++){
            if(chooseWord.charAt(i)!=word.charAt(i)){
                t++;
            }
        }
        if(t==1){
            return true;
        }else{
            return false;
        }
    }
}
