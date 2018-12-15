package com.evolution.tree;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @description: SimpleNodeTest
 * @author: liuzijian
 * @date: 2018-11-27 15:08
 */
@RunWith(JUnit4.class)
public class SimpleNodeTest {

    TwoForkTree tree;

    @Before
    public void bef(){
        testAdd();
        tree.printTree();
        System.out.println("——————————————");
    }

    private void testAdd(){
        tree = new SimpleNode(32)
                .add(40).add(20)
                .add(41).add(35)
                .add(38);
    }

    @Test
    public void testPrintTree(){
        tree.printTree();
    }

    @Test
    public void testSearchRange(){
        Collection collection = tree.searchRange(25,36);
        System.out.println(JSON.toJSONString(collection));
    }

    @Test
    public void testFindMin(){
        SimpleNode simpleNode = (SimpleNode) tree;
        TwoForkTree minNode = simpleNode.findMin(tree);
        System.out.println(minNode.getId());
    }

    @Test
    public void testRemove(){
        int targetId = 40;
        tree = tree.remove(targetId);
        System.out.println(String.format("After remove node '%d':",targetId));
        tree.printTree();
    }

    @Test
    public void testTreeIterator(){
        tree.treeIterator(tree);
    }

    @Test
    public void testRecord(){
        tree.record(tree,0);
        SimpleNode simpleNode = (SimpleNode) tree;
        Map<Integer,List<Integer>> layerTree = simpleNode.layerTree;

        int layerIndex=0;
        while (layerIndex<layerTree.size()){
            layerIndex++;
            List<Integer> layerData = layerTree.get(layerIndex);
            for (Integer data:layerData){
                System.out.print(data+"\t");
            }
            System.out.println();
        }
    }

    @Test
    public void testRecordBean(){
        tree.printTree(tree,0);
        SimpleNode simpleNode = (SimpleNode) tree;
        Map<Integer,List<TwoForkTree.Printer>> layerTree = simpleNode.layerTreeMap;

        int layerIndex=0;
        while (layerIndex<layerTree.size()){
            layerIndex++;
            List<TwoForkTree.Printer> layerData = layerTree.get(layerIndex);
            StringBuilder nodeBu = new StringBuilder();
            StringBuilder linkBu = new StringBuilder();
            for (TwoForkTree.Printer data:layerData){
                nodeBu.append("\t  "+data.getId()+"`\t\t");

                boolean hasLeft = data.getLeftChildLink()!=null;
                boolean hasRight = data.getRightChildLink()!=null;
                linkBu.append("\t"+(hasLeft?data.getLeftChildLink():"")+ (hasLeft?"\t  ":"")
                        +(hasRight?data.getRightChildLink():"")+"\t\t");
            }
            System.out.println(nodeBu);
            System.out.println(linkBu);
            System.out.println();
        }
    }

}
