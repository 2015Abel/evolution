package com.evolution.tree;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @description: AVL-Tree，查找二叉树基础上增加旋转，得到平衡查找二叉树AVL
 * @author: liuzijian
 * @date: 2018-12-05 11:38
 */
@Setter
@Getter
public class AVLNode<V>{

    Integer id;

    Object val;

    int height;

    // 左节点
    private AVLNode<V> leftNode;
    // 右节点
    private AVLNode<V> rightNode;

    public AVLNode(Integer id, Object val, AVLNode leftNode, AVLNode rightNode) {
        this.id = id;
        this.val = val;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int height(AVLNode avlNode){
        if(avlNode==null){
            return -1;
        }else {
            return avlNode.height;
        }
    }

    //左左
    private AVLNode rotateLL(AVLNode avlNode){
        AVLNode top = avlNode.leftNode;
        AVLNode rightNode = top.rightNode;
        top.rightNode=avlNode;
        avlNode.leftNode = rightNode;

        top.height = Math.max(height(top.leftNode),height(top.rightNode))+1;
        avlNode.height = Math.max(height(avlNode.leftNode),height(avlNode.rightNode))+1;
        return top;
    }

    private AVLNode rotateRR(AVLNode avlNode){
        AVLNode top = avlNode.rightNode;
        AVLNode leftNode = top.leftNode;
        top.leftNode=avlNode;
        avlNode.rightNode = leftNode;

        top.height = Math.max(height(top.leftNode),height(top.rightNode))+1;
        avlNode.height = Math.max(height(avlNode.leftNode),height(avlNode.rightNode))+1;
        return top;
    }


    private AVLNode rotateLR(AVLNode avlNode){
        avlNode.leftNode = rotateRR(avlNode.leftNode);
        return rotateLL(avlNode);
    }

    private AVLNode rotateRL(AVLNode avlNode){
        avlNode.rightNode = rotateLL(avlNode.rightNode);
        return rotateRR(avlNode);
    }

    public AVLNode add(Integer id,Object val){
        return add(id,val,this);
    }

    private AVLNode add(Integer id,Object val,AVLNode avlNode){
        if(avlNode==null){
            return new AVLNode(id,val,null,null);
        }

        if(id<avlNode.id){
            avlNode.leftNode = add(id,val,avlNode.leftNode);
            if(height(avlNode.leftNode)-height(avlNode.rightNode)==2){
                if(id<avlNode.leftNode.id){
                    avlNode = rotateLL(avlNode);
                }else if(id>avlNode.leftNode.id){
                    avlNode = rotateLR(avlNode);
                }
            }
        }

        if(id>avlNode.id){
            avlNode.rightNode = add(id,val,avlNode.rightNode);
            if(height(avlNode.rightNode)-height(avlNode.leftNode)==2){
                if(id>avlNode.rightNode.id){
                    avlNode = rotateRR(avlNode);
                }else if(id<avlNode.rightNode.id){
                    avlNode = rotateRL(avlNode);
                }
            }
        }

        avlNode.height = Math.max(height(avlNode.leftNode),height(avlNode.rightNode))+1;
        return avlNode;
    }

    public AVLNode remove(Integer id,AVLNode avlNode){
        if(avlNode==null){
            return avlNode;
        }

        if(id<avlNode.id){
            avlNode.leftNode = remove(id,avlNode.leftNode);
            if(height(avlNode.rightNode)-height(avlNode.leftNode)==2){
                //TODO 删除左节点，树失衡
                AVLNode rightNode = avlNode.rightNode;
                if(rightNode.leftNode!=null && rightNode.rightNode==null){
                    avlNode = rotateRL(avlNode);
                }else if(rightNode.rightNode!=null ){
                    avlNode = rotateRR(avlNode);
                }
            }
        }

        if(id>avlNode.id){
            avlNode.rightNode = remove(id,avlNode.rightNode);
            if(height(avlNode.leftNode)-height(avlNode.rightNode)==2){
                //TODO 删除右节点，树失衡
                AVLNode leftNode = avlNode.leftNode;
                if(leftNode.rightNode!=null && leftNode.leftNode==null){
                    avlNode = rotateLR(avlNode);
                }else if(leftNode.leftNode!=null ){
                    avlNode = rotateLL(avlNode);
                }
            }
        }

        if(id==avlNode.id){
            if(avlNode.leftNode==null && avlNode.rightNode==null){
                return null;
            }else if(avlNode.leftNode==null && avlNode.rightNode!=null){
                avlNode = avlNode.rightNode;
            }else if(avlNode.leftNode!=null && avlNode.rightNode==null){
                avlNode = avlNode.leftNode;
            }else {
                AVLNode minNode = findMin(avlNode.rightNode);
                avlNode.id = minNode.id;
                avlNode.val = minNode.val;
                avlNode.rightNode = remove(minNode.id,avlNode.rightNode);
            }
        }

        avlNode.height = Math.max(height(avlNode.leftNode),height(avlNode.rightNode))+1;
        return avlNode;
    }

    public AVLNode findMin(AVLNode avlNode){
        if(avlNode.leftNode==null){
            return avlNode;
        }else {
            return findMin(avlNode.leftNode);
        }

    }

    public void printTree(){
        Map<Integer,LinkedList<Printer>> printMap = printTree(this,0);
        int i = 1;
        StringBuilder idBu = new StringBuilder();
        StringBuilder linkBu = new StringBuilder();
        LinkedList<Integer> nextLineIdPositions = new LinkedList<>();
        while (i<=maxIndex){
            LinkedList<Printer> printers = printMap.get(i);
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
                appendAt(idBu,position,printer.getId()+"");

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
            i++;
//            String singleRes = idBu.toString();
//            System.out.println(singleRes);
        }

        map = new TreeMap<>();
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
    Map<Integer,LinkedList<Printer>> map = new TreeMap<>();
    int maxIndex = 0;

    private Map<Integer,LinkedList<Printer>> printTree(AVLNode avlNode,int index){

        if(avlNode==null){
            return null;
        }

        index++;
        maxIndex = Math.max(maxIndex,index);
        LinkedList tempList = map.get(index);
        if(CollectionUtils.isEmpty(tempList)){
            tempList = new LinkedList();
            map.put(index,tempList);
        }
        Printer printer = new Printer();
        tempList.add(printer);
        printer.setId(avlNode.getId());
        printer.setIndex(index);
        if(avlNode.leftNode!=null){
            printer.setLeftChildLink("/");
        }
        if(avlNode.rightNode!=null){
            printer.setRightChildLink("\\");
        }

        printTree(avlNode.leftNode,index);


        printTree(avlNode.rightNode,index);

        return map;
    }

    @Setter
    @Getter
    private class Printer{
        private Integer id;
        private int index;
        private String leftChildLink;
        private String rightChildLink;
    }

}
