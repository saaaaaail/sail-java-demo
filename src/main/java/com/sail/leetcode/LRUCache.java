package com.sail.leetcode;

import java.util.HashMap;

/**
 * @program: JavaDemo
 * @description: LRU缓存 最近最少使用
 *
 *  LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例:
 * 使用
 *
 *
 * @author: sail
 * @create: 2019/05/05 11:57
 */

public class LRUCache {
    class LinkedNode{
        int key;
        int val;
        LinkedNode pre;
        LinkedNode post;
    }
    private HashMap<Integer,LinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private LinkedNode head;
    private LinkedNode tail;
    public LRUCache(int capacity){
        count=0;
        this.capacity=capacity;

        head = new LinkedNode();
        head.pre=null;

        tail = new LinkedNode();
        tail.post=null;

        head.post=tail;
        tail.pre=head;

    }

    public void moveToHead(LinkedNode node){
        removeNode(node);
        addToHead(node);
    }

    public void removeNode(LinkedNode node){
        /**
         * 删除节点node
         */
        node.pre.post=node.post;
        node.post.pre=node.pre;
    }

    public void addToHead(LinkedNode node){
        /**
         * 插入到开头
         */
        node.post=head.post;
        node.post.pre=node;
        node.pre=head;
        head.post=node;
    }

    public LinkedNode popTail(){
        LinkedNode node = tail.pre;
        removeNode(node);
        return node;
    }

    public int get(int key){
        LinkedNode node = cache.get(key);
        if (node==null){
            return  -1;
        }
        moveToHead(node);
        return node.val;
    }
    public void put(int key,int val){
        LinkedNode node = cache.get(key);
        if (node!=null){
            node.val=val;
            cache.put(key,node);
            /**
             * 移动到开头
             */
            moveToHead(node);
        }else{
            node = new LinkedNode();
            node.key=key;
            node.val=val;
            cache.put(key,node);
            /**
             * 加入开头
             */
            addToHead(node);
            count++;
            /**
             *  容量已满
             */
            if (count>capacity){
                LinkedNode res = popTail();
                cache.remove(res.key);
                count--;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4,4);
        System.out.println(cache.get(1));
    }


}
