package com.evolution.common;

import com.alibaba.fastjson.JSON;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-04-20 17:59
 */
public class MapDemo {
    public static void main(String[] args) {

        Object o = null;
        String s = (String) null;


        class Bean{
            @Getter
            Integer i;

            public Bean(Integer i) {
                this.i = i;
            }
        }
        Bean b = new Bean(999);
        Map<String,Bean> map = new HashMap<>();
        map.put("map",b);
//
//        Integer k = b.i;
//
//        System.out.println(b.i==k);
//        b.i = 888;
//        System.out.println(b.i==k);

        System.out.println(JSON.toJSONString(map));

        Map<String,Bean> map2 = new HashMap<>();
        map2.put("map2",b);

        Bean map2Bean = map2.get("map2");
        map2Bean.i = 2222;
        System.out.println(JSON.toJSONString(map));





    }
}


