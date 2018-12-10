package com.evolution.tree;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @description: 简单二叉树，不考虑平衡旋转；可对照数据库表进行理解
 *
 *      树在范围查找方面有所优势
 *
 * @author: liuzijian
 * @date: 2018-11-27 11:45
 */
@Getter
@Setter
public class BinaryNode<V> {

    private final String tabName = "t_usr";

    // id ( 数据库表`id`)
    private Integer id;

    // val （数据库表`数据`）
    private V val;

    // 左节点
    private BinaryNode<V> leftNode;
    // 右节点
    private BinaryNode<V> rightNode;

    public BinaryNode(Integer id, V val, BinaryNode<V> leftNode, BinaryNode<V> rightNode) {
        this.id = id;
        this.val = val;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * 新增
     * @param id
     * @param val
     * @return
     */
    public BinaryNode<V> add(Integer id,V val){
        return add(id,val,this,true);
    }

    private BinaryNode<V> add(Integer id,V val,BinaryNode<V> tree,boolean idCheck){
        if(tree == null){
            tree = new BinaryNode<>(id,val,null,null);
        }

        if(idCheck){
            boolean idExists = MajorKeyValidator.add(tabName,id);
            if(idExists){
                throw new RuntimeException(String.format("id=%d 已存在！",id));
            }
        }

        if(id>tree.id){
            tree.rightNode = add(id,val,tree.rightNode,false);
        }

        if(id<tree.id){
            tree.leftNode = add(id,val,tree.leftNode,false);
        }

        return tree;
    }

    /**
     * 范围查找
     * @param min
     * @param max
     * @return
     */
    public Collection<V> searchRange(Integer min,Integer max){
        Collection<V> collection = new LinkedList<>();
        return searchRange(min,max,collection,this);
    }

    private Collection<V> searchRange(Integer min, Integer max, Collection<V> collection,BinaryNode<V> tree){//当前节点与min比较，如果大于min，递归查看当前节点的左节点
        //当前节点与min比较，如果大于min，递归查看当前节点的左节点（如果有左节点的话）
        if(min<tree.getId() && tree.leftNode!=null){
            searchRange(min,max,collection,tree.leftNode);
        }

        //当前节点在范围内，则放入结果集
        if(min<=tree.getId() && max>=tree.getId()){
            collection.add(tree.getVal());
        }

        //当前节点与max比较，如果小于max，递归查看当前节点的右节点（如果有右节点的话）
        if(tree.getId()<max && tree.rightNode!=null){
            searchRange(min,max,collection,tree.rightNode);
        }

        return collection;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public BinaryNode<V> remove(Integer id){
        return remove(id,this);
    }

    private BinaryNode<V> remove(Integer id,BinaryNode<V> tree){
        if(tree==null){
            return null;
        }

        if(id>tree.getId()){
            tree.rightNode = remove(id,tree.rightNode);
        }

        if(id<tree.getId()){
            tree.leftNode = remove(id,tree.leftNode);
        }

        if(id.equals(tree.id)){
            MajorKeyValidator.del(tabName,id);

            //单子
            if(tree.getLeftNode()==null && tree.getRightNode()!=null){
                tree = tree.rightNode;
            }else if(tree.getLeftNode()!=null && tree.getRightNode()==null){
                tree = tree.leftNode;
            }
            //双子
            else if(tree.getLeftNode()!=null && tree.getRightNode()!=null){
                //方便起见：将值改变，引用不动
                BinaryNode<V> min = findMin(tree.rightNode);
                tree.id = min.id;
                tree.val = min.val;

                tree.rightNode = remove(tree.id,tree.rightNode);
            }
            //无子
            else {
                tree = null;
            }
        }
        return tree;
    }

    public BinaryNode<V> findMin() {
        return findMin(this);
    }

    private BinaryNode<V> findMin(BinaryNode<V> tree) {
        if(tree.leftNode==null){
            return tree;
        }
        BinaryNode min = findMin(tree.leftNode);
        return min;
    }


    /**
     * 中序遍历达到排序效果
     * @return
     */
    public Collection<V> sort(){
        Collection<V> resList = new LinkedList<>();
        return sort(this,resList);
    }

    //栈，用来记录路径
    LinkedList<BinaryNode<V>> nodeStack = new LinkedList<>();

    private Collection<V> sort(BinaryNode<V> tree,Collection<V> collection){
        if(tree==null){
            return collection;
        }

        nodeStack.add(tree);
        sort(tree.leftNode,collection);
        while (!CollectionUtils.isEmpty(nodeStack)){
            BinaryNode<V> temp = nodeStack.removeLast();
            collection.add(temp.getVal());
            if(temp.rightNode!=null){
                sort(temp.rightNode,collection);
            }
        }

        return collection;
    }

    /**
     * 中序遍历2，这种方式更简单
     * @return
     */
    public Collection<V> sort2(){
        return sort(this);
    }

    LinkedList<V> valList = new LinkedList<>();

    private Collection<V> sort(BinaryNode<V> tree){
        if(tree==null){
            return valList;
        }

        sort(tree.leftNode);
        valList.add(tree.getVal());
        sort(tree.rightNode);
        return valList;
    }
}
