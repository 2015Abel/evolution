package com.evolution.common;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-04-26 16:24
 */
public class IntegerDemo {
    public static void main(String[] args) {
        Integer i = 999;
        Integer g = i;
        System.out.println(g==i);
        Set<Integer> set = new HashSet<>();
        set.add(i);
        i= 888;
        System.out.println(i==set.iterator().next());
    }
}
