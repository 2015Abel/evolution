package com.evolution.tree;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;

/**
 * @description: BinaryNodeTest
 * @author: liuzijian
 * @date: 2018-11-27 15:08
 */
@RunWith(JUnit4.class)
public class BinaryNodeTest {

    BinaryNode tree;

    @Before
    public void bef(){
        testAdd();
    }

    @Test
    public void testAdd(){
        tree = new BinaryNode(50,"50",null,null)
                .add(30,"30")
                .add(40,"40").add(20,"20")
                .add(41,"41").add(35,"35")
                .add(32,"32").add(38,"38");
        System.out.println();
    }

    @Test
    public void testSearchRange(){
        Collection collection = tree.searchRange(34,51);
        System.out.println(collection);
    }

    @Test
    public void testFindMin(){
        BinaryNode min = tree.findMin();
        System.out.println();
    }

    @Test
    public void testRemove(){
        tree = tree.remove(30);
        System.out.println();
    }

    @Test
    public void testSort(){
        System.out.println(tree.sort());
    }

    @Test
    public void testSort2(){
        System.out.println(tree.sort2());
    }

}
