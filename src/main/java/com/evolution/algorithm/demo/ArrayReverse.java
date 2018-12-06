package com.evolution.algorithm.demo;

/**
 * @description: 数组反转
 * @author: liuzijian
 * @date: 2018-12-06 16:42
 */
public class ArrayReverse {

    public static int[] reverse(int[] arr,int begin,int end){
        if(arr==null && arr.length==0){
            throw new RuntimeException("empty array!");
        }

        if(begin<0 || begin>=arr.length){
            throw new RuntimeException("wrong param begin!");
        }

        if(end<0 || end-begin<1){
            throw new RuntimeException("wrong param end!");
        }

        if(end>=arr.length){
            end = arr.length-1;
        }

        for(;begin<end;begin++){
            int temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
            end--;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        arr = reverse(arr,2,5);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");

        }
    }
}
