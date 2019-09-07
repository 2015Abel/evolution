package com.evolution.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLock
 * @Description: TODO
 * @Author: Lzj
 * @Date: 2019/8/30 10:14
 */
public class ReentrantLockTest {

    static Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();;


    public void doSomething(){
        lock.lock();
        System.out.println(String.format("%s线程，获取到锁了",Thread.currentThread().getName()));
        try {
            System.out.println(String.format("%s线程，await",Thread.currentThread().getName()));
            TimeUnit.SECONDS.sleep(2L); //模拟耗时业务逻辑执行
            condition.await();  //await
            System.out.println(String.format("%s线程，await被唤醒",Thread.currentThread().getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s线程，业务执行完毕",Thread.currentThread().getName()));
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        int total = 2;
        while (total>0){
            Thread t = new Thread(()->{
                test.doSomething();
            },"T-"+total);
            t.start();

            TimeUnit.MILLISECONDS.sleep(200L);  //让子线程T-1率先获取到锁
            lock.lock();
            TimeUnit.SECONDS.sleep(10L);
            System.out.println(String.format("%s线程，获取到锁了",Thread.currentThread().getName()));
            test.condition.signal();
            System.out.println(String.format("%s线程，signal",Thread.currentThread().getName()));
            lock.unlock();
            total--;
        }



    }
}
