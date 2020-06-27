package com.sail.leetcode.interview2020.经典习题;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *  
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 */
public class NO146_LRU缓存机制 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

    }

    /**
     * 还一种 自己定义链表节点的
     */


    /**
     * 使用LinkedList 效率不高啊
     */
    static class LRUCache {

        private Map<Integer,Integer> map = null;
        private LinkedList<Integer> list = null;
        private int capacity;
        private int size;
        public LRUCache(int capacity) {
            map = new HashMap<>();
            list = new LinkedList<>();
            this.capacity = capacity;
            this.size = 0;
        }

        public int get(int key) {
            Integer res = map.get(key);
            if(res!=null){
                list.remove(new Integer(key));
                list.addFirst(key);
                return res;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                map.put(key,value);
                list.remove(new Integer(key));
                list.addFirst(key);
            }else{
                if(size+1>capacity){
                    Integer last = list.removeLast();
                    map.remove(last);
                }else{
                    size++;
                }
                map.put(key,value);
                list.addFirst(key);
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
