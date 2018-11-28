package com.evolution.design.observer;

/**
 * @description: 模拟鼠标行为
 * @author: liuzijian
 * @create: 2018-10-02 06:29
 **/
public class Mouse extends EventListener{
    public void click(){
        System.out.println("== mouse click ==");
        super.trigger(MouseAction.CLICK);
    }
}
