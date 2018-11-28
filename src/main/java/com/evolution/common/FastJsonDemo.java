package com.evolution.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-04-18 08:14
 */
@Data
public class FastJsonDemo {


    public static void main(String[] args) {

        List<String> list = new LinkedList<>();
        list.add("aa");
        list.add("bb");
        System.out.println(JSON.toJSONString(list));

        Map<String,String> map = new HashMap<>();
        map.put("aa","a");
        map.put("bb","b");
        String str = "{\"aa\":\"a\",\"bb\":\"b\"}";
        JSONObject job = JSONObject.parseObject(str);
        System.out.println(job);
    }

}
