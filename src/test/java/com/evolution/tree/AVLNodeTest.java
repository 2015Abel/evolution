package com.evolution.tree;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @description: BinaryNodeTest
 * @author: liuzijian
 * @date: 2018-11-27 15:08
 */
@RunWith(JUnit4.class)
public class AVLNodeTest {

    AVLNode tree;

    @Before
    public void bef(){
        testAdd();
    }

    @Test
    public void testAdd(){
        tree = new AVLNode(400,400,null,null)
                .add(410,410).add(450,450)
                .add(300,300).add(440,440)
                .add(460,460)
                .add(600,600)
                .add(455,455);
//        tree = new AVLNode(7,7,null,null)
//                .add(111,111).add(45,45)
//                .add(2,2).add(44,44)
//                .add(46,46)
//                .add(60,60);
        System.out.println();
    }

    @Test
    public void testFindMin(){
        AVLNode minNode = tree.findMin(tree);
        System.out.println();
    }

    @Test
    public void testRemove(){
        tree.printTree();
        System.out.println("##########################################");
        tree = tree.remove(440,tree);
        tree.printTree();
    }

    @Test
    public void testPrint(){
        tree.printTree();
        System.out.println();
    }

}
