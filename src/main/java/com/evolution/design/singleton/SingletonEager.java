package com.evolution.design.singleton;

/**
 * @ClassName SingletonEager
 * @Description: 单例
 * @Author: Lzj
 * @Date: 2019/6/19 13:57
 */
public class SingletonEager {

    private static final SingletonEager singletonInstance = new SingletonEager();
    private SingletonEager(){}

    public static SingletonEager getInstance(){
        return singletonInstance;
    }
}


class Singleton {

    private static Singleton singletonInstance; // A - 温婉到只有变量声明
    private Singleton (){}  // B - 私有化的构造器，避免随意ndew

    public static synchronized Singleton getInstance(){  // C - 暴露给外部的获取方法，成员变量的赋值后延至此
        synchronized (Singleton.class){
            if(singletonInstance==null){
                singletonInstance = new Singleton();
            }
        }

        return singletonInstance;
    }
}
