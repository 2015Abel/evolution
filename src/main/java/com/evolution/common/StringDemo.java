package com.evolution.common;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-04-23 10:25
 */
public class StringDemo {
    public static void main(String[] args) {
        /*String demo = "req/";
        System.out.println(demo.length());
        System.out.println(demo.lastIndexOf("/"));*/

        String str = "a";
        String beforeReplace = "abadd";
        String afterReplace = beforeReplace.replace(str,"z");
        System.out.println(afterReplace);
    }
}
