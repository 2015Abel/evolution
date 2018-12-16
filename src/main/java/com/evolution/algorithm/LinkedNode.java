package com.evolution.algorithm;

/**
 * @description: LinkedNode
 * @author: liuzijian
 * @create: 2018-12-16 19:40
 **/
public class LinkedNode {
    Integer id ;
    LinkedNode next;

    public LinkedNode(Integer id) {
        this.id = id;
    }

    /**
     * 链表翻转，循环 + 双指针（pre、next）实现
     * @param cur
     * @return
     */
    public LinkedNode reverse(LinkedNode cur){
        LinkedNode pre = null;

        while (cur!=null){
            LinkedNode next = cur.next; // 1.
            cur.next = pre; // 2.
            pre = cur;  // 3.
            cur = next; // 4.
        }

        return pre;
    }

    /**
     * 链表反转，递归实现
     * @param node
     * @return
     */
    public LinkedNode reverse2(LinkedNode node){
        if(node.next==null){
            return node;
        }

        LinkedNode newHead = reverse2(node.next);
        node.next.next = node;  //node.next.next 换成 newHead.next 不行，因为node在递归中在追溯上一个节点，仔细体会下
        node.next = null;
        return newHead;
    }

    public static LinkedNode init(int limit){
        int begin = 1;
        LinkedNode node = new LinkedNode(begin);
        begin++;
        LinkedNode next = new LinkedNode(begin);
        node.next = next;
        while (begin<limit){
            begin++;
            next.next = new LinkedNode(begin);
            next = next.next;
        }
        return node;
    }

    public void show(){
        System.out.print(id+"->");
        LinkedNode tempNext = next;
        while (tempNext!=null){
            System.out.print(tempNext.id+"->");
            tempNext = tempNext.next;
        }
    }

    public static void main(String[] args) {
        LinkedNode node = LinkedNode.init(4);
        node.show();
        System.out.println("\nExecute reverse..");
        LinkedNode reverseNode = node.reverse(node);
        reverseNode.show();

        System.out.println("\nExecute reverse2..");
        LinkedNode reverseNode2 = reverseNode.reverse2(reverseNode);
        reverseNode2.show();
    }
}
