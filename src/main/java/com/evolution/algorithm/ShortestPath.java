package com.evolution.algorithm;

import com.evolution.algorithm.bean.Node;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: 最短路径问题
 * @author: liuzijian
 * @date: 2018-04-11 23:04
 */
public class ShortestPath {

    Map<String,List<Node>> pic = new HashMap<>();
    Set<String> existsNodes = new HashSet<>();
    LinkedList<Node> pathList = new LinkedList<>();

    private void initPic(){
        pic.put("a",ImmutableList.of(new Node("c"),new Node("e")));
        pic.put("b",ImmutableList.of(new Node("a"),new Node("g")));
        pic.put("c",ImmutableList.of(new Node("f")));
        pic.put("d",ImmutableList.of(new Node("a"),new Node("g")));
        pic.put("e",ImmutableList.of(new Node("d"),new Node("a"),new Node("f")));
        pic.put("f",ImmutableList.of(new Node("b"),new Node("d")));
        pic.put("g",ImmutableList.<Node>of());
    }

    public ShortestPath(){
        initPic();
    }

    public void findPath(String source,final String target){
        findPath(new Node(source),target);
    }

    private void findPath(Node source,final String target){
        List<Node> relations = pic.get(source.getNode());
        for(Node node:relations){
            if(node.getNode().equals(target)){
                System.out.println("Get it!Path is '"+node.pathAppend(source.getPath())+"'");
                return;
            }else if(existsNodes.contains(node.getNode())){
                //do nothing
            }else{
                existsNodes.add(node.getNode());
                pathList.add(node);
                node.setPath(node.pathAppend(source.getPath()));
            }
        }

        if(pathList.isEmpty()){
            System.out.println("Sorry!Can not reach!");
        }else{
            Node node = pathList.removeFirst();
            findPath(node,target);
        }
    }

    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();
        sp.findPath("d","f");
    }

}
