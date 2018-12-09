package com.evolution.algorithm;

/**
 * @description: 冒泡
 * @author: liuzijian
 * @create: 2018-12-09 15:14
 **/
public class BubbleSort {

    int[] arr = {8,54,36,78,2,11,99,1};

    public int[] doSort(){
        for(int i=0,len=arr.length;i<len-1;i++){
            int max = arr[i];
            for(int k=i+1;k<len;k++){
                if(arr[k]>max){
                    max = arr[k];
                    arr[k] = arr[i];
                    arr[i] = max;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        BubbleSort bs = new BubbleSort();
        int arr[] = bs.doSort();
        int i=0;
        while (i<arr.length){
            System.out.print(arr[i]+"\t");
            i++;
        }

    }
}
