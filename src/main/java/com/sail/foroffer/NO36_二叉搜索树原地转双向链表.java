package com.sail.foroffer;

import com.sail.foroffer.pojo.Node_NO36;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 *  
 *
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 *
 *  
 *
 *
 *
 *  
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 *  
 *
 *
 *
 *  
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO36_二叉搜索树原地转双向链表 {

    public Node_NO36 treeToDoublyList(Node_NO36 root) {
        if(root==null){
            return root;
        }

        /**
         * 先找到树里面最大和最小的节点的引用，作为链表的头尾
         */
        Node_NO36 tmp = root;
        while(tmp.left!=null){
            tmp = tmp.left;
        }
        Node_NO36 head  =tmp;
        tmp = root;
        while(tmp.right!=null){
            tmp = tmp.right;
        }
        Node_NO36 tail = tmp;

        /**
         * 关键
         */
        toDoublyList(root);

        /**
         * 头尾指针指一下
         */
        head.left = tail;
        tail.right = head;


        return head;

    }

    /**
     * 思路，要求自底向上的递归，先一直走到根节点，找到根节点的前驱和后继，然后改其左指针指向前驱，右指针指向后继
     * 并且还得判断其前驱的后继是不是为空的，为空的话还要将前驱的后继指向当前节点
     * 判断其后继的前驱是不是为空的，为空的话还要将后继的前驱指向当前节点
     * 之所以要这么做是因为没有父指针，子节点如果要找父节点作为后继节点是找不到的，
     * 因此在父节点找到子节点作为前驱的时候就将双休关系指定好，
     * 所以为了直接指定好双向的关系就只能自底向上递归，
     * 从上到下处理的话双向指的话父节点指向子节点没问题，
     * 但是子节点指向父节点的时候有可能把子节点自己的的左右孩子弄丢了
     * @param node
     */
    public void toDoublyList(Node_NO36 node){
        if(node==null){
            return ;
        }

        Node_NO36 leftChild = node.left;
        Node_NO36 rightChild = node.right;

        //先走到底
        toDoublyList(leftChild);
        toDoublyList(rightChild);


        Node_NO36 pre = leftChild;
        Node_NO36 post = rightChild;
        //一个节点的左孩子的最右孩子是直接前驱
        if(leftChild!=null){
            while(pre.right!=null){
                pre = pre.right;
            }
        }
        //一个节点的右孩子的最左孩子是直接后继
        if(rightChild!=null){
            while(post.left!=null){
                post = post.left;
            }
        }


        //如果找到这个节点的前驱节点不为空，但是前驱节点的后继是空的，就将前驱节点的后继初始化一下
        //从下到上处理不担心子节点丢了左右孩子
        node.left=pre;
        if(pre!=null&&pre.right==null){
            pre.right=node;
        }
        //如果找到这个节点的后继节点不为空，但是后继节点的前驱是空的，就将后继节点的前驱初始化一下
        node.right=post;
        if(post!=null&&post.left==null){
            post.left = node;
        }

    }
}
