package com.evolution.common;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @create: 2018-04-23 00:22
 **/
public class VariableDemo {



    public void paramOperation(Integer[] a){
        a[0] = 1;
    }

    public void paramOperation2(int[] a2){
        a2[0] = 1;
    }

    public void paramOperation3(int a3){
        a3 = 1;
    }

    public void paramOperation4(Integer a4){
        a4 = new Integer(888);
    }

    public static void main(String[] args) {
        VariableDemo demo = new VariableDemo();
//        Integer[] arr = new Integer[10];
//        demo.paramOperation(arr);
//        System.out.println(arr[0]);
//
//        int[] arr2 = new int[10];
//        demo.paramOperation2(arr2);
//        System.out.println(arr2[0]);
//
//        int a3 = 0;
//        demo.paramOperation3(a3);
//        System.out.println(a3);

        Integer a4 = new Integer(666);
        demo.paramOperation4(a4);
        System.out.println(a4);
    }
}
