package com.evolution.common;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @create: 2018-04-22 23:04
 **/
public class MapDemo {
    public static void main(String[] args) {
        Map<Integer,String> map = Maps.newTreeMap();
        map.put(5,"5a");
        map.put(1,"1b");
        map.put(9,"9c");
        map.put(99,"9c");
        map.put(87,"9c");
        map.put(32,"9c");
        map.put(11,"9c");

        for(Integer i:map.keySet()){
            System.out.println(i);
        }
    }
}
