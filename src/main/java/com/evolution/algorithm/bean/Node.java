package com.evolution.algorithm.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 图中的节点
 * @author: liuzijian
 * @date: 2018-04-11 23:06
 */
@Setter
@Getter
public class Node {
    private String node;
    private String path;

    public Node(String node) {
        this.node = node;
        path = node;
    }

    private final String nextSymbol = "->";

    public String pathAppend(String lastPath){
        return lastPath + nextSymbol + node;
    }
}
