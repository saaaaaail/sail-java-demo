package com.sail.leetcode.interview2020.设计;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO208_前缀树 {


    /**
     * 40ms 95.36%
     * 对于前缀树，构建前缀树节点包括当前节点字符，当前节点是否是一个词，以及后面节点的指针数组
     * 思路就是每个节点都包含一个指针数组，已经存在的节点
     *
     */
    class Trie {

        class TrieNode{
            char c;
            boolean end;
            TrieNode[] next = new TrieNode[26];
        }

        private TrieNode head;

        /** Initialize your data structure here. */
        public Trie() {
            head = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode tmpHead = head;
            char[] words = word.toCharArray();
            int index = 0;
            while(index<words.length){
                char c = words[index++];
                /**
                 * 如果这个字符不存在
                 */
                if(tmpHead.next[c-'a']==null){
                    /**
                     * 新增这个字符节点，并插入这个节点
                     */
                    TrieNode node = new TrieNode();
                    node.c = c;
                    tmpHead.next[c-'a']=node;
                }
                //然后更新当前节点，继续插入下一个字符
                tmpHead = tmpHead.next[c-'a'];
            }
            //这个单词插入完以后，就给其终止节点设置标记
            tmpHead.end = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode tmpHead = head;
            char[] words = word.toCharArray();
            int index = 0;
            while(index<words.length){
                char c = words[index++];
                if(tmpHead.next[c-'a']==null){
                    //与插入不同，如果某一层没搜到单词里的字符，认为单词不存在
                    return false;
                }
                tmpHead = tmpHead.next[c-'a'];
            }
            //如果整个单词都存在，还要判断结束位置是不是单词结束标记，不是的话说明匹配到的只是某单词的前缀
            return tmpHead.end;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode tmpHead = head;
            char[] words = prefix.toCharArray();
            int index = 0;
            while(index<words.length){
                char c = words[index++];
                if(tmpHead.next[c-'a']==null){
                    return false;
                }
                tmpHead = tmpHead.next[c-'a'];
            }
            return true;
        }
    }
}
