package com.evolution.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ReentrantLock
 * @Description: TODO
 * @Author: Lzj
 * @Date: 2019/8/30 10:14
 */
public class CountDownTest {

    public static void main(String[] args) throws InterruptedException {

        int total = 1;
        CountDownLatch countDownLatch = new CountDownLatch(total);

        while (total>0){
            Thread t = new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" countDown()..");
                countDownLatch.countDown();
            },"T-"+total);
            t.start();
            total--;
        }

        System.out.println("main-Thread await!");
        countDownLatch.await();
        System.out.println("main-Thread over!");

    }
}
