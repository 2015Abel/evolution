package com.evolution.design.observer;

import java.lang.reflect.Method;

/**
 * @description: MouseTest
 * @author: liuzijian
 * @create: 2018-10-02 06:57
 **/
public class MouseTest {

    public static void main(String[] args) throws NoSuchMethodException {
        MouseCallback target = new MouseCallback();
        Method callbackMethod = target.getClass().getMethod("onClick",Event.class);

        Mouse mouse = new Mouse();
        mouse.addListerer(MouseAction.CLICK,target,callbackMethod);
        mouse.click();

    }
}
