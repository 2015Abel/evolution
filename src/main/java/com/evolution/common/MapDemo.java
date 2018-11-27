package com.evolution.common;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-04-20 17:59
 */
public class MapDemo {
    public static void main(String[] args) {


        Map<String,String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        System.out.println(JSON.toJSONString(new ArrayList<Map.Entry<String,String>>(map.entrySet())));



    }
}


