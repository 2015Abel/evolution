package com.evolution.guava;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * @author: liuzijian
 * @date: 2018-04-20 11:54
 */
public class SplitterDemo {
    public static void main(String[] args) {
        String param = "aaa=a&bbb=b";
        List<String> stringList = Splitter.on("&").splitToList(param);
        System.out.println(stringList);
    }
}
