package com.sail.foroffer;

import java.util.LinkedList;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *  
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO59_队列的最大值2 {


    /**
     * 单调队列写法，NO59_滑动窗口最大值1 里面也使用了
     */
    class MaxQueue {

        LinkedList<Integer> queue;
        LinkedList<Integer> maxQueue;
        public MaxQueue() {
            queue = new LinkedList<>();
            maxQueue = new LinkedList<>();
        }

        public int max_value() {
            if(!queue.isEmpty()){
                return maxQueue.peek();
            }
            return -1;
        }

        public void push_back(int value) {
            queue.addLast(value);
            while(!maxQueue.isEmpty()&&value>maxQueue.peekLast()){
                maxQueue.removeLast();
            }
            maxQueue.addLast(value);
        }

        public int pop_front() {
            if(!queue.isEmpty()){
                int tmp = queue.removeFirst();
                if(tmp == maxQueue.peekFirst()){
                    maxQueue.removeFirst();
                }
                return tmp;
            }
            return -1;
        }
    }
}
