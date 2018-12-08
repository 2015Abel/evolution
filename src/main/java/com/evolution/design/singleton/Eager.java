package com.evolution.design.singleton;

/**
 * @description: 静态内部类式饿汉单例
 * @author: liuzijian
 * @date: 2018-12-06 20:35
 */
public class Eager {

    private Eager(){}

    public static Eager getInstance(){
        return Singleton.EAGER;
    }

    private static class Singleton{
        private final static Eager EAGER = new Eager();
    }
}
