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
        tree = new AVLNode(40,40,null,null)
                .add(41,41).add(45,45)
                .add(30,30).add(44,44)
                .add(46,46);
        tree = tree.add(60,60);
        System.out.println();
    }

    @Test
    public void testFindMin(){
        AVLNode minNode = tree.findMin(tree);
        System.out.println();
    }

    @Test
    public void testRemove(){
        tree = tree.remove(46,tree);
        System.out.println();
    }

}
