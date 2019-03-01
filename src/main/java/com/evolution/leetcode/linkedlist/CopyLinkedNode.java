package com.evolution.leetcode.linkedlist;

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

    Map<Integer,Node> nodeMap = new HashMap<>();

    public Node copyRandomList(Node head) {

        if(head==null){
            return head;
        }

        Node res;
        Node mapNode = nodeMap.get(head.val);
        if(mapNode==null){
            res = new Node();
        }else {
            res = mapNode;
        }

        res.val = head.val;
        nodeMap.put(res.val,res);

        if(head.next!=null){
            Node next = copyRandomList(head.next);
            res.next = next;
        }

        if(head.random!=null){
            Node random = nodeMap.get(head.random.val);
            res.random = random;
        }

        return res;
    }

    public static void main(String[] args) {
        CopyLinkedNode copyLinkedNode = new CopyLinkedNode();

        Node node2 = new Node(2,null,null);
        node2.random = node2;
        Node node1 = new Node(1,node2,node2);
        Node res = copyLinkedNode.copyRandomList(node1);
        System.out.println();
    }
}

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}
