package com.evolution.common.bean;

import lombok.Data;

@Data
public class MapBean<K,V> {
    private K key;
    private V val;

    private MapBean(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public static <K,V>MapBean<K,V> init(K key, V val){
        return new MapBean<>(key,val);
    }

    @Override
    public String toString(){
        return key+"="+val;
    }
}
