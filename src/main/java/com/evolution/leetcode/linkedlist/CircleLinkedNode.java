package com.evolution.leetcode.linkedlist;

/**
 * @program: evolution
 * @description: 检测环形链表
 *
 *  提示：
 *      链表中节点的数目范围是 [0, 104]
 *      -10^5 <= Node.val <= 10^5
 *      pos 为 -1 或者链表中的一个 有效索引 。
 *
 * @author: zijian
 * @create: 2020-10-23 16:08
 **/
public class CircleLinkedNode {
    /**
     * 这种方法属于抖机灵，钻空子
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        int min = -10^5-1;

        boolean res = false;
        while (head!=null){
            if(head.val==min){
                res = true;
                break;
            }

            head.val = min;
            head = head.next;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(1);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node2;
        System.out.println(new CircleLinkedNode().hasCycle(node0));
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
     val = x;
     next = null;
    }
}

