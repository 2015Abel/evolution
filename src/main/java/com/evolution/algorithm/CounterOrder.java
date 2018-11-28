package com.evolution.algorithm;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.Random;

/**
 * @description: 计数排序
 * @author: liuzijian
 * @date: 2018-04-17 08:29
 */
public class CounterOrder {
    int counterArr[] = new int[11]; //计数槽

    LinkedList<Integer> fifeenNum = Lists.newLinkedList();

    /**
     * 随机数初始化0-10的15个数字
     */
    private void init(){
        Random random = new Random();
        for(int i=0;i<15;i++){
            fifeenNum.add(random.nextInt(11));
        }
        System.out.println("source="+fifeenNum);
    }

    public CounterOrder(){
        init();
    }

    /**
     * 计数排序
     */
    public LinkedList<Integer> doOrder(){
        //  <<<<<<<<<   1.计数
        for(int i:fifeenNum){
            int count = counterArr[i];
            count++;
            counterArr[i] = count;
        }

        // <<<<<<<<<   2.出槽
        LinkedList<Integer> resList = Lists.newLinkedList();
        for(int i=0,len=counterArr.length;i<len;i++){
            int count = counterArr[i];
            while (count>0){
                resList.add(i);
                count--;
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        CounterOrder counter = new CounterOrder();
        System.out.println("result is "+counter.doOrder());
    }
}
