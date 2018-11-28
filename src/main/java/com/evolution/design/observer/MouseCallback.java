package com.evolution.design.observer;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @create: 2018-10-02 06:56
 **/
public class MouseCallback {
    public void onClick(Event e){
        System.out.println("## 鼠标点击，出发相应事件！ ##");
        System.out.println("Event参数："+e);
    }
}
