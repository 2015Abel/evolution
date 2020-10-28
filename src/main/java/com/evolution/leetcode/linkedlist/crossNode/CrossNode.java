package com.evolution.leetcode.linkedlist.crossNode;

/**
 * @program: evolution
 * @description: 相交链表
 *
 *  编写一个程序，找到两个单链表相交的起始节点。
 *  如果链表不相交，则返回null
 * @author: zijian
 * @create: 2020-10-27 15:32
 **/
public class CrossNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointA = headA;
        ListNode pointB = headB;

        ListNode tailA = null;
        ListNode tailB = null;
        while (pointA!=null && pointB!=null){
            // 小心第一个节点就相交
            if(pointA.equals(pointB)){
                return pointA;
            }

            if(pointA.next==null){
                tailA = pointA;
                pointA = headB;
            }else {
                pointA = pointA.next;
            }

            if(pointB.next==null){
                tailB = pointB;
                pointB = headA;
            }else {
                pointB = pointB.next;
            }

            if(tailA!=null && tailB!=null && !tailA.equals(tailB)){
                return null;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        ListNode nodeA = new ListNode(4);
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeAB0 = new ListNode(8);
        ListNode nodeAB1 = new ListNode(4);
        ListNode nodeAB2 = new ListNode(5);
        ListNode nodeB = new ListNode(5);
        ListNode nodeB1 = new ListNode(0);
        ListNode nodeB2 = new ListNode(1);
        nodeA.next = nodeA1;
        nodeA1.next = nodeAB0;
        nodeAB0.next = nodeAB1;
        nodeAB1.next = nodeAB2;
        nodeB.next = nodeB1;
        nodeB1.next = nodeB2;
        nodeB2.next = nodeAB0;

        ListNode res = new CrossNode().getIntersectionNode(nodeA,nodeB);
        if(res!=null){
            System.out.println(res.val);
        }else {
            System.out.println("不相交");
        }
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
