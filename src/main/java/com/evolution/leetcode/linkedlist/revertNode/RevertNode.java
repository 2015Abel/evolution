package com.evolution.leetcode.linkedlist.revertNode;

import org.apache.lucene.util.bkd.HeapPointWriter;

/**
 * @program: evolution
 * @description: 反转链表
 *
 * 反转一个单链表。
 *
 *   示例:
 *     输入: 1->2->3->4->5->NULL
 *     输出: 5->4->3->2->1->NULL
 *
 * @author: zijian
 * @create: 2020-10-27 16:07
 **/
public class RevertNode {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (head !=null){
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = pre;
            pre = tmp;

            head = cur;
        }
        return pre;

    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode res = new RevertNode().reverseList(node0);
        while (res!=null){
            System.out.print(res.val+"->");
            res = res.next;
        }
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
}