package com.evolution.design.observer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: EventListener
 * @author: liuzijian
 * @create: 2018-10-01 18:12
 **/
public class EventListener {

    private Map<Enum,Event> map = new HashMap<>();

    public void addListerer(Enum action,Object target, Method callback) {
        map.put(action,new Event(target,callback));
    }

    public void trigger(Enum action){
        if(map.containsKey(action)){
            try {
                Event event = map.get(action);
                event.setSource(this);
                event.setTime(System.currentTimeMillis());
                event.setTrigger(action.name());
                event.getCallback().invoke(event.getTarget(),event);  //??
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
