package com.sail.leetcode.interview2020.分治算法;

import com.sail.leetcode.interview2020.pojo.ListNode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO23_合并K个排序链表 {


    /**
     * 用分治，类似于merge的方式
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0){
            return null;
        }
        int l=0;
        int r = lists.length-1;
        return mergeSort(lists,l,r);

    }

    /**
     * 将listNode数组不停地二分，
     * 直到区间范围里面剩一个，就直接返回
     * 直到区间范围里面刚好剩两个就 链表的merge
     * 其他的区间 都 二分，
     * 二分获得的返回值，最后还要merge成一条链
     * @param lists
     * @param l
     * @param r
     * @return
     */
    public ListNode mergeSort(ListNode[] lists,int l, int r){
        if(l==r){
            return lists[l];
        }
        if(r-l==1){
            return merge(lists[l],lists[r]);
        }
        int mid = (l+r)/2;
        ListNode left = mergeSort(lists,l,mid);
        ListNode right = mergeSort(lists,mid+1,r);

        return merge(left,right);

    }

    public ListNode merge(ListNode l1,ListNode l2){
        if(l1==null&&l2==null){
            return null;
        }
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode head = null;
        ListNode tail = null;
        while(l1!=null&&l2!=null){
            ListNode l = null;
            if(l1.val>l2.val){
                l = l2;
                l2 = l2.next;
                l.next = null;
            }else{
                l = l1;
                l1 = l1.next;
                l.next =null;
            }

            if(head == null){
                head = l;
                tail = l;
            }else{
                tail.next = l;
                tail = tail.next;
            }
        }
        if(l1!=null){
            tail.next = l1;
        }
        if(l2!=null){
            tail.next = l2;
        }
        return head;
    }

    /**
     * 这一种是第一时间想到的，顺序求解最小值， 336ms 5%
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode head = null;
        ListNode tail = null;

        int minIndex = 0;
        ListNode minNode = null;
        while(true){

            for(int i=0;i<lists.length;i++){
                ListNode node = lists[i];
                if(node==null){
                    continue;
                }

                if(minNode==null||node.val<minNode.val){
                    minNode = node;
                    minIndex = i;
                }
            }
            if(minNode==null){
                break;
            }
            lists[minIndex] = minNode.next;

            if(head == null){
                head = minNode;
                tail =head;
                minNode.next =null;
            }else{
                tail.next = minNode;
                tail = tail.next;
                minNode.next =null;
            }
            minNode = null;

        }
        return head;

    }
}
