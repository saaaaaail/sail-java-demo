package com.sail.leetcode.interview2020.堆与栈;

import java.util.*;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 示例 1：
 *
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 *  
 *
 * 示例 2：
 *
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO692_前k个高频单词 {
    public static void main(String[] args) {
        List<String> strings = topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4);
        System.out.println(strings);
    }

    static class Node{
        String key;
        int freq;
        public Node(){}
        public Node(String key,int freq){
            this.key = key;
            this.freq = freq;
        }
    }

    /**
     * 思路，统计词频，入堆，指定排序规则，输出前k个
     * @param words
     * @param k
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        Arrays.sort(words);
        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)->{
            if(o1.freq!=o2.freq){
                return o2.freq-o1.freq;
            }
            return o1.key.compareTo(o2.key);
        });

        for(int i=0;i<words.length;i++){
            String s = words[i];
            Node node = new Node(s,1);
            while(i+1<words.length&&words[i+1].equals(s)){
                i++;
                node.freq++;
            }
            queue.offer(node);
        }

        List<String> result = new ArrayList<>();
        while(k>=0){
            result.add(queue.poll().key);
            k--;
        }
        return result;
    }
}
