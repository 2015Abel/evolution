package com.evolution.algorithm;

import com.evolution.algorithm.bean.CardinalityBucket;

/**
 * @description: 基数排序
 * @author: liuzijian
 * @date: 2018-04-16 08:37
 */
public class CardinalitySort {
    static int arr[] = {123,8,32,76,9751,13,2,16};


    public int[] doOrder(){
        CardinalityBucket bucket = new CardinalityBucket();
        return bucket.collect(arr);
    }

    public static void main(String[] args) {
        CardinalitySort sort = new CardinalitySort();
//        System.out.println(sort.bitNum(arr[4],4));
        int res[] = sort.doOrder();
        StringBuilder bu = new StringBuilder();
        for(int i:res){
            bu.append(i+",");
        }
        System.out.println(bu);
    }
}
