package com.evolution.design.singleton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-12-06 20:49
 */
@RunWith(JUnit4.class)
public class EagerTest {
    @Test
    public void testGetInstance(){
        Eager eager = Eager.getInstance();
    }
}
