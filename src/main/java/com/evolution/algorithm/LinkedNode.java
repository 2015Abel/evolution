package com.evolution.algorithm;

/**
 * @description: 链表反转
 * @author: liuzijian
 * @create: 2018-12-15 23:38
 **/
public class LinkedNode {
    private Integer id;
    private LinkedNode next;

    public LinkedNode(Integer id) {
        this.id = id;
    }

    public LinkedNode reverse(LinkedNode node){
        if(node.next==null){
            return node;
        }

        LinkedNode temp = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return temp;
    }

    public LinkedNode reverse2(LinkedNode node){
        LinkedNode pre = null;
        LinkedNode cur = node;
        LinkedNode head = null;

        while (cur!=null){
            LinkedNode next = cur.next;
            if(next==null){
                head = cur;
            }

            cur.next = pre;
            pre = cur;

            cur = next;
        }

        return head;
    }

    public void print(){
        print(this);
    }

    private void print(LinkedNode node){
        if(node==null){
            return;
        }

        System.out.print(node.id+"->");
        print(node.next);
    }

    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        node1.next = node2;
        node2.next = node3;

        node1.print();

        System.out.println();
        LinkedNode reverseNode = node1.reverse2(node1);
        reverseNode.print();
    }
}
