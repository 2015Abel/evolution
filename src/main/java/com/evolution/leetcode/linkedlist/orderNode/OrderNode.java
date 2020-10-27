package com.evolution.leetcode.linkedlist.orderNode;

/**
 * @program: evolution
 * @description: 链表排序
 *
 *  在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * @author: zijian
 * @create: 2020-10-23 16:35
 **/
public class OrderNode {
    /**
     * 归并法
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }

        // 快慢指针找中间点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!=null){
            if(fast==null || fast.next==null){
                break;
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode right = slow.next;
        slow.next = null;

        right = sortList(right);
        ListNode left = sortList(head);

        ListNode res = new ListNode();
        ListNode result = res;
        while (left!=null && right !=null){
            if(left.val<right.val){
                res.next = left;
                left = left.next;
            }else {
                res.next = right;
                right = right.next;
            }
            res = res.next;
        }

        res.next = left==null?right:left;

        return result.next;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(4);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(0);
        ListNode node6 = new ListNode(-1);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode res = new OrderNode().sortList(node0);
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
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}