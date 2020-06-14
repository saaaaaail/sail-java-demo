package com.sail.foroffer;

import java.util.LinkedList;

/**
 * @program: JavaDemo
 * @description: 用两个栈实现队列
 * @author: sail
 * @create: 2019/05/30 14:32
 */

public class NO9_用两个栈实现队列 {
    static class Queue{
        private LinkedList<Character> stack1;//入栈
        private LinkedList<Character> stack2;//出栈
        private int size;
        /**
         * 用一个size控制队列元素的多少，可以保证stack1和stack2的元素和为size
         */
        private int capacity;
        public Queue(int capacity){
            stack1 = new LinkedList<>();//入栈
            stack2 = new LinkedList<>();
            this.capacity=capacity;
        }

        private void add(Character x){
            if (size+1>capacity){
                System.out.println("容器已满，添加失败");
                return;
            }
            stack1.push(x);
            size++;
        }

        private Character remove(){
            /**
             * 若出栈不为空，就不能进行move操作
             */
            if (!stack2.isEmpty()){
                size--;
                return stack2.pop();
            }
            /**
             * 只在删除的时候进行move操作
             */
            move();
            if (stack2.isEmpty()){
                System.out.println("容器已空，删除失败");
                return '*';
            }
            size--;
            return stack2.pop();
        }

        private void move(){
            /**
             * 每次move将stack1的所有元素添加到stack2中
             */
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue(3);
        queue.add('a');
        queue.add('b');
        queue.add('c');queue.add('d');
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        queue.add('d');
        System.out.println(queue.remove());
    }


}
