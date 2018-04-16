package com.evolution.algorithm.bean;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 基数桶
 * @author: liuzijian
 * @date: 2018-04-16 09:35
 */
public class CardinalityBucket {
    private int collectTimes = 0;   //收集次数

    private int bitNum(int param,int bit){
        String paramStr = param+"";
        int len = paramStr.length();
        if(len>collectTimes){
            collectTimes=len;
        }
        if(len<bit){
            return 0;
        }
        char c =  paramStr.charAt(len-bit);
        return Integer.valueOf(c+"");
    }

    public int[] collect(int arr[]){
        int times = 1;
        do{
            arr = singleCollect(arr,times);
            times++;
        }while (times<=collectTimes);
        return arr;
    }

    /**
     * @description: 单次收集
     * @author: liuzijian
     * @param: [arr, times]
     * @return: void
     * @date: 2018/4/16 10:10
     */
    private int[] singleCollect(int arr[],int times){
        LinkedList<Integer>[] bucket = new LinkedList[10];
        for(int i:arr){
            int idx = bitNum(i,times);
            LinkedList<Integer> list = bucket[idx];
            if(isEmpty(list)){
                list = Lists.newLinkedList();
                bucket[idx] = list;
            }
            list.add(i);
        }

        LinkedList<Integer> resList = new LinkedList();
        for(LinkedList<Integer> element:bucket){
            if(!isEmpty(element)){
                resList.addAll(element);
            }
        }
        int res[] = new int[arr.length];
        for(int i=0,len=res.length;i<len;i++){
            res[i] = resList.remove();
        }
        return  res;
    }

    private boolean isEmpty(List list){
        return list==null || list.size()==0;
    }
}
