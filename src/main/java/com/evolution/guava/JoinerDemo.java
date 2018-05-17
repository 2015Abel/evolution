package com.evolution.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-04-19 16:37
 */
public class JoinerDemo {
    public static void main(String[] args) {
        List<String> name = ImmutableList.of("zhang","wang","li");
        String result = "'"+Joiner.on("','").join(name)+"'";
        System.out.println(result);
    }
}
