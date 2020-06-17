package com.sail.foroffer;

import com.sail.foroffer.pojo.Node_NO35;

import java.util.HashMap;
import java.util.Map;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 *
 *
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 *
 *
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO35_复杂链表的复制_注意 {

    public static void main(String[] args) {
        Node_NO35 head = new Node_NO35(7);
        Node_NO35 node1 = new Node_NO35(13);
        Node_NO35 node2 = new Node_NO35(11);
        Node_NO35 node3 = new Node_NO35(10);
        Node_NO35 node4 = new Node_NO35(1);
        head.next=node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next=null;
        head.random=null;
        node1.random=head;
        node2.random=node4;
        node3.random=node2;
        node4.random=head;
        Node_NO35 result = copyRandomList(head);

        while (result!=null){
            System.out.println("result.val:"+result.val+",result.next"+(result.next!=null?result.next.val:null)+",result.random"+(result.random!=null?result.random.val:null));
            result = result.next;
        }
    }

    /**
     * hash的做法并不是一个有效的做法，不推荐使用，推荐下面的原地修改方法
     * map里面存 原链表节点->复制链表节点 的映射关系，因为val有可能重复，不能只存val
     * 先跑一遍原链表，把map建起来，把复制的next链表连起来
     * 然后跑一新链表与原链表，从map里面找到原链表random节点对应的节点复制链表的节点
     * @param head
     * @return
     */
    public static Node_NO35 copyRandomList(Node_NO35 head) {
        if(head==null){return head;}
        Node_NO35 tmpHead = head;
        Map<Node_NO35, Node_NO35> map = new HashMap<>();

        Node_NO35 resultHead = new Node_NO35(head.val);
        map.put(tmpHead,resultHead);
        Node_NO35 resultTail = resultHead;
        resultTail.next=null;
        while(tmpHead.next!=null){
            tmpHead=tmpHead.next;
            Node_NO35 resultNode = new Node_NO35(tmpHead.val);
            map.put(tmpHead,resultNode);
            resultTail.next = resultNode;
            resultTail = resultTail.next;
            resultTail.next=null;
        }
        Node_NO35 tmpResultHead = resultHead;

        while(tmpResultHead!=null){
            tmpResultHead.random = head.random!=null?map.get(head.random):null;
            tmpResultHead = tmpResultHead.next;
            head = head.next;
        }
        return resultHead;
    }

    /**
     * 原链表复制
     * 第一步、给原链表每个节点复制一份添加到原节点后
     * 1->2->3->null   变成   1->1->2->2->3->3->null
     * 第二步、将复制的节点random添加上，复制节点的random等于原节点.random.next，因此此次遍历还要记录复制节点的原节点pre
     * 第三步、将这条链删除复制节点，把复制节点接到新的链上
     * @param head
     * @return
     */
    public static Node_NO35 copyRandomList1(Node_NO35 head) {
        if(head==null){
            return head;
        }
        Node_NO35 newHead =new Node_NO35(head.val);
        Node_NO35 tmpHead = head;
        while(tmpHead!=null){
            Node_NO35 node = new Node_NO35(tmpHead.val);
            node.next = tmpHead.next;
            tmpHead.next = node;

            tmpHead = tmpHead.next.next;
        }

        tmpHead = head;
        Node_NO35 pre = tmpHead;
        tmpHead = tmpHead.next;
        while(pre!=null&&tmpHead!=null){
            tmpHead.random = (pre.random!=null)?pre.random.next:null;
            pre = pre.next.next;
            if(tmpHead.next==null){
                break;
            }
            tmpHead = tmpHead.next.next;
        }

        Node_NO35 resultHead = null;
        Node_NO35 resultTail = null;

        while(head!=null&&head.next!=null){

            if(resultHead==null){
                resultHead = head.next;
                head.next = head.next.next;
                resultHead.next=null;
                resultTail = resultHead;
            }else{
                resultTail.next = head.next;
                head.next = head.next.next;
                resultTail = resultTail.next;
                resultTail.next = null;
            }
            head = head.next;
        }
        return resultHead;
    }
}
