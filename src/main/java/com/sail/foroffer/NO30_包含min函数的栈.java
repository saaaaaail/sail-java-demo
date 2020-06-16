package com.sail.foroffer;

import com.sail.foroffer.pojo.ListNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 *  
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *  
 *
 * 提示：
 *
 * 各函数的调用总次数不超过 20000 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO30_包含min函数的栈 {

    /**
     * 最小栈
     * @param args
     */
    public static void main(String[] args) {
        MinStack st = new MinStack();
        st.push(-2);
        st.push(0);
        st.push(-3);
        System.out.println(st.min());
        st.pop();
        System.out.println(st.top());
        System.out.println(st.min());

    }

    /**
     * 思路就是用两个栈
     * 一个存所有元素
     * 一个存比当前最小值要小的元素
     */
    static class MinStack {

        private LinkedList<Integer> stack =null;
        private LinkedList<Integer> minStack = null;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new LinkedList();
            minStack = new LinkedList();
        }

        public void push(int x) {
            stack.push(x);
            if(minStack.isEmpty() || x <= minStack.peek()){
                minStack.push(x);
            }


        }

        public void pop() {
            int i = stack.pop();
            if(!minStack.isEmpty()&&i==minStack.peek()){
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }

}
