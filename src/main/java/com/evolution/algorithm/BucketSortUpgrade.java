package com.evolution.algorithm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @description: 桶排序空间优化版
 * @author: liuzijian
 * @date: 2018-04-18 14:06
 */
public class BucketSortUpgrade {
    int arr[] = {306, 20000, 304, 12, 768, 310, 303, 307};   //待排序数组
    final int elementNum = 10;
    int min;    //最小值
    int max;    //最大值
    int bucketNum;  //桶个数

    public static void main(String[] args) {
        BucketSortUpgrade bucketSort = new BucketSortUpgrade();
        LinkedList<Integer> res = bucketSort.doOrder();
        System.out.println(res);
    }

    /**
     * @description: 桶排序
     * @return: java.util.LinkedList<java.lang.Integer>
     * @date: 2018/4/20 16:22
     */
    public LinkedList<Integer> doOrder() {
        firstLoop();  //首次遍历，获取最大值、最小值、桶个数等信息

        Map<Integer, LinkedList<Integer>> bucket = Maps.newTreeMap();

        // <<<<<<<  入桶方法 >>>>>>>
        for (int i : arr) {
            int bucketIndex = (i - min) / elementNum; //计算元素归属于哪个桶
            LinkedList<Integer> list = bucket.get(bucketIndex);
            if (list == null) {
                list = new LinkedList<>();
                bucket.put(bucketIndex, list);
            }
            list.add(i);
        }

        // <<<<<<<  出桶方法 >>>>>>>
        LinkedList<Integer> resList = Lists.newLinkedList();
        Iterator<Map.Entry<Integer, LinkedList<Integer>>> iterator = bucket.entrySet().iterator();
        int[] counter = new int[elementNum];
        while (iterator.hasNext()) {
            Map.Entry<Integer, LinkedList<Integer>> element = iterator.next();
            if (element.getValue() != null && element.getValue().size() > 0) {
                resList.addAll(outBucket(element,counter));   //计数排序方式出桶
                iterator.remove();  //每个桶完成出桶操作后，就释放桶空间
            }
        }
        return resList;
    }

    /**
     * 计数排序方式出桶
     *
     * @param bucketElement
     * @return
     */
    private List<Integer> outBucket(Map.Entry<Integer, LinkedList<Integer>> bucketElement,int[] counter) {
        Integer bucketNo = bucketElement.getKey();
        int bucketStart = bucketNo * elementNum + min;

        for(int i=0;i<elementNum;i++){
            counter[i] = 0;
        }

        for (Integer i : bucketElement.getValue()) {
            int count = counter[i - bucketStart];
            count++;
            counter[i - bucketStart] = count;
        }

        LinkedList<Integer> resList = Lists.newLinkedList();
        for (int i = 0; i < elementNum; i++) {
            int count = counter[i];
            if (count > 0) {
                resList.add(bucketStart + i);
                count--;
            }
        }
        return resList;
    }


    /**
     * @description: 第一次轮询，获取最大值、最小值和桶个数
     * @author: liuzijian
     * @return: void
     * @date: 2018/4/18 14:18
     */
    public void firstLoop() {
        min = arr[0];
        max = arr[0];
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        int addition = (max - min) % elementNum == 0 ? 0 : 1;   //如果有余数，桶个数+1
        bucketNum = (max - min) / elementNum + addition;
    }
}
