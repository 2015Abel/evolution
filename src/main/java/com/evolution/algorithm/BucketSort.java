package com.evolution.algorithm;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * @description: 桶排序
 * @author: liuzijian
 * @date: 2018-04-18 14:06
 */
public class BucketSort {
    int arr[] = {67, 29, 74, 52, 13, 16, 15, 59, 20, 61, 43, 38};

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        LinkedList<Integer> res = bucketSort.doOrder();
        System.out.println(res);
    }

    public LinkedList<Integer> doOrder() {
        InitParam initParam = firstLoop();
        //两、三个桶其实也意义不大？
        LinkedList<Integer>[] bucket = new LinkedList[initParam.bucketNum];
        for(int i:arr){
            int bucketIndex = (i-initParam.min)/elementNum;
            LinkedList<Integer> list = bucket[bucketIndex];
            if(list==null){
                list = new LinkedList<>();
                bucket[bucketIndex] = list;
            }
            addBySort(i,list);
        }

        //出桶
        LinkedList<Integer> resList = Lists.newLinkedList();
        for(LinkedList<Integer> bucketElement:bucket){
            if(bucketElement!=null && bucketElement.size()>0){
                resList.addAll(bucketElement);
            }
        }
        return resList;
    }

    /**
     * 按从小到大的顺序进行插入
     * @param i
     * @param list
     */
    private void addBySort(int i,LinkedList<Integer> list){
        if(list.size()==0){
            list.add(i);
            return;
        }

        int index = 0;

        for(Integer ele:list){
            if(i>=ele){
                index++;
            }else{
                break;
            }
        }
        list.add(index,i);
    }

    final int elementNum = 10;

    class InitParam {
        int min;
        int max;
        int bucketNum;

        public InitParam(int min, int max, int bucketNum) {
            this.min = min;
            this.max = max;
            this.bucketNum = bucketNum;
        }
    }

    /**
     * @description: 第一次轮询，获取最大值、最小值和桶个数
     * @author: liuzijian
     * @return: void
     * @date: 2018/4/18 14:18
     */
    public InitParam firstLoop() {
        int min = arr[0];
        int max = arr[0];
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        int addition = (max - min) % elementNum == 0 ? 0 : 1;
        int bucketNum = (max - min) / elementNum + addition;

        return new InitParam(min, max, bucketNum);
    }
}
