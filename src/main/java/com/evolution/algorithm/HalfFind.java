package com.evolution.algorithm;

/**
 * @description: 折半查找
 * @author: liuzijian
 * @create: 2018-12-09 15:35
 **/
public class HalfFind {
    //提供的结合必须时有序的，否则需要先排序
    int[] arr = {1,8,11,36,84,88,90,93,99,118,176};

    public int doFind(int target){
        int position = -1;
        int min = 0;
        int max = arr.length-1;
        while (true){
            if(min==max){
                return position;
            }

            int half = (max - min) /2;
            if(arr[half] == target){
                return half;
            } else if (arr[half] > target){
                max = half;
            }else{
                min = half;
            }

        }
    }

    public static void main(String[] args) {
        HalfFind hf = new HalfFind();
        System.out.println(hf.doFind(11));
    }
}
