package com.evolution.tree;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @description: TwoForkTree 二叉树父类
 * @author: liuzijian
 * @create: 2018-12-15 07:47
 **/
@Getter
@Setter
public abstract class TwoForkTree {
    // id
    protected int id;
    // 值
    // protected Object val; 简单起见，去掉val

    // 左节点
    protected TwoForkTree leftNode;
    // 右节点
    protected TwoForkTree rightNode;

    public abstract TwoForkTree add(int id);

    public abstract TwoForkTree remove(int id);

    public abstract Collection searchRange(int min,int max);

    /**
     * 树打印
     */
    public void printTree(){
        Map<Integer,List<Printer>> printMap = printTree(this,0);
        int layerIndex = 1;
        StringBuilder idBu = new StringBuilder();
        StringBuilder linkBu = new StringBuilder();
        LinkedList<Integer> nextLineIdPositions = new LinkedList<>();
        while (layerIndex<=layerTreeMap.size()){
            List<Printer> printers = printMap.get(layerIndex);
            int lastIdLen = 0;
            int lastIdPosition = 0;
            for(Printer printer:printers){
                int position;
                if(CollectionUtils.isEmpty(nextLineIdPositions)){
                    position = 20;
                }else {
                    position = nextLineIdPositions.removeFirst()-idLen(printer.getId())/2;
                    if(position<=lastIdPosition+lastIdLen){
                        position+=idLen(printer.getId())/2;
                    }
                }
                lastIdPosition = position;
                lastIdLen = idLen(printer.getId());
                appendAt(idBu,position,printer.getId()+"`");

                if(!Strings.isNullOrEmpty(printer.getLeftChildLink())
                        || !Strings.isNullOrEmpty(printer.getRightChildLink())){
                    int linkPosition = idBu.length()-idLen(printer.getId());
                    if(!Strings.isNullOrEmpty(printer.getLeftChildLink())){
                        appendAt(linkBu,linkPosition-idLen(printer.getId())/2,printer.getLeftChildLink());
                        nextLineIdPositions.add(linkPosition-idLen(printer.getId())/2);
                    }

                    if(!Strings.isNullOrEmpty(printer.getRightChildLink())){
//                        if(Strings.isNullOrEmpty(printer.getLeftChildLink())){
//                            linkPosition+=2;
//                        }
                        appendAt(linkBu,linkPosition+idLen(printer.getId()),printer.getRightChildLink());
                        nextLineIdPositions.add(linkPosition+idLen(printer.getId())+1);
                    }
                }
            }
            System.out.println(idBu.toString());
            System.out.println(linkBu.toString());
            idBu.setLength(0);
            linkBu.setLength(0);
            layerIndex++;
        }

        // 数据还原
        layerTreeMap.clear();
    }

    private int idLen(Integer id){
        return (id+"").length();
    }

    private StringBuilder appendAt(StringBuilder bu,int position,String param){
        while (bu.length()<position){
            bu.append(" ");
        }
        return bu.append(param);
    }

    private String createSpace(int num){
        StringBuilder spaceBu = new StringBuilder();
        for(int k=0;k<num;k++){
            spaceBu.append(" ");
        }
        return spaceBu.toString();
    }

    //栈，用来记录路径
    Map<Integer,List<Printer>> layerTreeMap = new HashMap<>();

    public Map<Integer,List<Printer>> printTree(TwoForkTree node,int index){

        if(node==null){
            return null;
        }

        index++;
        List tempList = layerTreeMap.get(index);
        if(CollectionUtils.isEmpty(tempList)){
            tempList = new LinkedList();
            layerTreeMap.put(index,tempList);
        }
        Printer printer = new Printer();
        tempList.add(printer);
        printer.setId(node.getId());
        printer.setIndex(index);
        if(node.leftNode!=null){
            printer.setLeftChildLink("/");
        }
        if(node.rightNode!=null){
            printer.setRightChildLink("\\");
        }

        printTree(node.leftNode,index);


        printTree(node.rightNode,index);

        return layerTreeMap;
    }

    @Setter
    @Getter
    public class Printer{
        private Integer id;
        private int index;
        private String leftChildLink;
        private String rightChildLink;
    }


    public void treeIterator(TwoForkTree tree){
        if(tree==null){
            return ;
        }

        treeIterator(tree.leftNode);
        System.out.print(tree.getId()+"\t");   //打印节点
        treeIterator(tree.rightNode);
    }


    //按层级存储节点的值
    @Getter
    Map<Integer,List<Integer>> layerTree = new HashMap<>();

    public void record(TwoForkTree tree,int index){
        if(tree==null){
            return ;
        }
        index++;
        record(tree.leftNode,index);

        List<Integer> layerData = layerTree.get(index);
        if(CollectionUtils.isEmpty(layerData)){
            layerData = new LinkedList<>();
            layerTree.put(index,layerData);
        }
        layerData.add(tree.id);

        record(tree.rightNode,index);
    }
}
