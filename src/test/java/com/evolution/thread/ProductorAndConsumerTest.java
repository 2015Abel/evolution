package com.evolution.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-12-06 20:18
 */
@RunWith(JUnit4.class)
public class ProductorAndConsumerTest {

    @Test
    public void testRun() throws InterruptedException {
        final ProductorAndConsumer gun = new ProductorAndConsumer();
        Thread prepare =new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i<1000){
                    gun.create(i);
                    i++;
                }
            }
        },"Thread-Prepare");
        prepare.start();

        Thread shoot = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"射出子弹-"+gun.get());
                }
            }
        },"Thread-Shoot");
        shoot.start();

        TimeUnit.SECONDS.sleep(100L);

    }

    @Test
    public void testRun2() throws InterruptedException {
        final ProductorAndConsumer2 gun = new ProductorAndConsumer2();
        Thread prepare =new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i<1000){
                    try {
                        gun.create(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        },"Thread-Prepare");
        prepare.start();

        TimeUnit.SECONDS.sleep(5L);
        Thread shoot = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        gun.shoot();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Thread-Shoot");
        shoot.start();

        TimeUnit.SECONDS.sleep(100L);


    }
}
