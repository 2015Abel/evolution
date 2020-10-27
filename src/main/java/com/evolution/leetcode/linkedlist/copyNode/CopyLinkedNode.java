package com.evolution.leetcode.linkedlist.copyNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 链表深度拷贝
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 *
 * 示例：
 *
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *
 *
 * 提示：
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 *
 *  @program: evolution
 * @Author: Lzj
 * @Date: 2019/2/28 15:55
 */
public class CopyLinkedNode {

    public Node copyRandomList(Node head) {
        Node pre = null;
        Node next = null;

        // 存储源和新节点的map关系
        Map<Node,Node> sourceCopierMap = new HashMap<>();

        while (head!=null){
            Node node = new Node(head.val);
            if(pre!=null){
                pre.next = node;
            }else {
                next = node;
            }

            node.random = head.random;

            sourceCopierMap.put(head,node);

            node.next = head.next;
            head = head.next;
            pre = node;
        }

        Node res = next;

        // random指针替换
        while (next!=null){
            if(next.random!=null){
                next.random = sourceCopierMap.get(next.random);
            }

            next = next.next;
        }

        return res;
    }

    public static void main(String[] args) {
        CopyLinkedNode copyLinkedNode = new CopyLinkedNode();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node2.random = node1;

        Node res = copyLinkedNode.copyRandomList(node1);
        System.out.println();
    }
}

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
