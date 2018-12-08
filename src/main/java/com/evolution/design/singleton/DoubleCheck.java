package com.evolution.design.singleton;

/**
 * @description: 双重检查方式单例
 * @author: liuzijian
 * @date: 2018-12-07 09:40
 */
public class DoubleCheck {
    private volatile static DoubleCheck bean = null;

    private DoubleCheck(){}

    public static DoubleCheck getInstance(){
        if(bean==null){
            synchronized (DoubleCheck.class){
                if(bean==null){
                    bean = new DoubleCheck();
                }
            }
        }
        return bean;
    }
}
