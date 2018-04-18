package com.evolution.algorithm.bean;

import lombok.Data;

/**
 * @description: 封装节点数据
 * @author: liuzijian
 * @date: 2018-04-11 23:06
 */
@Data
public class Node {
    private String node;
    private String path;    //保存路径

    public Node(String node) {
        this.node = node;
        path = node;
    }

    private final String nextSymbol = "->"; //间隔符

    public String pathAppend(String lastPath){
        return lastPath + nextSymbol + node;
    }
}
