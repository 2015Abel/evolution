package com.evolution.guava;

import com.google.common.base.Splitter;

import java.util.Map;

/**
 * @author: liuzijian
 * @date: 2018-04-20 11:54
 */
public class SplitterDemo {
    public static void main(String[] args) {
        String param = "aaa=a&&bbb=b";
        Map<String,String> stringMap = Splitter.on("&").omitEmptyStrings().withKeyValueSeparator("=").split(param);
        System.out.println(stringMap);
    }
}
