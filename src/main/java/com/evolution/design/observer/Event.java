package com.evolution.design.observer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Method;

/**
 * @description: 事件，包含观察的整个来龙去脉
 * @author: liuzijian
 * @create: 2018-10-01 18:11
 **/
@Getter
@Setter
@ToString
public class Event {
    private Object source;
    private Object target;
    private Method callback;
    private String trigger;
    private long time;

    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }
}
