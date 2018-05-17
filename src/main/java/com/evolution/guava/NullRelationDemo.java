package com.evolution.guava;


import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * @description: NullRelationDemo
 * @author: liuzijian
 * @date: 2018-04-12 15:20
 */
public class NullRelationDemo {

    public static void use() {
        String param = null;
        String AfterOptional = Optional.fromNullable(param).or("222");

        System.out.println(AfterOptional);

        System.out.println(Strings.nullToEmpty(param));
    }

    public static void main(String[] args) {
        use();
    }
}
