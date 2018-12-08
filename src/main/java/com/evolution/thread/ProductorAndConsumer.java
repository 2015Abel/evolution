package com.evolution.thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description: ProductorAndConsumer
 * @author: liuzijian
 * @date: 2018-12-06 20:11
 */
public class ProductorAndConsumer {

    private final ArrayBlockingQueue<Integer> repo = new ArrayBlockingQueue(20);

    public void create(int bullet){
        try {
            System.out.println(Thread.currentThread().getName()+"压入子弹-"+bullet);
            repo.put(Integer.valueOf(bullet));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer get(){
        try {
            return repo.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
