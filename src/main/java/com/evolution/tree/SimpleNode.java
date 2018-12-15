package com.evolution.tree;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @description: 简单二叉树
 * @author: liuzijian
 * @create: 2018-12-15 08:12
 **/
public class SimpleNode extends TwoForkTree {

    public SimpleNode(int id) {
        super.id = id;
    }

    @Override
    public TwoForkTree add(int id) {
        return addNode(id,this);
    }

    private TwoForkTree addNode(int id,TwoForkTree node){
        if(node==null){
            return new SimpleNode(id);
        }

        if(id<node.id){
            node.leftNode = addNode(id,node.leftNode);
        }

        if(id>node.id){
            node.rightNode = addNode(id,node.rightNode);
        }
        return node;
    }

    @Override
    public TwoForkTree remove(int id) {
        return remove(id,this);
    }

    private TwoForkTree remove(int id,TwoForkTree node){
        if(node==null){
            return null;
        }

        if(id<node.id){
            node.leftNode = remove(id,node.leftNode);
        }

        if(id==node.id){
            if(node.leftNode==null && node.rightNode!=null){
                node = node.rightNode;
            }else if(node.rightNode==null && node.leftNode!=null){
                node = node.leftNode;
            }else if(node.leftNode==null && node.rightNode==null){
                node = null;
                return node;
            }else {
                TwoForkTree min = findMin(node.rightNode);
                node.id = min.id;
                node.rightNode = remove(min.id,node.rightNode);
            }
        }


        if(id>node.id){
            node.rightNode = remove(id,node.rightNode);
        }

        return node;
    }

    /**
     * 寻找最小节点
     * @param node
     * @return
     */
    public TwoForkTree findMin(TwoForkTree node){
        if(node.leftNode==null){
            return node;
        }

        TwoForkTree min = findMin(node.leftNode);
        return min;
    }

    @Override
    public Collection searchRange(int min, int max) {
        Collection collection = new LinkedList();
        return searchRange(min,max,this,collection);
    }

    private Collection searchRange(int min,int max,TwoForkTree node,Collection collection){
        if(node == null){
            return collection;
        }

        if(node.id>min){
            searchRange(min,max,node.leftNode,collection);
        }

        if(min<=node.id && node.id<=max){
            collection.add(node.id);
        }

        if(node.id<max){
            searchRange(min,max,node.rightNode,collection);
        }
        return collection;
    }
}
