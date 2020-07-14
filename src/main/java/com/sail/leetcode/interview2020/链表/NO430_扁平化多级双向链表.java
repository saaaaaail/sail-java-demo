package com.sail.leetcode.interview2020.链表;

import com.sail.leetcode.interview2020.pojo.Node;

/**
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
 * 解释：
 *
 * 输入的多级列表如下图所示：
 *
 *
 *
 * 扁平化后的链表如下图：
 *
 *
 * 示例 2：
 *
 * 输入：head = [1,2,null,3]
 * 输出：[1,3,2]
 * 解释：
 *
 * 输入的多级列表如下图所示：
 *
 *   1---2---NULL
 *   |
 *   3---NULL
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 如何表示测试用例中的多级链表？
 *
 * 以 示例 1 为例：
 *
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 * 序列化其中的每一级之后：
 *
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
 *
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * 合并所有序列化结果，并去除末尾的 null 。
 *
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 *  
 *
 * 提示：
 *
 * 节点数目不超过 1000
 * 1 <= Node.val <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO430_扁平化多级双向链表 {

    public Node flatten(Node head) {
        if(head==null){
            return head;
        }
        doFlatten(head);
        return head;
    }

    /**
     * 思路有很多种，比如将链表图顺时针旋转45度，当成二叉树做前序遍历即可
     * 或者像我这样一层一层递归 总的就是将child节点变成我的next节点，在变之前呢还要将child节点里的child先改掉
     * 递归的时候 可以直接返回尾节点，因为头节点引用是知道的，就是当前节点child节点，直接返回尾节点完成拼接
     * 不需要上一层再次遍历一遍
     * @param node
     * @return
     */
    public Node doFlatten(Node node){
        while(node.next!=null&&node.child==null){
            node = node.next;
        }
        //暂存next
        Node tmp = node.next;

        //如果有child，插入child到next
        if(node.child!=null){
            Node tailNode = doFlatten(node.child);
            //next指向child
            node.next = node.child;
            node.child.prev = node;
            node.child = null;
            //node修改为tailNode
            node = tailNode;
        }

        //插入完child以后指向暂存的tmp
        node.next = tmp;
        if(tmp!=null){
            tmp.prev = node;
        }
        //找到尾节点
        while(node.next!=null){
            node = node.next;
        }
        return node;
    }
}
