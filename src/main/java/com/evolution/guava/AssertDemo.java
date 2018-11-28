package com.evolution.guava;

import com.google.common.base.Preconditions;

/**
 * @description: 参数断言
 * @author: liuzijian
 * @date: 2018-04-12 15:40
 */
public class AssertDemo {

    public static void use(String param){
        Preconditions.checkNotNull(param,"checkNotNull参数为空！");
    }

    public static void main(String[] args) {
        use(null);
    }
}
